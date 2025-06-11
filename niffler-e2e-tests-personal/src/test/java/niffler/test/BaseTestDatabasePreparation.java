package niffler.test;

import niffler.jupiter.BeforeSuiteExtensionDatabasePreparation;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BeforeSuiteExtensionDatabasePreparation.class)
public abstract class BaseTestDatabasePreparation {
}
