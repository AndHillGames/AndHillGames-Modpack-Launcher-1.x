package de.andhillgames.ahglauncher.classes;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class DownloadHandler {
	
	public static boolean downloadList(int mode) {
		
		String modListUrl;
		switch (mode) {
			case 0: modListUrl = "http://modpack.andhillgames.de/lists/modlist.json"; break;
			case 1: modListUrl = "http://modpack.andhillgames.de/lists/assetlist.json"; break;
			case 2: modListUrl = "http://modpack.andhillgames.de/lists/dirlist.json"; break;	
			default: modListUrl = "http://modpack.andhillgames.de/lists/modlist.json";
		}
		
		try {
			URL url = new URL(modListUrl);
			File file = new File(ConfigHandler.Path+"modlist.json");
			FileUtils.copyURLToFile(url, file);
			return true;
		} catch(IOException e) {
			return false;
		}
	}
	
	public static void downloadFile(String filename, String destination) {
		
	}
	
}