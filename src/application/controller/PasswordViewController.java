package application.controller;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

public class PasswordViewController implements Initializable {
	private Clipboard clipboard;
	private ClipboardContent content;

	@FXML
	private Label password_text;

	@FXML
	private Button gen_btt;

	@FXML
	private Button copy_btt;

	@FXML
	private RadioButton radio_4;

	@FXML
	private Label message_label;

	@FXML
	private ToggleGroup length_group;

	@FXML
	private RadioButton radio_8;

	@FXML
	private RadioButton radio_16;

	@FXML
	private Button close_btt;
	@FXML
	private CheckBox check_number;
	@FXML
	private CheckBox check_symbol;

	@FXML
	private CheckBox check_upper;

	@FXML
	private CheckBox check_lower;

	private StringBuilder builder_pass = new StringBuilder();

	private String password;
	/**
	 * índice 0 = maíusculas;
	 * índice 1 = minúsculas;
	 * índice 2 = símbolos;
	 * índice 3 = números;
	 * */
	private int[] count_chars = new int[4];
	
	private int length,max_length;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		select_maxLenght(null);
		
	}
	
	@FXML
	public void create_pass() {

	}
	
	@FXML
	public void select_maxLenght(ActionEvent event) {
		RadioButton radio = (RadioButton) length_group.getSelectedToggle();
		max_length = Integer.parseInt(radio.getText());
		System.out.println(max_length);
	}
	
	public void select_chars(ActionEvent e) {
		
		CheckBox checkbox = (CheckBox) e.getSource();
		if (checkbox.isSelected()) {
			switch (checkbox.getId()) {
				case "check_upper":
					count_chars[0]++;
					break;
				case "check_lower":
					count_chars[1]++;
					break;
				case "check_symbol":
					count_chars[2]++;
					break;
				case "check_number":
					count_chars[3]++;
					break;
			}

		} else {
			
			switch (checkbox.getId()) {
				case "check_upper":
					count_chars[0]--;
					break;
				case "check_lower":
					count_chars[1]--;
					break;
				case "check_symbol":
					count_chars[2]--;
					break;
				case "check_number":
					count_chars[3]--;
					break;
			}
		}
		
		System.out.println(count_chars[0]+" "+count_chars[1]+" "+count_chars[2]+" "+count_chars[3]);
	}
	
	
	public void generate() {
		for(int character:count_chars) {
			if(character > 0)
				add_char(0,0);
		}
	}
	
	private void add_char(int n_char, int lenght_pass) {
		
	}
	
	

	@FXML
	private void copy(ActionEvent event) throws InterruptedException {
		String password = password_text.getText();
		clipboard = Clipboard.getSystemClipboard();
		content = new ClipboardContent();
		content.putString(password);
		clipboard.setContent(content);
		message_label.setText("Copy to Clipboard!");
	}

	

}
