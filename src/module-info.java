module password_generator {
	exports application;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application;
	opens application.controller;
	opens application.model;
	opens application.controller.util;
}