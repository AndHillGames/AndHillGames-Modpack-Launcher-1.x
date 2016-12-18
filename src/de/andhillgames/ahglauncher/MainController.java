package de.andhillgames.ahglauncher;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import de.andhillgames.ahglauncher.classes.AHGLibrary;
import de.andhillgames.ahglauncher.dialogs.SettingsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController {
	
	@FXML
	public WebView welcomePage;
	
	@FXML
	public TextArea consoleOutput;
	
	@FXML
	public Text dateLabel;
	
	@FXML
	public Text timeLabel;
	
	@FXML
	public Tab consoleTab;
	
	@FXML
	public TabPane tabPane;
	
	public static Timer timer1 = new Timer();
	public SimpleDateFormat dFormat = new SimpleDateFormat("dd.MM.YYYY");
	public SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm:ss");
	
	// Initialize some of the controls
	public void initialize() {
		
		// Define the start page of the welcome page
		WebEngine webEngine = welcomePage.getEngine();
		webEngine.setUserAgent(Main.APPNAME);
		webEngine.load("http://modpack.andhillgames.de");
		
		consoleOutput.clear();
		
		// Setting time and date
		dateLabel.setText(dFormat.format(new Date()));
		timeLabel.setText(tFormat.format(new Date()));
		
		timer1.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
	
				// Setting the new time and date every second
				dateLabel.setText(dFormat.format(new Date()));
				timeLabel.setText(tFormat.format(new Date()));
				
			}
			
		}, 1000, 1000);
		
	}
	
	// Method to close the application over a confirmation dialog
	public void DoExit() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit Application?");
		alert.setHeaderText(null);
		alert.initStyle(StageStyle.UTILITY);
		alert.setContentText("Are you sure that you want to exit the "+Main.APPNAME+" V"+Main.APPVERSION+" BUILD "+Main.APPBUILD+"?");
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && result.get() == ButtonType.OK) {
			timer1.cancel();
			System.exit(0);
		}
		
	}
	
	// Method to open up the information dialog
	public void ShowInformationDialog() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information About");
		alert.setHeaderText(Main.APPNAME+" V"+Main.APPVERSION+" BUILD "+Main.APPBUILD);
		alert.initStyle(StageStyle.UTILITY);
		alert.setContentText("Programmed and designed by Andreas Hiller\n"
				+ "Copyright © 2017 AndHillGames\n"
				+ "All Rights reserved\n\n"
				+ "Website: http://www.andhillgames.de\n"
				+ "E-Mail: andhillgames@gmx.de");
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && result.get() == ButtonType.OK) {
			alert.close();
		}
		
	}
	
	public void doInstall() {
		tabPane.getSelectionModel().select(consoleTab);
		addToConsole("Installing Minecraft ...");
	}
	
	public void addToConsole(String msg) {
		consoleOutput.setText(consoleOutput.getText()+msg+"\n");
	}
	
	public void openSettingsDialog() {
		AnchorPane root;
		Stage settings = new Stage();
		try {
			root = (AnchorPane)FXMLLoader.load(SettingsController.class.getResource("Settings.fxml"));
			Scene scene = new Scene(root,500,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			settings.setScene(scene);
			settings.setAlwaysOnTop(true);
			settings.setTitle("Settings");
			settings.setResizable(false);
			settings.initStyle(StageStyle.UTILITY);
			settings.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openHomepage() {
		AHGLibrary.openUrl("http://www.andhillgames.de");
	}
	
	
	
}