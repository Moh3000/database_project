package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class sign_up {
	
	
	  @FXML
	    private Button back;
	@FXML
    private TextField name;

    @FXML
    private TextField job;

    @FXML
    private TextField address;
    @FXML
    private PasswordField passwoed;

    @FXML
    private TextField d;

    @FXML
    private TextField m;

    @FXML
    private TextField y;

    @FXML
    private TextField number;

    @FXML
    private ToggleGroup ss;


    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;
ObservableList<String>license=(ObservableList<String>) FXCollections.observableArrayList("automatic","manual");
    @FXML
    private Button Reg;
    @FXML
    private ComboBox tyep;
    
    @FXML
    private Label error;
    
    @FXML 
private void initialize(){
	tyep.setValue("automatic");
	tyep.setItems(license);
	tyep.setValue("manual");
	tyep.setItems(license);
}

@FXML
void type_e(ActionEvent event) {
	
}
    @FXML
    void reg_g(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    	if (number.getText().isEmpty() || !(number.getText().matches("[0-9]+"))|| passwoed.getText().isEmpty() || name.getText().isEmpty()
				|| job.getText().isEmpty() || y.getText().isEmpty() ||!(y.getText().matches("[0-9]+"))|| m.getText().isEmpty()
				|| !(m.getText().matches("[0-9]+"))||!(d.getText().matches("[0-9]+"))|| d.getText().isEmpty()) {
			error.setVisible(true);
			return;
		}

    	try{
    		
    		ss=new ToggleGroup();
    	this.female.setToggleGroup(ss);
    	this.male.setToggleGroup(ss);
    	RadioButton S = (RadioButton) ss.getSelectedToggle();

    	CRDB.execute("INSERT INTO customer "+"(phn, pass, cname, job,gender,address,date_of_birth,license_type)"+" values (" + number.getText() + ",md5('" +passwoed.getText()+"'),'" +name.getText()+ "','"+ job.getText() + "','"
				+ S.getText() + "','" + address.getText() + "','" +y.getText()+"-"+ m.getText()+ "-"+d.getText()+ "','"
				+ tyep.getValue() + "')"+";");
    	
    	
	
    	
    } catch (SQLException e) {

	
	}

		booking.setStat(true);
		Parent root1 = FXMLLoader.load(getClass().getResource("booking.fxml"));
		Scene scenec = new Scene (root1, 600, 400);
		Stage sts = (Stage) (((Node) event.getSource()).getScene().getWindow());
		sts.setScene(scenec);
		sts.setTitle("booking");
	
		sts.show();

    }
   
  

    @FXML
    void back_(ActionEvent event) {
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
}
