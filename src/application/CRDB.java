package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CRDB {

	private static String dbUsername = "root"; // database username
	private static String dbPassword = "1234"; // database password

	private static String URL = "127.0.0.1"; // server location
	private static String port = "3306"; // port that mysql uses
	private static String dbName = "CarRental"; // database on mysql to connect to


	public static ResultSet select(String SQL) throws SQLException, ClassNotFoundException {
		System.out.println(SQL);
		DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPassword);
		login_c.con = a.connectDB();
		Statement stmt = login_c.con.createStatement();
		return stmt.executeQuery(SQL);
	}

	public static void execute(String SQL) throws ClassNotFoundException, SQLException{
		System.out.println(SQL);
		DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPassword);
		login_c.con = a.connectDB();
		Statement stmt = login_c.con.createStatement();
		stmt.executeUpdate(SQL);
		stmt.close();
		login_c.con.close();
	}
}

class DBConn {

	private Connection con;
	private String dbURL;
	private String dbUsername;
	private String dbPassword;
	private String URL;
	private String port;
	private String dbName;

	DBConn(String URL, String port, String dbName, String dbUsername, String dbPassword) {
		this.URL = URL;
		this.port = port;
		this.dbName = dbName;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
	}

	public Connection connectDB() throws ClassNotFoundException, SQLException {
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(dbURL, p);
		return con;
	}
}