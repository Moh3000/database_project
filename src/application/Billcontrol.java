package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Billcontrol implements Initializable {

	@FXML
	private TextField custid;

	@FXML
	private TextField year;

	@FXML
	private TextField month;

	@FXML
	private TextField day;

	@FXML
	private Button show_costs;

	@FXML
	private Button confirmp;

	@FXML
	private Label carcost;

	@FXML
	private Label drivercost;
	@FXML
	private Label pickup;
	@FXML
	private Label dropoff;
	@FXML
	private Label num_days;
	@FXML
	private Label num_late;
	@FXML
	private Label total;

	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "1234";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "CarRental";
	private Connection con;

	public String customer_id;
	public int eyear;
	public int emonth;
	public int eday;

	public void initialize(URL Location, ResourceBundle resources) {

	}

	public void getToAccountscreen(ActionEvent event2) throws SQLException {
		// go back to accountant
		Parent root1;
		//con.close();
		try {
			root1 = FXMLLoader.load(getClass().getResource("Accountantui.fxml"));
			Scene scenec = new Scene(root1, 600, 400);
			Stage sts = (Stage) (((Node) event2.getSource()).getScene().getWindow());
			sts.setScene(scenec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showvalues(ActionEvent event) throws SQLException, ClassNotFoundException {
		customer_id = custid.getText();
		eyear = Integer.parseInt(year.getText());
		emonth = Integer.parseInt(month.getText());
		eday = Integer.parseInt(day.getText());
		String bid = "";
		String SQL = "select b.bid from booking b ,customer c where b.makes_cid = " + customer_id + " and b.paid = 0;";
		try {
			bid = getData(SQL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SQL = "select b.date_of_pickup from booking b where b.bid = " + bid + " ;";
		String stdate = "";
		try {
			stdate = getData(SQL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pickup.setText(stdate);
		SQL = "select b.date_of_dropoff from booking b where b.bid = " + bid + " ;";
		String enddate = "";
		try {
			enddate = getData(SQL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//label type
		dropoff.setText(enddate);
		String needsDriver = "";
		SQL = "select b.need_driver from booking b where b.bid = " + bid + ";";
		try {
			needsDriver = getData(SQL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String simdi = "";
		SQL = "select c.Rental_price_per_day from car c, customer cs, booking b, customer2car c2c where " + " b.bid =  "
				+ bid + " and  " + customer_id + "  = cs.cid and cs.cid = c2c.cust_id and c2c.car_vin = c.vin;";

		try {
			simdi = getData(SQL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		carcost.setText(simdi);
		int driverprice = 100;
		String sdriverprice = "100";

		double rentalPrice = Double.parseDouble(simdi);
		if (needsDriver.equals("0")) {
			drivercost.setText("No driver");
		} else {
			drivercost.setText(sdriverprice);
		}
		LocalDate today = LocalDate.of(eyear, emonth, eday);
		String[] line1 = stdate.split("-");
		int year1 = Integer.parseInt(line1[0]);
		int month1 = Integer.parseInt(line1[1]);
		int day1 = Integer.parseInt(line1[2]);
		LocalDate st = LocalDate.of(year1, month1, day1);
		System.out.println("pickup" + st);
		String[] line = enddate.split("-");
		int year2 = Integer.parseInt(line[0]);
		int month2 = Integer.parseInt(line[1]);
		int day2 = Integer.parseInt(line[2]);
		LocalDate fin = LocalDate.of(year2, month2, day2);
		System.out.println("dropoff" + fin);
		long number_of_days = ChronoUnit.DAYS.between(st, fin);
		num_days.setText(Long.toString(number_of_days));
		System.out.println(number_of_days);
		long days_late = ChronoUnit.DAYS.between(fin, today);
		num_late.setText(Long.toString(days_late));
		System.out.println(days_late);
		// Statement stmt = null;
		/*
		 * try { //connectDB(); } catch (ClassNotFoundException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); } catch (SQLException e1) {
		 * // TODO Auto-generated catch block e1.printStackTrace(); }
		 */
		double costs = 0;
		if (needsDriver.equals("1")) {
			if (days_late >= 0) {
				costs = (number_of_days * rentalPrice) + (number_of_days * driverprice) + (days_late * 3 * rentalPrice);
			} else {
				number_of_days = number_of_days + days_late + 1;
				costs = (number_of_days * rentalPrice) + (number_of_days * driverprice);
			}

		} else {
			if (days_late >= 0) {
				costs = (number_of_days * rentalPrice) + (days_late * 3 * rentalPrice);
			} else {
				number_of_days = number_of_days + days_late + 1;
				costs = (number_of_days * rentalPrice);
			}
		}
		num_days.setText(Long.toString(number_of_days));
		total.setText(Double.toString(costs));
		SQL = "update booking set booking.paid = 1 where booking.makes_cid  = " + customer_id + " and booking.bid = "
				+ bid + ";";
		// executeit(SQL);
		// stmt.executeQuery(SQL);
		connectDB();
		ExecuteStatement(SQL);
		String foul = Double.toString(days_late * 3 * rentalPrice);
		SQL = "insert into resulting_bill(book_id, bdate, foul_of_late_car, total_to_pay)" + "values(" + bid + ",'"
				+ today + "'," + foul + "," + costs + ");";
		// stmt.executeQuery(SQL);
		// stmt.close();
		ExecuteStatement(SQL);
	}


	private String getData(String SQL) throws SQLException, ClassNotFoundException {

		connectDB();
		System.out.println(SQL);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		rs.next();
		String result = rs.getString(1);

		rs.close();
		stmt.close();
		con.close();

		return result;

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
