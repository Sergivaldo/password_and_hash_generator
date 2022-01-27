package application.controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.model.Letters;
import application.model.Numbers;
import application.model.Symbols;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.util.Duration;

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
	private RadioButton radio_8;
	
	@FXML
	private RadioButton radio_16;
	
	@FXML
	private Label message_label;
	
	@FXML
	private ToggleGroup length_group;
	
	@FXML
	private CheckBox check_number;
	
	@FXML
	private CheckBox check_symbol;
	
	@FXML
	private CheckBox check_upper;
	
	@FXML
	private CheckBox check_lower;
	
	private StringBuilder builder_pass = new StringBuilder();

	
	/**
	 * Array que contém a quantidade de caracteres de cada tipo.<br>
	 * <b>0</b> - contador de caracteres de letras maiusculas.<br>
	 * <b>1</b> - contador de caracteres de letras minusculas.<br>
	 * <b>2</b> - contador de caracteres de símbolos.<br>
	 * <b>3</b> - contador de caracteres de números.<br>
	 */
	private int[] count_chars = new int[4];
	
	private int current_length, max_length;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		select_max_lenght(null);
	}
	/**
	 * Verifica qual RadioButton está selecionado e atribui o seu valor ao<br>
	 * tamanho máximo da senha.
	 * @param e - Evento.
	 */
	@FXML
	public void select_max_lenght(ActionEvent e) {
		RadioButton radio = (RadioButton) length_group.getSelectedToggle();
		max_length = Integer.parseInt(radio.getText());
	}
	
	/**
	 * Verifica as <b>CheckBox</b> que estão marcadas e incrementa 1 ao seu respectivo contador e ao tamanho atual da senha.
	 * O inverso acontecerá se a <b>CheckBox</b> for desmarcada.<br>
	 * Quando uma <b>CheckBox</b> é marcada, é garantido que ao menos 1 caractere do tipo selecionado será<br>
	 * adicionado a senha.
	 * 
	 * @param e - Evento
	 */
	public void select_chars(ActionEvent e) {
		CheckBox checkbox = (CheckBox) e.getSource();
		if (checkbox.isSelected()) {
			switch (checkbox.getId()) {
			case "check_upper":
				count_chars[0]++;
				current_length++;
				break;
			case "check_lower":
				count_chars[1]++;
				current_length++;
				break;
			case "check_symbol":
				count_chars[2]++;
				current_length++;
				break;
			case "check_number":
				count_chars[3]++;
				current_length++;
				break;
			}
		} else {
			switch (checkbox.getId()) {
			case "check_upper":
				count_chars[0]--;
				current_length--;
				break;
			case "check_lower":
				count_chars[1]--;
				current_length--;
				break;
			case "check_symbol":
				count_chars[2]--;
				current_length--;
				break;
			case "check_number":
				count_chars[3]--;
				current_length--;
				break;
			}
		}

	}
	
	/**
	 * Chama os métodos <b>size_increment</b> 
	 * @param e - Evento.
	 */
	@FXML
	public void generate(ActionEvent e) {

		int i = 0;
		while (current_length < max_length) {
			if (i >= count_chars.length)
				i = 0;

			if (count_chars[i] > 0)
				count_chars[i] = size_increment(count_chars[i]);

			if (i < count_chars.length) {
				i++;
			}
		}

		for (int j = 0; j < count_chars.length; j++) {
			add_char(count_chars[j], j);
		}
		password_text.setText(builder_pass.toString());
		System.out.println(builder_pass.toString());
		reset();
	}

	private void add_char(int n_char, int type_char) {
		for (int i = 0; i < n_char; i++) {
			switch (type_char) {
			case 0:
				builder_pass.append(Letters.getRandomUpper());
				break;
			case 1:
				builder_pass.append(Letters.getRandomLower());
				break;
			case 2:
				builder_pass.append(Symbols.getRandomSymbol());
				break;
			case 3:
				builder_pass.append(Numbers.getRandomNumber());
				break;
			}
		}
	}

	private void reset() {
		builder_pass.delete(0, builder_pass.length());
		current_length = 0;
		for (int i = 0; i < count_chars.length; i++) {
			if (count_chars[i] > 0) {
				count_chars[i] = 1;
				current_length += count_chars[i];
			}
		}
	}

	private int size_increment(int n_char) {
		if (current_length < max_length) {
			int max_chars = max_length - current_length;
			int random_value = new Random().nextInt((max_chars - 1) + 1) + 1;
			current_length += random_value;
			return n_char + random_value;
		} else {
			return n_char;
		}

	}

	@FXML
	private void copy(ActionEvent event) throws InterruptedException {
		String password = password_text.getText();
		clipboard = Clipboard.getSystemClipboard();
		content = new ClipboardContent();
		content.putString(password);
		clipboard.setContent(content);
		show_copy_message();

	}

	private void show_copy_message() {
		message_label.setVisible(true);
		message_label.setText("Copy to Clipboard!");
		PauseTransition transition = new PauseTransition(Duration.millis(2000));
		transition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				message_label.setVisible(false);
				message_label.setText("");
			}

		});
		transition.play();
	}

}
