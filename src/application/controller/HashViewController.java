package application.controller;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.util.Duration;

public class HashViewController implements Initializable {
	private Clipboard clipboard;
	private ClipboardContent content;
	@FXML
	private ComboBox<String> hash_combo;

	@FXML
	private TextArea text_area;

	@FXML
	private Label message_lbl;

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
		ObservableList<String> obs_combo_itens = FXCollections.observableArrayList("MD2", "MD5", "SHA-1", "SHA-256",
				"SHA-386", "SHA-512");
		hash_combo.setItems(obs_combo_itens);
	}

	@FXML
	public void copy(ActionEvent event) {
		String hash = hash_lbl.getText();
		clipboard = Clipboard.getSystemClipboard();
		content = new ClipboardContent();
		content.putString(hash);
		clipboard.setContent(content);
		show_message("Copy to Clipboard!");
	}

	@FXML
	public void generate(ActionEvent event) {
		String hash = getHash(text_area.getText().getBytes(), hash_combo.getValue());
		hash_lbl.setText(hash);
	}
	
	private void show_message(String message) {
		message_lbl.setVisible(true);
		message_lbl.setText(message);
		PauseTransition transition =  new PauseTransition(Duration.millis(2000));
		transition.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				message_lbl.setVisible(false);
				message_lbl.setText("");
			}
			
		});
		transition.play();
	}
	
	private String getHash(byte[] input_bytes, String function) {
		String hash = "";
		try {
			MessageDigest md = MessageDigest.getInstance(function);
			md.update(input_bytes);
			byte[] digestedBytes = md.digest();
			final BigInteger number = new BigInteger(1, digestedBytes);
			hash = number.toString(16);
		} catch (NoSuchAlgorithmException e) {

		}
		return hash;
	}

}
