package de.andhillgames.ahglauncher.classes;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public class AHGLibrary {

	// Open a website with the default web browser
	public static void openUrl(String uri) {
		 Desktop d=Desktop.getDesktop();
		 try {
			d.browse(new URI(uri));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	// Random UUID erstellen
	public static String makeUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
}