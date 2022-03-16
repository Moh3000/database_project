package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class logemp_c {

    @FXML
    private Button logemp;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label wid;

    @FXML
    private Label wpass;
    @FXML
    private Button back;
    @FXML
   
    void logemp_c(ActionEvent event) throws ClassNotFoundException, SQLException {
   
    	try {
    		
    		wpass.setVisible(false);
    	wid.setVisible(false);
    	
		
    	
		if(!(username.getText().contentEquals("") || password.getText().contentEquals(""))){
    		
    		if(username.getText().matches("[0-9]+")) {
    			
    	ResultSet rs = CRDB.select(
				"select * from employees where jid="+username.getText()+" and pass LIKE md5("+"'"+password.getText()+"'"+");");
    	
    	if(rs.next()) { 
    	
    		ResultSet rsd = CRDB.select(
    				"select * from driver where jid="+username.getText()+";");
    		if(rsd.next()) {
    	Parent root = FXMLLoader.load(getClass().getResource("driver_TEST.fxml"));
			Scene scene = new Scene (root, 600, 400);
			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
			st.setScene(scene);
			st.setTitle("driver");
			
			st.show();}else {
				ResultSet rsa = CRDB.select(
	    				"select * from accountant where jid="+username.getText()+";");
	    		if(rsa.next()) {
	    		
	    	Parent root = FXMLLoader.load(getClass().getResource("Accountantui.fxml"));
				Scene scene = new Scene (root, 800, 500);
				Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
				st.setScene(scene);
				st.setTitle("accountant");
				
				st.show();}else {
					ResultSet rsm = CRDB.select(
		    				"select * from mechanic where jid="+username.getText()+";");
		    		if(rsm.next()) {
		    	Parent root = FXMLLoader.load(getClass().getResource("Mechanic.fxml"));
					Scene scene = new Scene (root, 600, 400);
					Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
					st.setScene(scene);
					st.setTitle("mechanic");
					
					st.show();}else {
						ResultSet rsma = CRDB.select(
			    				"select * from manager where jid="+username.getText()+";");
			    		if(rsma.next()) {
			    	Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
						Scene scene = new Scene (root, 600, 400);
						Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
						st.setScene(scene);
						st.setTitle("manager");
						
						st.show();}
					}
				}
				
			}
    		
    	
    	
    	
    	
    
    	

    
    }else {
    	ResultSet rs0 = CRDB.select(
				"select * from employees where jid="+username.getText()+";");
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


}

    @FXML
    void back_k(ActionEvent event) {
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

    }}
