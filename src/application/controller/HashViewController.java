package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HashViewController implements Initializable {

    @FXML
    private ComboBox<String> hash_combo;

    @FXML
    private TextField text_lbl;

    @FXML
    private Label hash_lbl;

    @FXML
    private Button gen_btt;

    @FXML
    private Button copy_btt;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		load_combo_itens();
	}
    
    private void load_combo_itens() {
    	ObservableList<String> obs_combo_itens = FXCollections.observableArrayList("MD2","MD5","SHA-1","SHA-256","SHA-386","SHA-512");
    	hash_combo.setItems(obs_combo_itens);
    }
    
    
    
    @FXML
    public void copy(ActionEvent event) {

    }

    @FXML
    public void create_pass(ActionEvent event) {

    }

	
    
}
