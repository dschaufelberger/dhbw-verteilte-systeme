package info.sperber;

import java.sql.*;

/**
 * Created by Dodo on 23.02.2017.
 */
public class Database {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection connection = DriverManager.getConnection("jdbc:mysql://" +
                "localhost/vorlesung?" +
                "user=vorlesung&password=vorlesung");

        try (Statement sql = connection.createStatement()) {
            ResultSet result = sql.executeQuery("SELECT * FROM tabelle;");
            while(result.next()) {
                System.out.println(String.format("%s: %s", result.getString(1), result.getString(2)));
            }
        }
    }
}
