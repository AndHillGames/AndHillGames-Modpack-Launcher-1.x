package de.andhillgames.ahglauncher.dialogs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import de.andhillgames.ahglauncher.classes.ConfigHandler;
import de.andhillgames.ahglauncher.classes.Minecraft;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController {
	
	@FXML TextField javapath;
	@FXML TextField useremail;
	@FXML PasswordField userpass;
	@FXML ComboBox<String> ramusage;
	@FXML CheckBox updatecheck;
	@FXML CheckBox installupdates;
	@FXML Button closebutton;
	
	public void initialize() {
		ramusage.getItems().addAll("1 GigaByte", "2 GigaByte", "3 GigaByte", "4 GigaByte (recommended)", "5 GigaByte", "6 GigaByte");
		ramusage.getSelectionModel().select(3);
		
		String Path = new File("").getAbsolutePath()+"\\";
		File jsonFile = new File(Path+"settings.json");
		if (jsonFile.exists() && jsonFile.isFile()) {
			loadValues();
		}
	}
	
	public void loadValues() {
		javapath.setText(ConfigHandler.javaPath);
		useremail.setText(ConfigHandler.userEmail);
		userpass.setText(ConfigHandler.userPass);
		ramusage.getSelectionModel().select(ConfigHandler.ramUsage);
		if (ConfigHandler.updateCheck == true) {
			updatecheck.setSelected(true);
		}
		if (ConfigHandler.installUpdates == true) {
			installupdates.setSelected(true);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void saveValues() {
		// Get the values from fields
		ConfigHandler.javaPath = javapath.getText();
		ConfigHandler.userEmail = useremail.getText();
		ConfigHandler.userPass = userpass.getText();
		ConfigHandler.ramUsage = ramusage.getSelectionModel().getSelectedIndex();
		ConfigHandler.updateCheck = updatecheck.isSelected();
		ConfigHandler.installUpdates = installupdates.isSelected();
		
		// Checks if the access token is available. If not get it!
		if(ConfigHandler.accessToken.isEmpty()) {
			String[] newAccessData = new String[4];
			newAccessData = Minecraft.getAccsessToken(ConfigHandler.userEmail, ConfigHandler.userPass, ConfigHandler.clientToken);			
			ConfigHandler.accessToken = newAccessData[0];
			ConfigHandler.profileID = newAccessData[1];
			ConfigHandler.minecraftUser = newAccessData[2];
			ConfigHandler.twitchToken = newAccessData[3];
		}
		
		JSONObject root = new JSONObject();
		root.put("javapath", ConfigHandler.javaPath);
		root.put("useremail", ConfigHandler.userEmail);
		root.put("userpass", ConfigHandler.userPass);
		root.put("ramusage", new Integer(ConfigHandler.ramUsage));
		root.put("updatecheck", new Boolean(ConfigHandler.updateCheck));
		root.put("installupdates", new Boolean(ConfigHandler.installUpdates));
		root.put("accesstoken", ConfigHandler.accessToken);
		root.put("profileid", ConfigHandler.profileID);
		root.put("minecraftuser", ConfigHandler.minecraftUser);
		root.put("twitchtoken", ConfigHandler.twitchToken);
		root.put("clienttoken", ConfigHandler.clientToken);
		
		
		try {
			String Path = new File("").getAbsolutePath()+"\\";
			FileWriter file = new FileWriter(Path+"settings.json");
			file.write(root.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeForm();
	}
	
	@FXML public void closeForm() {
	    Stage stage = (Stage) closebutton.getScene().getWindow();
	    stage.close();
	}
}