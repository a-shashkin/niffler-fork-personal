package niffler.jupiter;

public class BeforeSuiteExtension implements AroundAllTestsExtension {

    @Override
    public void beforeAllTests() {
        //AroundAllTestsExtension.super.beforeAllTests();
        System.out.println("BEFORE SUITE!!!");;
    }

    @Override
    public void afterAllTests() {
        //AroundAllTestsExtension.super.afterAllTests();
        System.out.println("AFTER SUITE!!!");
    }
}
