/*package application;

import application.Customer;
import static javafx.stage.Modality.NONE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.DateStringConverter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main extends Application {

	private ArrayList<Customer> data;
	private ObservableList<Customer> dataList;

	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "1234";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "CarRental";
	private Connection con;

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage stage) {
		data = new ArrayList<>();

		try {

			getData();

			// convert data from arraylist to observable arraylist
			dataList = FXCollections.observableArrayList(data);

			// really bad method
			tableView(stage);
			stage.show();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")

	private void tableView(Stage stage) {

		TableView<Customer> myDataTable = new TableView<Customer>();

		Scene scene = new Scene(new Group());
		stage.setTitle("Customer Table");
		stage.setWidth(850);
		stage.setHeight(500);

		Label label = new Label("Customer Table");
		label.setFont(new Font("Times New Roman", 20));
		label.setTextFill(Color.RED);

		myDataTable.setEditable(true);
		myDataTable.setMaxHeight(200);
		myDataTable.setMaxWidth(830);

		// name of column for display
		TableColumn<Customer, Integer> cidCol = new TableColumn<Customer, Integer>("Cid");
		cidCol.setMinWidth(50);

		// to get the data from specific column
		cidCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("cid"));

		TableColumn<Customer, String> cnameCol = new TableColumn<Customer, String>("Name");
		cnameCol.setMinWidth(100);
		cnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("cname"));

		cnameCol.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
		cnameCol.setOnEditCommit((CellEditEvent<Customer, String> t) -> {
			((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCname(t.getNewValue()); // display
																													// only
			updateName(t.getRowValue().getCid(), t.getNewValue());
		});

		TableColumn<Customer, String> jobCol = new TableColumn<Customer, String>("Job");
		jobCol.setMinWidth(50);
		jobCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("Job"));

		jobCol.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());

		jobCol.setOnEditCommit((CellEditEvent<Customer, String> t) -> {
			((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setJob(t.getNewValue());
			updateJob(t.getRowValue().getCid(), t.getNewValue());
		});

		TableColumn<Customer, String> genderCol = new TableColumn<Customer, String>("Gender");
		genderCol.setMinWidth(100);
		genderCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("Gender"));

		genderCol.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
		genderCol.setOnEditCommit((CellEditEvent<Customer, String> t) -> {
			((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGender(t.getNewValue());
			updateGender(t.getRowValue().getCid(), t.getNewValue());
		});

		TableColumn<Customer, String> addressCol = new TableColumn<Customer, String>("Address");
		addressCol.setMinWidth(100);
		addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("Address"));

		addressCol.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
		addressCol.setOnEditCommit((CellEditEvent<Customer, String> t) -> {
			((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress(t.getNewValue());
			updateAddress(t.getRowValue().getCid(), t.getNewValue());
		});

		TableColumn<Customer, Date> dateCol = new TableColumn<Customer, Date>("Date Of Birth");
		dateCol.setMinWidth(100);
		dateCol.setCellValueFactory(new PropertyValueFactory<Customer, Date>("date_of_birth"));

		dateCol.setCellFactory(TextFieldTableCell.<Customer, Date>forTableColumn(new DateStringConverter()));
		dateCol.setOnEditCommit((CellEditEvent<Customer, Date> t) -> {
			((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setDate_of_birth(t.getNewValue());
			updateDate(t.getRowValue().getCid(), t.getNewValue());
		});

		TableColumn<Customer, String> licenseCol = new TableColumn<Customer, String>("License");
		licenseCol.setMinWidth(100);
		licenseCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("license_type"));

		licenseCol.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
		licenseCol.setOnEditCommit((CellEditEvent<Customer, String> t) -> {
			((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setLicense_type(t.getNewValue());
			updateLicense(t.getRowValue().getCid(), t.getNewValue());
		});

		myDataTable.setItems(dataList);

		myDataTable.getColumns().addAll(cidCol, cnameCol, jobCol, genderCol, addressCol, dateCol, licenseCol);

		final TextField addcid = new TextField();
		addcid.setPromptText("Cid");
		addcid.setMaxWidth(cidCol.getPrefWidth());

		final TextField addName = new TextField();
		addName.setMaxWidth(cnameCol.getPrefWidth());
		addName.setPromptText("Name");

		final TextField addjob = new TextField();
		addjob.setMaxWidth(jobCol.getPrefWidth());
		addjob.setPromptText("Job");

		final TextField addgender = new TextField();
		addgender.setMaxWidth(genderCol.getPrefWidth());
		addgender.setPromptText("Gender");

		final TextField addaddress = new TextField();
		addaddress.setMaxWidth(addressCol.getPrefWidth());
		addaddress.setPromptText("Address");

		final TextField adddate = new TextField();
		adddate.setMaxWidth(dateCol.getPrefWidth());
		adddate.setPromptText("Date");

		final TextField addlicense = new TextField();
		addlicense.setMaxWidth(licenseCol.getPrefWidth());
		addlicense.setPromptText("License");

		final Button addButton = new Button("Add");
		addButton.setOnAction((ActionEvent e) -> {
			Customer rc;
			rc = new Customer(Integer.valueOf(addcid.getText()), addName.getText(), addjob.getText(),
					addgender.getText(), addaddress.getText(), StringToDate(adddate.getText()), addlicense.getText());

			dataList.add(rc);
			insertData(rc);
			addcid.clear();
			addName.clear();
			addjob.clear();
			addgender.clear();
			addaddress.clear();
			adddate.clear();
			addlicense.clear();
		});

		final HBox hb = new HBox();

		final Button deleteButton = new Button("Delete");
		deleteButton.setOnAction((ActionEvent e) -> {
			ObservableList<Customer> selectedRows = myDataTable.getSelectionModel().getSelectedItems();
			ArrayList<Customer> rows = new ArrayList<>(selectedRows);
			rows.forEach(row -> {
				myDataTable.getItems().remove(row);
				deleteRow(row);
				myDataTable.refresh();
			});
		});

		hb.getChildren().addAll(addcid, addName, addjob, addgender, addaddress, adddate, addlicense, addButton,
				deleteButton);
		hb.setSpacing(3);

		final Button refreshButton = new Button("Refresh");
		refreshButton.setOnAction((ActionEvent e) -> {
			myDataTable.refresh();
		});

//		Button ownedNoneButton = new Button("Owned None");
//		ownedNoneButton.setOnAction(c -> );

		final Button clearButton = new Button("Clear All");
		clearButton.setOnAction((ActionEvent e) -> {
			showDialog(stage, NONE, myDataTable);

		});

		final HBox hb2 = new HBox();
		hb2.getChildren().addAll(clearButton, refreshButton);
		hb2.setAlignment(Pos.CENTER_RIGHT);
		hb2.setSpacing(3);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(label, myDataTable, hb, hb2);

		((Group) scene.getRoot()).getChildren().addAll(vbox);
		stage.setScene(scene);
	}

	private void insertData(Customer rc) {

		try {
			System.out.println("Insert into customer (cid, cname, job, gender, addres, date, license type) values("
					+ rc.getCid() + ",'" + rc.getCname() + "','" + rc.getJob() + "'," + rc.getGender() + ", '"
					+ rc.getAddress() + "')" + rc.getDate_of_birth() + rc.getLicense_type());

			connectDB();
			ExecuteStatement("Insert into customer (cid, cname, job, gender, addres, date, license type) values("
					+ rc.getCid() + ",'" + rc.getCname() + "','" + rc.getJob() + "'," + rc.getGender() + ", '"
					+ rc.getAddress() + "')" + rc.getDate_of_birth() + rc.getLicense_type());
			con.close();
			System.out.println("Connection closed" + data.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void getData() throws SQLException, ClassNotFoundException {

		String SQL;

		connectDB();
		System.out.println("Connection established");

		SQL = "select cid,cname,job,gender,address,date_of_birth,license_type from customer order by cid";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next())
			data.add(new Customer(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), StringToDate(rs.getString(6)), rs.getString(7)));

		rs.close();
		stmt.close();

		con.close();
		System.out.println("Connection closed" + data.size());

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

	public void updateName(int cid, String name) {

		try {
			System.out.println("update customer set cname = '" + name + "' where snum = " + cid);
			connectDB();
			ExecuteStatement("update customer set cname = '" + name + "' where snum = " + cid + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateJob(int cid, String job) {

		try {
			System.out.println("update customer set job = '" + job + "' where cid = " + cid);
			connectDB();
			ExecuteStatement("update customer set job = '" + job + "' where cid = " + cid + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateGender(int cid, String gender) {

		try {
			System.out.println("update customer set gender = '" + gender + "' where cid = " + cid);
			connectDB();
			ExecuteStatement("update customer set gender = '" + gender + "' where cid = " + cid + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateAddress(int cid, String address) {

		try {
			System.out.println("update customer set address = '" + address + "' where cid = " + cid);
			connectDB();
			ExecuteStatement("update customer set address = '" + address + "' where cid = " + cid + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateDate(int cid, Date dob) {

		try {
			System.out.println("update customer set date of birth = '" + dob + "' where cid = " + cid);
			connectDB();
			ExecuteStatement("update customer set date of birth = '" + dob + "' where cid = " + cid + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Date StringToDate(String s) {

		Date result = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			result = dateFormat.parse(s);
		}

		catch (ParseException e) {
			e.printStackTrace();

		}
		return result;
	}

	public void updateLicense(int cid, String lcs) {

		try {
			System.out.println("update customer set license type = '" + lcs + "' where cid = " + cid);
			connectDB();
			ExecuteStatement("update customer set license type = '" + lcs + "' where cid = " + cid + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void deleteRow(Customer row) {

		try {
			System.out.println("delete from  student where snum=" + row.getCid() + ";");
			connectDB();
			ExecuteStatement("delete from  student where snum=" + row.getCid() + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void showDialog(Window owner, Modality modality, TableView<Customer> table) {
		// Create a Stage with specified owner and modality
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
		// Label modalityLabel = new Label(modality.toString());

		Button yesButton = new Button("Confirm");
		yesButton.setOnAction(e -> {
			for (Customer row : dataList) {
				deleteRow(row);
				table.refresh();
			}
			table.getItems().removeAll(dataList);
			stage.close();

		});

		Button noButton = new Button("Cancel");
		noButton.setOnAction(e -> stage.close());

		HBox root = new HBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);

		root.getChildren().addAll(yesButton, noButton);
		Scene scene = new Scene(root, 200, 100);
		stage.setScene(scene);
		stage.setTitle("Confirm Delete?");
		stage.show();
	}

}*/
/*package application;



import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application{

    @FXML
    private Button log;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    void login_c(ActionEvent event ) throws IOException{
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
			Scene scene = new Scene (root, 910, 675);
			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
			st.setScene(scene);
			st.show();
    	
    	}
    	catch(Exception e){
    		
   
    	}

    }

}*/
package application;

import java.io.IOException;
import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Main extends Application {
	public static Stage s;
	public static Scene scene;
	public static Connection con;

	

	

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("login_c.fxml"));
		scene = new Scene(root, 588, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("log in");

		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.centerOnScreen();
	}

	public static void main(String[] args) {
		launch(args);
	}
}


