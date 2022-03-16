package application;
import javafx.scene.Node;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import application.SampleController3;

public class Choosecontrol implements Initializable {

	@FXML
	private TableView<Car> table;

	@FXML
	private TableColumn<Car, String> vin;

	@FXML
	private TableColumn<Car, String> manufacturer;

	@FXML
	private TableColumn<Car, String> model;

	@FXML
	private TableColumn<Car, Integer> year;

	@FXML
	private TableColumn<Car, Double> cost_day;

	@FXML
	private TableColumn<Car, String> fuel;

	@FXML
	private TableColumn<Car, Double> deposit;

	@FXML
	private TableColumn<Car, String> gearbox;

	@FXML
	private TableColumn<Car, String> size;

	@FXML
	private TableColumn<Car, Integer> occupants;

	@FXML
	private TableColumn<Car, String> color;
	
	@FXML
	public Button conf;
	
	@FXML
	public ComboBox<String> selections;
	
	@FXML
	public Button back;


	public ArrayList<Car> data;
	public ObservableList<Car> dataList;
	public ObservableList<String> vinList;
	public ArrayList<String> vins;
	static String carSelected;
	static int driver_id;
	static String start_date;
	static String end_date;
	static int cid;
	static boolean driver_wanted;
	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "1234";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "CarRental";
	private Connection con;

	private void getData() throws SQLException, ClassNotFoundException {

		String SQL;

		connectDB();
		System.out.println("Connection established");
		start_date = booking.getStart();
		end_date = booking.getEnd();  
		SQL = "select c.vin, c.manufacturer_name, c.model, c.year_of_manufacture, c.Rental_price_per_day, c.fuel_efficiency, c.security_deposit, c.transmission_system, c.size, c.number_of_occupants, c.color "
				+ " from car c where c.vin not in(select c.vin from car c, customer2car c2c, customer cs, booking b where c.vin = c2c.car_vin and c2c.cust_id = cs.cid and cs.cid = b.makes_cid and (( b.date_of_pickup >= "
				+ start_date + " and b.date_of_pickup <= " + end_date + ") or (b.date_of_dropoff >= " + start_date
				+ " and b.date_of_dropoff <= " + end_date + ") or (b.date_of_pickup <= " + start_date
				+ " and b.date_of_dropoff >= " + end_date + ")) );";
		System.out.println(SQL);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		
		while (rs.next()) {
			data.add(new Car(rs.getString(1), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)),
					Double.parseDouble(rs.getString(5)), rs.getString(6), Double.parseDouble(rs.getString(7)),
					rs.getString(8), rs.getString(9), Integer.parseInt(rs.getString(10)), rs.getString(11)));
			
		}
		rs.close();
		stmt.close();
		if (driver_wanted) {
			String SQL2 = "select d.jid "
					+ " from driver d where d.jid not in(select d.jid from driver d, cust2driver c2d, customer cs, booking b"
					+ " where d.jid = c2d.eid and c2d.cid = cs.cid and cs.cid = b.makes_cid and (( b.date_of_pickup >= "
					+ start_date + " and b.date_of_pickup <= " + end_date + ") or (b.date_of_dropoff >= " + start_date
					+ " and b.date_of_dropoff <= " + end_date + ") or (b.date_of_pickup <= " + start_date
					+ " and b.date_of_dropoff >= " + end_date + ")) );";
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery(SQL2);
			if (rs2.next()) {
				driver_id = Integer.parseInt(rs2.getString(1));
			}
			rs2.close();
			stmt2.close();
		}
		else {
			driver_id = 0;
		}
		con.close();
		System.out.println(driver_id);
		System.out.println("Connection closed" + data.size());
		
	}

	public static void setDriver_wanted(boolean wanted) {
		Choosecontrol.driver_wanted = wanted;
	}
	public static void setStart_date(String start) {
		Choosecontrol.start_date = start;
	}
	
	public static void setEnd_date(String end) {//MAKE SURE THATT ABU ZEINA SENDS ME THE STRING IN THE REQUIRED FORM "' '"
		Choosecontrol.end_date = end;
	}
	
	public void initialize(URL Location, ResourceBundle resources) {
		data = new ArrayList<Car>();
		vins = new ArrayList<String>();
		try {
			getData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataList = FXCollections.observableArrayList(data);
		System.out.println(dataList.size());
		vin.setCellValueFactory(new PropertyValueFactory<Car, String>("vin"));
		manufacturer.setCellValueFactory(new PropertyValueFactory<Car, String>("manufacturer_name"));
		model.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
		year.setCellValueFactory(new PropertyValueFactory<Car, Integer>("year_of_manufacture"));
		cost_day.setCellValueFactory(new PropertyValueFactory<Car, Double>("Rental_price_per_day"));
		fuel.setCellValueFactory(new PropertyValueFactory<Car, String>("fuel_efficiency"));
		deposit.setCellValueFactory(new PropertyValueFactory<Car, Double>("security_deposit"));
		gearbox.setCellValueFactory(new PropertyValueFactory<Car, String>("transmission_system"));
		size.setCellValueFactory(new PropertyValueFactory<Car, String>("size"));
		occupants.setCellValueFactory(new PropertyValueFactory<Car, Integer>("number_of_occupants"));
		color.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));
		table.setItems(dataList);
		for(int i  = 0; i < data.size(); i++) {
			vins.add(data.get(i).getVin());
		}
		vinList = FXCollections.observableArrayList(vins);
		selections.setItems(vinList);
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
	
	public void comboChanged(ActionEvent event) {
		carSelected = selections.getValue();
		System.out.println(carSelected);
	}
	
	public void buttonPressed(ActionEvent event2) throws SQLException {
		//go to next window and pass VIN of the car selected
		Parent root1;
		try {
			carSelected = "'" + carSelected + "'";
			SampleController3.setVin(carSelected);
			SampleController3.setStart_date(start_date);
			SampleController3.setEnd_date(end_date);
			SampleController3.setDriver_id(driver_id);
			SampleController3.setCid(cid);
			root1 = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scenec = new Scene (root1, 600, 400);
			Stage sts = (Stage) (((Node) event2.getSource()).getScene().getWindow());
			sts.setScene(scenec);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("VIN Chosen is: " + carSelected);
		System.out.println("Next");
	}
	public static void setCid1(int cid) {
		Choosecontrol.cid = cid;
	}

	public void backPressed(ActionEvent event3) throws SQLException {
		Parent root1;
		try {
			
			root1 = FXMLLoader.load(getClass().getResource("booking.fxml"));
			Scene scenec = new Scene (root1, 600, 400);
			Stage sts = (Stage) (((Node) event3.getSource()).getScene().getWindow());
			sts.setScene(scenec);
			sts.setTitle("booking");
		
			sts.show();
		} catch (IOException e) {
		
		}
		
	}
}
