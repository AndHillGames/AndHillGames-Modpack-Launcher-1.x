package de.andhillgames.ahglauncher.classes;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigHandler {
	
	public static String javaPath = "";
	public static String userEmail = "";
	public static String userPass = "";
	public static Integer ramUsage = 0;
	public static boolean updateCheck = false;
	public static boolean installUpdates = false;
	public static String clientToken = AHGLibrary.makeUUID();
	public static String profileID = "";
	public static String accessToken = "";
	public static String minecraftUser = "";
	public static String userID = "";
	public static String twitchToken = "";
	public static String Path = new File("").getAbsolutePath()+"\\";
	public static String ModpackPath = Path+"modpack\\";

	public static void loadConfig() {
		String Path = new File("").getAbsolutePath()+"\\";
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(Path+"settings.json"));
			JSONObject root = (JSONObject) obj;
			Long ram;
			javaPath = (String) root.get("javapath");
			userEmail = (String) root.get("useremail");
			userPass = (String) root.get("userpass");
			ram = (Long) root.get("ramusage");
			ramUsage = new Integer(ram.intValue());
			updateCheck = (boolean) root.get("updatecheck");
			installUpdates = (boolean) root.get("installupdates");
			clientToken = (String) root.get("clienttoken");
			profileID = (String) root.get("profileid");
			accessToken = (String) root.get("accesstoken");
			minecraftUser = (String) root.get("minecraftuser");
			userID = (String) root.get("userid");
			twitchToken = (String) root.get("twitchtoken");
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}