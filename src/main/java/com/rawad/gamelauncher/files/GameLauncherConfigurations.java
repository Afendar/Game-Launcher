package com.rawad.gamelauncher.files;

import java.io.File;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="config")
public class GameLauncherConfigurations extends DataContainer {
	
//	private static final String KEY_GAMES_DIR = "gamesDirectory";
	// KEY_UPDATE_WEBSITE <- Site to update launcher.
	// KEY_VERSION <- Current version of launcher.
	
	private File gamesDirectory;
	
	/**
	 * @return the gamesDirectory
	 */
	public File getGamesDirectory() {
		return gamesDirectory;
	}
	
	/**
	 * @param gamesDirectory the gamesDirectory to set
	 */
	public void setGamesDirectory(File gamesDirectory) {
		this.gamesDirectory = gamesDirectory;
	}
	
}
