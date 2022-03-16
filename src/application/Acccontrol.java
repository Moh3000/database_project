package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Acccontrol implements Initializable{
	
	@FXML
	private TableView<Employee> employees;

	@FXML
	private TableColumn<Employee, String> name;
	
	@FXML
	private TableColumn<Employee, String> gender;
	
	@FXML
	private TableColumn<Employee, Double> salary;
	
	@FXML
	private TableColumn<Employee, Boolean> employed;
	
	@FXML
	private TableColumn<Employee, String> address;
	
	@FXML
	private TableColumn<Employee, String> email;
	
	@FXML
	private TableColumn<Employee, Integer> phone;
	
	@FXML
	private TableColumn<Employee, String> type;
	
	@FXML
	private Button payment;
	
	@FXML
	private Button statistics;
	
	@FXML
	private Button monthly;
	
	@FXML
	private Button exit;
	
	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "1234";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "CarRental";
	private Connection con;
	
	public ArrayList<Employee> datas;
	public ObservableList<Employee> datasList;
	
	private void getData() throws SQLException, ClassNotFoundException {

		String SQL;

		connectDB();

		SQL = "select e.ename,e.gender,e.total_salary, e.still_employed, e.address, e.email, e.phone_num from"
			   + " employees e, manager m where e.jid = m.jid;";
		System.out.println(SQL);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		
		while (rs.next()) {
			datas.add(new Employee(rs.getString(1), rs.getString(2), Double.parseDouble(rs.getString(3)), getbool(rs.getString(4)),
				rs.getString(5), rs.getString(6),Integer.parseInt(rs.getString(7)), "Manager"));
			
		}
		rs.close();
		stmt.close();
		
		String SQL1 = "select e.ename,e.gender,e.total_salary, e.still_employed, e.address, e.email, e.phone_num from"
				   + " employees e, driver d where e.jid = d.jid;";
		System.out.println(SQL1);
		Statement stmt1 = con.createStatement();
		ResultSet rs1 = stmt1.executeQuery(SQL1);
		while (rs1.next()) {
			datas.add(new Employee(rs1.getString(1), rs1.getString(2), Double.parseDouble(rs1.getString(3)), getbool(rs1.getString(4)),
				rs1.getString(5), rs1.getString(6),Integer.parseInt(rs1.getString(7)), "Driver"));
			
		}
		rs1.close();
		stmt1.close();
		
		String SQL2 = "select e.ename,e.gender,e.total_salary, e.still_employed, e.address, e.email, e.phone_num from"
				   + " employees e, accountant a where e.jid = a.jid;";
		System.out.println(SQL2);
		Statement stmt2 = con.createStatement();
		ResultSet rs2 = stmt2.executeQuery(SQL2);
		while (rs2.next()) {
			datas.add(new Employee(rs2.getString(1), rs2.getString(2), Double.parseDouble(rs2.getString(3)), getbool(rs2.getString(4)),
				rs2.getString(5), rs2.getString(6),Integer.parseInt(rs2.getString(7)), "Accountant"));
			
		}
		rs2.close();
		stmt2.close();
		
		String SQL3 = "select e.ename,e.gender,e.total_salary, e.still_employed, e.address, e.email, e.phone_num from"
				   + " employees e, mechanic m where e.jid = m.jid;";
		System.out.println(SQL3);
		Statement stmt3 = con.createStatement();
		ResultSet rs3 = stmt3.executeQuery(SQL3);
		while (rs3.next()) {
			datas.add(new Employee(rs3.getString(1), rs3.getString(2), Double.parseDouble(rs3.getString(3)), getbool(rs3.getString(4)),
				rs3.getString(5), rs3.getString(6),Integer.parseInt(rs3.getString(7)), "Mechanic"));
		}
		rs3.close();
		stmt3.close();
		
		con.close();		
	}
	
	public static boolean getbool(String x) {
		if ( x.equals("1") ) {
			return true;
		}
		else
			return false;
	}
	
	public void pay_bills(ActionEvent event2) {
		Parent root1;
		//con.close();
		try {
			root1 = FXMLLoader.load(getClass().getResource("Bill.fxml"));
			Scene scenec = new Scene(root1, 600, 400);
			Stage sts = (Stage) (((Node) event2.getSource()).getScene().getWindow());
			sts.setScene(scenec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void exit(ActionEvent event) {
		//got out of project window
	}
	
	public void initialize(URL Location, ResourceBundle resources) {
		datas = new ArrayList<>();
		try {
			getData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		datasList = FXCollections.observableArrayList(datas);
		name.setCellValueFactory(new PropertyValueFactory<Employee, String>("ename"));
		gender.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
		salary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("total_salary"));
		employed.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("still_employed"));
		address.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
		email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
		phone.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("phone_num"));
		type.setCellValueFactory(new PropertyValueFactory<Employee, String>("job_type"));
		employees.setItems(datasList);
	}
	
	private void connectDB() throws ClassNotFoundException, SQLException {

		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName
				+ "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=ConvertToNull&serverTimezone=GMT";
		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection(dbURL, p);

	}
}
