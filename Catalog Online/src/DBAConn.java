import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBAConn {

    public static Connection getConnection() {

        String url = "jdbc:mysql://104.248.84.92/ARTproiect";
        String user = "java";
        String pass = "Javaestetare123!";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println(connection);
            System.out.println("V-ati conectat cu succes!");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;

    }

    public static Connection connection = null;

}



