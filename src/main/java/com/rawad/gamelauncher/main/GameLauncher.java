package com.rawad.gamelauncher.main;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rawad.gamelauncher.files.FileLoader;
import com.rawad.gamelauncher.files.GameLauncherConfigurations;
import com.rawad.gamelauncher.files.GameProperties;
import com.rawad.gamelauncher.gui.GameIcon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class GameLauncher {
	
	private BorderPane root;
	
	@FXML private FlowPane gameIconHolder;
	
	public void init() {
		
		root = new BorderPane();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getClass().getSimpleName()));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(root);
		
		try {
			fxmlLoader.load();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// Use Runtime.getRuntime().exec("java -jar 'GameName'.jar"); to launch game in a different process (Method
		// returns the Process object).
		
		GameLauncherConfigurations gameLaucnherConfigs = FileLoader.loadGameLauncherConfigs();
		
		File gameDirectory = gameLaucnherConfigs.getGamesDirectory();
		
		if(!gameDirectory.isDirectory()) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING, "Given game directory \"" + gameDirectory
					.getAbsolutePath() + "\" isn't a directory. Using parent file instead.");
			gameDirectory = gameDirectory.getParentFile();
		}
		
		for(File gameFile: gameDirectory.listFiles()) {
			
			if(!gameFile.isDirectory()) continue;
			
			try {
				
				GameProperties gameProperties = FileLoader.loadGameProperties(gameFile);
				
				GameIcon gameIcon = new GameIcon(gameProperties);
				gameIcon.setOnMouseClicked(e -> {
					Logger.getLogger(getClass().getName()).log(Level.INFO, "Launching game...");
				});
				
				gameIconHolder.getChildren().add(gameIcon);
				
			} catch(Exception ex) {
				Logger.getLogger(getClass().getName()).log(Level.WARNING, "Failed to load game.", ex);
			}
			
		}
		
		/*/
		ConfigurationsFileParser configFileParser = new ConfigurationsFileParser();
		
		Loader loader = new Loader();
		
		loader.loadLauncherConfigurations(configFileParser);
		
		File gamesDirectory = new File(configFileParser.getGamesDirectory());
		
		if(!gamesDirectory.isDirectory()) throw new IllegalArgumentException("The file given for the games directory"
				+ " is not a directory.");
		
		File[] gameFolders = gamesDirectory.listFiles();
		
		GamePropertiesFileParser gamePropertiesFileParser = new GamePropertiesFileParser();
		
		for(File gameFolder: gameFolders) {
			
			if(!gameFolder.isDirectory()) continue;
			
			loader.loadGameProperties(gamePropertiesFileParser, gamesDirectory.getName(), gameFolder.getName());
			
			loader.register
			
			GameIcon gameIcon = new GameIcon();
			
		}/**/
		
	}
	
	public void setStage(Stage stage) {
		
		for(Node gameIcon: gameIconHolder.getChildren()) {
			gameIcon.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
				if(mouseEvent.isPrimaryButtonDown()) stage.setIconified(true);
			});
		}
		
	}
	
	public BorderPane getRoot() {
		return root;
	}
	
}
