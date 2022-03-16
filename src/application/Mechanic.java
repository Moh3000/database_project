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


public class Mechanic implements Initializable {

	private static String jid;

	public String getJid() {
		return jid;

	}

	public static void setId(String id) {

		Mechanic.jid = id;

	}

    	@FXML
    	private Label welcome;

	    @FXML
	    private Button car;

	    @FXML
	    private Button services;

	    @FXML
	    private Button reports;

	    @FXML
	    private Button backb;

	   
	    
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

	    @FXML
	    void car_a(ActionEvent event) {
	    	Parent root1;
    		try {
//				Service.setService_id(jid);
    			root1 = FXMLLoader.load(getClass().getResource("choose.fxml"));
    			Scene scenec = new Scene (root1, 700, 370);
    			Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
    			sts.setScene(scenec);
    			sts.setTitle("CAR MENU");
    			sts.show();
    			
    		} catch (IOException e) {
    		
    		}
	    }
	    

	    @FXML
	    void reports_a(ActionEvent event) {
	    	Parent root1;
    		try {
//				Service.setService_id(jid);
    			root1 = FXMLLoader.load(getClass().getResource("report.fxml"));
    			Scene scenec = new Scene (root1, 700, 370);
    			Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
    			sts.setScene(scenec);
    			sts.setTitle("report");
    			sts.show();
    			
    		} catch (IOException e) {
    		
    		}
	    }

	    @FXML
	    void services_a(ActionEvent event) {
	    	Parent root1;
    		try {
				//Service.setService_id(jid);
    			root1 = FXMLLoader.load(getClass().getResource("allServices.fxml"));
    			Scene scenec = new Scene (root1, 700, 370);
    			Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
    			sts.setScene(scenec);
    			sts.setTitle("services");
    			sts.show();
    			
    		} catch (IOException e) {
    		
    		}
    		
    		
    		
	    }
	
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
}