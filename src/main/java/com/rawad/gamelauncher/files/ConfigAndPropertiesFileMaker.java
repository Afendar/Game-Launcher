package com.rawad.gamelauncher.files;

import java.io.File;

public class ConfigAndPropertiesFileMaker {
	
	public static void main(String... args) {
		
		GameLauncherConfigurations gameLauncherConfigs = new GameLauncherConfigurations();
		gameLauncherConfigs.setGamesDirectory(new File("Games"));
		
		FileLoader.saveGameLauncherConfigs(gameLauncherConfigs);
		
		GameProperties ballSimProperties = new GameProperties();
		ballSimProperties.setName("Ball Simulator");
		ballSimProperties.setVersion("1.0");
		ballSimProperties.setJar(new File("Ball Simulator.jar"));
		ballSimProperties.setIcon(new File("res/textures/game_icon.png"));
		
		FileLoader.saveGameProperties(ballSimProperties, new File(gameLauncherConfigs.getGamesDirectory().getPath() 
				+ File.separatorChar + "Ball Simulator"));
		
	}
	
}
