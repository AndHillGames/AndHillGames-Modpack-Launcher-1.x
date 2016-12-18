package de.andhillgames.ahglauncher.classes;

public class Minecraft {

	public static String[] getAccsessToken(String username, String passoword, String clientToken) {
		String[] accessdata = new String[4];
		accessdata[0] = "";	// Access Token
		accessdata[1] = ""; // Profile ID
		accessdata[2] = ""; // Minecraft User
		accessdata[3] = "not available"; // Twitch Token
		return accessdata;
	}
	
	public static void refresh(String accessToken, String clientToken) {
		
	}
	
	public static String makeDownloadLink() {
		String output = "";
		return output; 
	}
	
}