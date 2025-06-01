package niffler.jupiter;

import io.qameta.allure.AllureId;
import niffler.dto.UserModel;
import org.junit.jupiter.api.extension.*;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UsersExtension implements
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

    private static final Queue<UserModel> USER_MODEL_QUEUE = new ConcurrentLinkedQueue<>();

    static {
        USER_MODEL_QUEUE.add(new UserModel("testuser1", "testuser1"));
        USER_MODEL_QUEUE.add(new UserModel("testuser2", "testuser2"));
        USER_MODEL_QUEUE.add(new UserModel("testuser3", "testuser3"));
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        String id = getTestID(context);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        String id = getTestID(context);
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
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return null;
    }
}
