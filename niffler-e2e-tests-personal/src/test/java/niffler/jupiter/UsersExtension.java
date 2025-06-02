package niffler.jupiter;

import io.qameta.allure.AllureId;
import niffler.dto.UserModel;
import org.junit.jupiter.api.extension.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static niffler.jupiter.User.UserType.ADMIN;
import static niffler.jupiter.User.UserType.COMMON;

// TODO необходимо поэкспериментировать с ExtensionContext.getRequiredTestMethod(),
//  ExtensionContext.getRequiredTestClass(), ExtensionContext.getRequiredTestInstance(),
//  понять на каком этапе жизненного цикла Extension они работают.
//  Возможно, это можно в MockTest провернуть.

// TODO дополнить UsersExtension возможностью указать двух юзеров разных типов в одном тесте.
//  UsersExtension должен обеспечивать резолв обоих юзеров,
//  дожидаясь, пока нужная для теста комбинация не станет доступна в очередях.
//  Вместе с этим надо будет и SampleApiTest допилить.

public class UsersExtension implements
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

    public static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(UsersExtension.class);

    private static final Queue<UserModel> USER_MODEL_ADMIN_QUEUE = new ConcurrentLinkedQueue<>();
    private static final Queue<UserModel> USER_MODEL_COMMON_QUEUE = new ConcurrentLinkedQueue<>();

    static {
        USER_MODEL_ADMIN_QUEUE.add(new UserModel("testuser1", "testuser1"));
        USER_MODEL_COMMON_QUEUE.add(new UserModel("testuser2", "testuser2"));
        USER_MODEL_COMMON_QUEUE.add(new UserModel("testuser3", "testuser3"));
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        String id = getTestID(context);

        User.UserType desiredUserType = Arrays.stream(context
                .getRequiredTestMethod()
                .getParameters())
                .filter(p-> p.isAnnotationPresent(User.class))
                .map(p -> p.getAnnotation(User.class))
                .findFirst()
                .orElseThrow().userType();

        UserModel user = null;
        while (user == null) {
            if (desiredUserType == ADMIN) {
                user = USER_MODEL_ADMIN_QUEUE.poll();
            } else {
                user = USER_MODEL_COMMON_QUEUE.poll();
            }
        }
        Objects.requireNonNull(user);
        context.getStore(NAMESPACE).put(id, Map.of(desiredUserType, user));
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        String id = getTestID(context);

        Map<User.UserType, UserModel> map = context.getStore(NAMESPACE).get(id, Map.class);
        if (map.containsKey(ADMIN)) {
            USER_MODEL_ADMIN_QUEUE.add(map.get(ADMIN));
        } else {
            USER_MODEL_COMMON_QUEUE.add(map.get(COMMON));
        }
    }

    private String getTestID(ExtensionContext context) {
//        AllureId annotation = context.getRequiredTestMethod().getAnnotation(AllureId.class);
//        if (annotation != null) {
//            return annotation.value();
//        } else {
//            context.getRequiredTestMethod().getName();
//        }

        return Objects.requireNonNull(
                context.getRequiredTestMethod().getAnnotation(AllureId.class)
        ).value();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(UserModel.class)
                && parameterContext.getParameter().isAnnotationPresent(User.class);
    }

    @Override
    public UserModel resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        String id = getTestID(extensionContext);
        return (UserModel) extensionContext
                .getStore(NAMESPACE)
                .get(id, Map.class)
                .values()
                .iterator()
                .next();
    }
}
