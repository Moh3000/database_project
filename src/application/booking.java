package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class booking implements Initializable{
	private static String id;
	private static String start;
	private static String end;
	private static boolean driv_stat;
	public static boolean isDriv_stat() {
		return driv_stat;
	}

	public static void setDriv_stat(boolean driv_stat) {
		booking.driv_stat = driv_stat;
	}

	public static String getStart() {
		return start;
	}

	public static void setStart(String start) {
		booking.start = start;
	}

	public static String getEnd() {
		return end;
	}

	public static void setEnd(String end) {
		booking.end = end;
	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		booking.id = id;
	}
	private static boolean stat;

	public static boolean isStat() {
		return stat;
	}

	public static void setStat(boolean stat) {
		booking.stat = stat;
	}
	@FXML
    private TextField yp;

    @FXML
    private TextField dd;

    @FXML
    private TextField md;

    @FXML
    private TextField yd;

    @FXML
    private TextField dp;

    @FXML
    private TextField mp;

    @FXML
    private RadioButton yes;

    @FXML
    private ToggleGroup ss;

    @FXML
    private RadioButton no;
    @FXML
    private Label errora;

    @FXML
    private Label errorb;

    @FXML
    private Label we;

    @FXML
    private Label yid;

    @FXML
    private Button backb;

    @FXML
    private Button done;
   
    

    @FXML
    void back_b(ActionEvent event) {
       	Parent root1;
    		try {
    			
    			root1 = FXMLLoader.load(getClass().getResource("login_c.fxml"));
    			Scene scenec = new Scene (root1, 600, 400);
    			Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
    			sts.setScene(scenec);
    			sts.setTitle("log in");
    		
    			sts.show();
    		} catch (IOException e) {
    		
    		}

        
    }

    @FXML
    void done_b(ActionEvent event) throws SQLException, ClassNotFoundException {
    	
    	if ( yp.getText().isEmpty() ||!(yp.getText().matches("[0-9]+"))|| mp.getText().isEmpty()
				|| !(mp.getText().matches("[0-9]+"))||!(dp.getText().matches("[0-9]+"))|| dp.getText().isEmpty()|| yd.getText().isEmpty() ||!(yd.getText().matches("[0-9]+"))|| md.getText().isEmpty()
				|| !(md.getText().matches("[0-9]+"))||!(dd.getText().matches("[0-9]+"))|| dd.getText().isEmpty()) {
			errorb.setVisible(true);
			return;
    	}
			if(	Integer.parseInt(yd.getText())<Integer.parseInt(yp.getText()))
			
			{
				errorb.setVisible(true);
				return;
    	}
			if(	Integer.parseInt(md.getText())<Integer.parseInt(mp.getText()))
				
			{
				errorb.setVisible(true);
				return;
    	}
if(	Integer.parseInt(dd.getText())<Integer.parseInt(dp.getText()))
				
			{
				errorb.setVisible(true);
				return;
    	}
    
    	
    	

    	try{
    		end = "'" +yd.getText()+"-"+ md.getText()+ "-"+dd.getText()+ "'";
        	start="'" +yp.getText()+"-"+ mp.getText()+ "-"+dp.getText()+ "'";
    		ss=new ToggleGroup();
    	this.yes.setToggleGroup(ss);
    	this.no.setToggleGroup(ss);
    	RadioButton S = (RadioButton) ss.getSelectedToggle();
    	
    	ResultSet rs =	CRDB.select("select c.vin, c.manufacturer_name, c.model, c.year_of_manufacture, c.Rental_price_per_day, c.fuel_efficiency, c.security_deposit, c.transmission_system, c.size, c.number_of_occupants, c.color " + " from car c where c.vin not in(select c.vin from car c, customer2car c2c, customer cs, booking b where c.vin = c2c.car_vin and c2c.cust_id = cs.cid and cs.cid = b.makes_cid and (( b.date_of_pickup >= " + start + " and b.date_of_pickup <= " + end + ") or (b.date_of_dropoff >= " + start + " and b.date_of_dropoff <= " + end + ") or (b.date_of_pickup <= " + start + " and b.date_of_dropoff >= " + end + ")) );");
    	if(!(rs.next())) {
errora.setVisible(true);
return;
    	}
    	
    	
	if(S.getText().equals("yes")) {
		
		ResultSet rs1 =	CRDB.select("select d.jid " + " from driver d where d.jid not in(select d.jid from driver d, cust2driver c2d, customer cs, booking b" + " where d.jid = c2d.eid and c2d.cid = cs.cid and cs.cid = b.makes_cid and (( b.date_of_pickup >= " + start + " and b.date_of_pickup <= " + end + ") or (b.date_of_dropoff >= " + start + " and b.date_of_dropoff <= " + end + ") or (b.date_of_pickup <= " + start+ " and b.date_of_dropoff >= " + end + ")) );");
	if(!(rs1.next()))
	 {
		
		errora.setVisible(true);
	return;}
	driv_stat=true;
	}
	
    	
    } catch (SQLException e) {

	
	}
    	
    	

    	Parent root1;
		try {
			
			Choosecontrol.setCid1(Integer.parseInt(id));
			root1 = FXMLLoader.load(getClass().getResource("choose.fxml"));
			Scene scenec = new Scene (root1, 1000, 500);
			Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
			sts.setScene(scenec);
			sts.setTitle("booking");
		
			sts.show();
		} catch (IOException e) {
		
		}
	
    	
    	
    }
    

    @FXML
    void type_e(ActionEvent event) {
 
    	
}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		errorb.setVisible(false);
	
ResultSet rs = null ;
		
if(stat) {
		try {
			rs = CRDB.select(
					"select * from  customer where cid =(select count(*) from  customer );");
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		try {
			if(rs.next()) 
			{
			id=rs.getString(1);
			we.setText("welcome Mr."+rs.getString(4));
			we.setVisible(true);
			yid.setText("This is your ID '"+rs.getString(1)+"', please save it to reuse it later");
			yid.setVisible(true);
			}
		} catch (SQLException e) {
			
		}}
else {
	try {
		rs = CRDB.select(
				"select * from  customer where cid ="+id+";");
	} catch (ClassNotFoundException | SQLException e) {
		
	}
	try {
		if(rs.next()) 
		{
		
		we.setText("welcome Mr."+rs.getString(4));
		we.setVisible(true);
	
		}
	} catch (SQLException e) {
		
	}
	
}
		
	}
	
  
}