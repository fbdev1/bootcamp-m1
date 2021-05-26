package hiberConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class H2CreateExample {

    private static final String createTableSQL = "create table cities (\r\n" + "  id  int(10) primary key,\r\n" +
            "  name varchar(255),\r\n" + "  region varchar(255),\r\n" + "  district varchar(255),\r\n" + "  population varchar(20),\r\n" +
            "  foundation varchar(255)\r\n" + "  );";

    public void createTable() throws SQLException {

        System.out.println(createTableSQL);
        try (Connection connection = H2JDBCUtils.getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
}