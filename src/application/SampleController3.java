package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Properties;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class SampleController3 {
	@FXML
	private Label car_model;

	@FXML
	private Label driver;

	@FXML
	private Label pickup_date;

	@FXML
	private Label dropoff_date;

	@FXML
	private Label car_rent;

	@FXML
	private Label car_dep;

	@FXML
	private Label driver_cost;

	@FXML
	private Label num_of_days;

	@FXML
	private Label total_cost;

	public ArrayList<Car> data;
	public ObservableList<Car> dataList;
	static String vin;
	static int driver_id;
	static int cid;
	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "1234";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "CarRental";
	private Connection con;
	static String start_date;
	static String end_date;
	public String driver_name;
	public String model;
	public double rentalprice;
	public double deposit;
	public int driver_daily_cost = 100;

	private void getData() throws SQLException, ClassNotFoundException {

		String SQL;

		connectDB();
		System.out.println("Connection established");

		// Choosecontrol x = (Choosecontrol)
		SQL = "select c.model, c.Rental_price_per_day, c.security_deposit from car c" + " where c.vin = " + vin + ";";
		System.out.println(SQL);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		rs.next();
		model = rs.getString(1);
		rentalprice = Double.parseDouble(rs.getString(2));
		deposit = Double.parseDouble(rs.getString(3));
		System.out.println("hello" + model);
		rs.close();
		stmt.close();
		if (driver_id != 0) {
			String SQL2 = "select e.ename from employees e where e.jid = " + driver_id + ";";
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery(SQL2);
			rs2.next();
			driver_name = rs2.getString(1);
		}
		// con.close();
		// System.out.println("Connection closed" + data.size());

	}

	public void initialize() {
		try {
			getData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (driver_id != 0) {
			driver.setText(driver_name);
		} else
			driver.setText("None");
		car_model.setText(model);
		String start_dateCopy = start_date.replace("'", "");
		String end_dateCopy = end_date.replace("'", "");
		System.out.println(start_date);
		String[] line1 = start_dateCopy.split("-");
		int year = Integer.parseInt(line1[0]);
		int month = Integer.parseInt(line1[1]);
		int day = Integer.parseInt(line1[2]);
		LocalDate st = LocalDate.of(year, month, day);
		String[] line2 = end_dateCopy.split("-");
		year = Integer.parseInt(line2[0]);
		month = Integer.parseInt(line2[1]);
		day = Integer.parseInt(line2[2]);
		LocalDate fin = LocalDate.of(year, month, day);
		pickup_date.setText(start_date);
		dropoff_date.setText(end_date);
		car_rent.setText(Double.toString(rentalprice));
		car_dep.setText(Double.toString(deposit));
		if (driver_id != 0)
			driver_cost.setText(Integer.toString(driver_daily_cost));
		else
			driver_cost.setText("None");
		long number_of_days = ChronoUnit.DAYS.between(st, fin);
		num_of_days.setText(Long.toString(number_of_days));
		double sum;
		if (driver_id != 0)
			sum = (number_of_days * rentalprice) + (number_of_days * driver_daily_cost);
		else
			sum = number_of_days * rentalprice;
		total_cost.setText(Double.toString(sum));
	}

	public static void setVin(String vin) {
		SampleController3.vin = vin;
	}

	public static void setDriver_id(int driver_id) {
		SampleController3.driver_id = driver_id;
	}

	public static void setStart_date(String start) {
		SampleController3.start_date = start;
	}

	public static void setEnd_date(String end) {// MAKE SURE THATT ABU ZEINA SENDS ME THE STRING IN THE REQUIRED FORM "'
												// '"
		SampleController3.end_date = end;
	}

	public static void setCid(int cid) {
		SampleController3.cid = cid;
	}

	public void confirmBook(ActionEvent event) throws SQLException, ClassNotFoundException {
		// go back to booking page
		Statement stmt = null;
		try {
			connectDB();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int x = 0;
		if (driver_id != 0) {
			x = 1;
		}
		String SQL = "insert into booking(paid,date_of_pickup, date_of_dropoff, need_driver, makes_cid)" + "values("
				+ false + "," + start_date + "," + end_date + "," + x + "," + cid + ");";
		//stmt.executeQuery(SQL);
		ExecuteStatement(SQL);
		SQL = "insert into customer2car(cust_id, car_vin, getdate)" + "values(" + cid + "," + vin + "," + start_date
				+ ");";
		ExecuteStatement(SQL);
		
	
		Parent root1;
		try {
			
			root1 = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scenec = new Scene (root1, 600, 400);
			Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
			sts.setScene(scenec);
			sts.setTitle("");
		
			sts.close();
		} catch (IOException e) {
		
		}
		////abu zeina we need to get out from project
	}

	public void back(ActionEvent event2) throws SQLException {
		// go back to available cars page
		con.close();
		Parent root1;
		try {
			root1 = FXMLLoader.load(getClass().getResource("choose.fxml"));
			Scene scenec = new Scene(root1, 1000, 500);
			Stage sts = (Stage) (((Node) event2.getSource()).getScene().getWindow());
			sts.setScene(scenec);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public void ExecuteStatement(String SQL) throws SQLException {

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();

		} catch (SQLException s) {
			s.printStackTrace();
			System.out.println("SQL statement is not executed!");

		}

	}

}
