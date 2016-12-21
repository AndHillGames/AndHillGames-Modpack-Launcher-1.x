package de.andhillgames.ahglauncher.dialogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.andhillgames.ahglauncher.MainController;
import de.andhillgames.ahglauncher.classes.ConfigHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class InstallMinecraftController {
	
	@FXML CheckBox checkList;
	@FXML CheckBox checkDirs;
	@FXML CheckBox checkMinecraft;
	@FXML CheckBox checkAssets;
	@FXML CheckBox checkMods;
	@FXML CheckBox checkLibraries;
	@FXML CheckBox checkBinaries;
	@FXML CheckBox checkBinariesExt;
	@FXML CheckBox checkLibrariesExt;

	public void initialize() {
		
		/*if (DownloadHandler.downloadList(0)) {
			if(DownloadHandler.downloadList(1)) {
				if(DownloadHandler.downloadList(2)) {
					
				} else {
					System.out.println("Geht nicht");
				}
			} else {
				System.out.println("Geht nicht");
			}
		} else {
			System.out.println("Geht nicht");
		}*/
		
		File file = new File(ConfigHandler.ModpackPath);
		if (file.exists() == false) {
			file.mkdir();
		}
		
		MainController.addToConsole("Installing Minecraft 1.7.10 ...");
		MainController.addToConsole("Creating Directories ...");
		createDirectories();
		
		
		System.out.println(getDirCount("dirlist.json").toString());
	}
	
	public void createDirectoriesFromJsonFile(String filename, String dirID) {
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(ConfigHandler.Path+filename));
			JSONObject root = (JSONObject) obj;
			JSONObject dir = (JSONObject)root.get("directories");
			JSONObject dirinfo = (JSONObject)dir.get(dirID);
			String pathname = (String)dirinfo.get("dirname");
			String rootname = (String)dirinfo.get("root");
			File file = new File(ConfigHandler.ModpackPath+rootname+pathname);
			MainController.addToConsole("Creating Directory: "+file.toString());
			if (file.exists() == false) {
				file.mkdirs();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer getDirCount(String filename) {
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(ConfigHandler.Path+filename));
			JSONObject root = (JSONObject) obj;
			Long dircount = (long)root.get("dircount");
			return new Integer(dircount.intValue());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void createDirectories() {
		Integer fileid = 1;
		for(int i = 1; i <= getDirCount("dirlist.json").intValue();i++) {
			try {
				
				fileid = (Integer)i;
				createDirectoriesFromJsonFile("dirlist.json",fileid.toString());
			} catch(Exception e) {
				
			}
		}
	}
	
}
