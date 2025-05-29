package niffler;

import niffler.jupiter.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(/*{
        MyBeforeAllExtension.class,
        MyBeforeEachExtension.class,
        MyAfterEachExtension.class,
        MyAfterAllExtension.class
}*/
MySuperExtension.class)
public class MockTest extends BaseTest {

    @BeforeAll
    static void setUpAll() {
        System.out.println("        ### BeforeAll! ###");
    }

    @BeforeEach
    void setUp() {
        System.out.println("            ### BeforeEach! ###");
    }

    @Test
    public void test() {
        System.out.println("                        ### TEST! ###");
    }

    @AfterEach
    void tearDown() {
        System.out.println("            ### AfterEach! ###");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("        ### AfterAll! ###");
    }
}
