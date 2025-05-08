package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String HOST = "localhost:3306";
    private static final String DATABASE = "kasir_toko";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String URL = "jdbc:mysql://" + HOST +  "/" + DATABASE;

    private static Connection connection;

    //method untuk koneksi ke datababase
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) { // Cek kalau koneksi null atau udah ditutup
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //method untuk menutup koneksi database
    public static void closeConnection(){
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch  (SQLException e) {
            e.printStackTrace();
        }
    }


}
