package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Driver implements Initializable {

//    public Driver(String jid, String jname, String gender, String address, Date date_of_birth, String license_type) {
//		super(jid, jname, gender, address, date_of_birth, license_type);
//		// TODO Auto-generated constructor stub
//	}

	private static String jid;

	public String getJid() {
		return jid;

	}

	public static void setId(String id) {

		Driver.jid = id;

	}
	public static String date_of_pickup;
	public static String date_of_drop_off;
	
    @FXML
    private Button details;
    
    @FXML
    void details_a(ActionEvent event) {

    }
	public static String getDate_of_pickup() {
		return date_of_pickup;
	}

	public static void setDate_of_pickup(String date_of_pickup) {
		Driver.date_of_pickup = date_of_pickup;
	}

	public static String getDate_of_drop_off() {
		return date_of_drop_off;
	}

	public static void setDate_of_drop_off(String date_of_drop_off) {
		Driver.date_of_drop_off = date_of_drop_off;
	}
	@FXML
	private Label welcome;

	@FXML
	private Button fBook;

	@FXML
	private Button sBook;

    @FXML
    private Label show;

    @FXML
    private Label salary;

	@FXML
	void fBookAction(ActionEvent event) {
		ResultSet rs = null;

		try {
			rs = CRDB.select("select * from employees where jid=" + jid + ";");
		} catch (ClassNotFoundException | SQLException e) {

		}
		try {
			if (rs.next()) {
				salary.setVisible(false);
				show.setText("Total Salary = " + rs.getString(7));
				show.setVisible(true);
			}
		} catch (SQLException e) {

		}
    	}
	

	@FXML
	void sBookAction(ActionEvent event) {
		ResultSet rs = null;

		try {
			rs = CRDB.select("select * from employees where jid=" + jid + ";");
		} catch (ClassNotFoundException | SQLException e) {

		}
		try {
			if (rs.next()) {
				show.setVisible(false);
				salary.setText("Total Salary = " + rs.getString(7));
				salary.setVisible(true);
			}
		} catch (SQLException e) {

		}
	}

	ResultSet rs = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ResultSet rs = null;

		try {
			rs = CRDB.select("select * from employees where jid=" + jid + ";");
		} catch (ClassNotFoundException | SQLException e) {

		}

		try {
			if (rs.next()) {

				welcome.setText("welcome Mr." + rs.getString(4));
				welcome.setVisible(true);
			}
		} catch (SQLException e) {

		}

	}

//		@Override
//		public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
//			rs = CRDB.select(
//					"select * from customer where cid="+id+";");
//	        welcome.setText("welcome Mr."+rs.getString(4));

	@FXML
	void back_b(ActionEvent event) {
		Parent root1;
		try {
			
			root1 = FXMLLoader.load(getClass().getResource("logemp_c.fxml"));
			Scene scenec = new Scene(root1, 600, 400);
			Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
			sts.setScene(scenec);
			sts.setTitle("log in");
			sts.show();
		} catch (IOException e) {

		}

	}

}

//    @FXML
//    void ok_raji(ActionEvent event) {
//    	
//    	if(raje.getText().equalsIgnoreCase("raje")) 
//    	{
//    		Parent root1;
//    		try {
//    		
//    			root1 = FXMLLoader.load(getClass().getResource("logemp_c.fxml"));
//    			
//    			Scene scenec = new Scene (root1, 600, 400);
//    			Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
//    			sts.setScene(scenec);
//    			sts.setTitle("logemp_c");
//    		
//    			sts.show();
//    		} catch (IOException e) {
//    		
//    		}
//    	
//    	
//    	}
//
//    }
