import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBAConn {


    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/banca1";
        String pass = "";
        String user = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println(connection);
            System.out.println("Successfully connected to database.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;

    }

    private static Connection connection = null;

}




