package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class login_c {
	public static Connection con;
    @FXML
    private Button log;

    @FXML
    private TextField password;

    @FXML
    private TextField username;
    @FXML
    private Button employee;
    @FXML
	  private Label wid;

	    @FXML
	    private Label wpass;
  
   
    @FXML
    private Label up;
      
      
 
      @FXML
    void up_u(MouseEvent event) {
    	Parent root1;
		try {
			
			root1 = FXMLLoader.load(getClass().getResource("sign_up.fxml"));
			Scene scenec = new Scene (root1, 400, 600);
			Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
			sts.setScene(scenec);
			sts.setTitle("sign up");
		
			sts.show();
			
		
		} catch (IOException e) {
		
		}
    	
    }

    @FXML
    void employe_e(ActionEvent event) {
    	Parent root1;
		try {
			
			root1 = FXMLLoader.load(getClass().getResource("logemp_c.fxml"));
			Scene scenec = new Scene (root1, 600, 400);
			Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
			sts.setScene(scenec);
			sts.setTitle("log in as employe");
		
			sts.show();
		} catch (IOException e) {
		
		}
    	

    }

    @FXML
    void login_c(final ActionEvent event ) throws IOException, ClassNotFoundException, SQLException{
    	
    	try {
    	wpass.setVisible(false);
    	wid.setVisible(false);
    	
		
    	if(!(username.getText().contentEquals("") || password.getText().contentEquals("") )){
    		
    		if(username.getText().matches("[0-9]+")) {
    			
    	ResultSet rs = CRDB.select(
				"select * from customer where cid="+username.getText()+" and pass LIKE md5('"+password.getText()+"');");
   
    	if(rs.next()) { 
    		
    		
    
        	Parent root1;
    		try {
    			
    			booking.setId(username.getText());
    			booking.setStat(false);    			
    			root1 = FXMLLoader.load(getClass().getResource("booking.fxml"));
    			

    			Scene scenec = new Scene (root1, 600, 400);
    			Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
    			sts.setScene(scenec);
    			sts.setTitle("booking");
    		
    			sts.show();
    		} catch (IOException e) {
    		
    		}
    	
    	
    	
    
    	

    
    }else {
    	ResultSet rs0 = CRDB.select(
				"select * from customer where cid="+username.getText()+";");
    	if(rs0.next()) {
    		wpass.setVisible(true);
    		username.setText(null);
    		password.setText(null);
    		
    	}else {
    		wid.setVisible(true);
    		username.setText(null);
    		password.setText(null);
    	}
    }}else {
    	wid.setVisible(true);
    	username.setText(null);
		password.setText(null);
    }
    	}else {
    	       	wid.setVisible(true);
        	username.setText(null);
    		password.setText(null);
    	}}catch(Exception e){
    		
    		
   
    	}

}}

