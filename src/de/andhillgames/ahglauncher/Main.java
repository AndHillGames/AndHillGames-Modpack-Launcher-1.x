package de.andhillgames.ahglauncher;
	
import java.io.File;

import de.andhillgames.ahglauncher.classes.ConfigHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	public static final String APPNAME = "AndHillGames Modpack Launcher";
	public static final String APPVERSION = "1.0.0";
	public static final String APPBUILD = "10";
	public static final String MODPACKNAME = "AndHillTech";
	public static final String MODPACKVERSION = "1.0";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,1000,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(APPNAME+" V"+APPVERSION+" BUILD "+APPBUILD);
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("appicon.png")));
			primaryStage.show();
			String Path = new File("").getAbsolutePath()+"\\";
			File jsonFile = new File(Path+"settings.json");
			if (jsonFile.exists() && jsonFile.isFile()) {
				ConfigHandler.loadConfig();
			}
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            @Override
	            public void handle(WindowEvent t) {
	            	MainController.timer1.cancel();		// Canceling the timer
	                Platform.exit();
	                System.exit(0);
	            }
	});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
