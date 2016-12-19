package de.andhillgames.ahglauncher.classes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Minecraft {

	@SuppressWarnings("deprecation")
	public static String[] getAccsessToken(String username, String passoword, String clientToken) {
		// Init accessdata array
		String[] accessdata = new String[5];
		// Server URL
		String authURL = "https://authserver.mojang.com/authenticate";
		
		try {
		
		URL url = new URL(authURL);
		byte[] rawData = buildPayLoad().getBytes("UTF-8");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setUseCaches(false);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setConnectTimeout(15000);
		conn.setReadTimeout(15000);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setRequestProperty("Content-Length", rawData.length + "");
		conn.setRequestProperty("Content-Language", "en-US");
		
		
		DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
		writer.write(rawData);
		writer.flush();
		writer.close();
		
		InputStream stream = null;
        String returnable = null;
		try {
			stream = conn.getInputStream();
            returnable = IOUtils.toString(stream, Charsets.UTF_8);
		} catch (IOException e) {
			stream = conn.getErrorStream();

			if (stream == null) {
				throw e;
			}
		} finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e) {}
        }
        
        stream.close();
        conn.disconnect();
        
        JSONParser parser = new JSONParser();
        try {
			Object obj = parser.parse(returnable);
			JSONObject root = (JSONObject) obj;
			JSONObject userid = (JSONObject) root.get("user");
			JSONObject selectedProfile = (JSONObject) root.get("selectedProfile");
			accessdata[0] = (String) root.get("accessToken");		// Access Token
			accessdata[1] = (String) selectedProfile.get("id");		// Profile ID
			accessdata[2] = (String) selectedProfile.get("name");	// Minecraft User
			accessdata[3] = (String) userid.get("id");				// User ID
			accessdata[4] = "";
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return accessdata;
	}
	
	public static void refresh(String accessToken, String clientToken) {
		
	}
	
	public static String makeDownloadLink() {
		String output = "";
		return output; 
	}
	
	public static String buildPayLoad() {
		String payload = "";
		payload = "{\"agent\":{\"name\":\"Minecraft\",\"version\":1},\"username\":\""+ConfigHandler.userEmail+"\",\"password\":\""+ConfigHandler.userPass+"\",\"clientToken\":\""+ConfigHandler.clientToken+"\",\"requestUser\":true}";
		return payload;
	}
	
}