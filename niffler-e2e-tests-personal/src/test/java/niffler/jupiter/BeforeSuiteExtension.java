package niffler.jupiter;

import java.sql.*;

public class BeforeSuiteExtension implements AroundAllTestsExtension {

    @Override
    public void beforeAllTests() {
        //AroundAllTestsExtension.super.beforeAllTests();
        //System.out.println("BEFORE SUITE!!!");

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/niffler-spend",
                    "postgres",
                    "secret"
            );
            Statement statement = connection.createStatement();
            int insertedColumns = statement.executeUpdate("insert into \"spend\" (username, spend_date, currency, amount, description, category_id)\n" +
                    " values ('testuser', '2025-05-28', 'RUB', '1000.5', 'Обучение в QA.GURU ADVANCED', '0852fcf6-3c88-11f0-9e4a-e6cc26d07876')"
            );
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterAllTests() {
        //AroundAllTestsExtension.super.afterAllTests();
        //System.out.println("AFTER SUITE!!!");
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/niffler-spend",
                    "postgres",
                    "secret"
            );
            Statement statement = connection.createStatement();
            int deletedColumns = statement.executeUpdate(
                    " delete from \"spend\"\n" +
                        " where username = 'testuser'"
            );
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
