package niffler.jupiter;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.function.Function;

public interface AroundAllTestsExtension extends BeforeAllCallback {

    default void beforeAllTests() {}
    default void afterAllTests() {}

    @Override
    default void beforeAll(ExtensionContext context) throws Exception {
        context
                .getRoot()
                .getStore(ExtensionContext.Namespace.GLOBAL)
                .getOrComputeIfAbsent(
                        this.getClass(),
                        k -> {
                            beforeAllTests();
                            return (ExtensionContext.Store.CloseableResource) this::afterAllTests;
                        }
                    );
    };
}
