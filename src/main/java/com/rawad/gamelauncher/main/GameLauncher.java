package com.rawad.gamelauncher.main;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rawad.gamelauncher.files.FileLoader;
import com.rawad.gamelauncher.files.GameLauncherConfigurations;
import com.rawad.gamelauncher.files.GameProperties;
import com.rawad.gamelauncher.game.Game;
import com.rawad.gamelauncher.gui.GameIcon;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class GameLauncher {
	
	private GameLauncherConfigurations configs;
	
	private ArrayList<Game> games;
	
	private Stage stage;
	
	private BorderPane root;
	
	@FXML private FlowPane gameIconHolder;
	
	public void init() {
		
		games = new ArrayList<Game>();
		
		root = new BorderPane();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getClass().getSimpleName() + ".fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(root);
		
		try {
			fxmlLoader.load();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		configs = FileLoader.loadGameLauncherConfigs();
		
		File gamesDirectory = configs.getGamesDirectory();
		
		findGames(gamesDirectory);
		
		addGames();
		
	}
	
	public void setStage(Stage stage) {
		
		this.stage = stage;
		
		addGuiListeners();
		
	}
	
	private void findGames(File gamesDirectory) {
		
		games.clear();
		
		if(!gamesDirectory.isDirectory()) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING, "Given game directory \"" + gamesDirectory
					.getAbsolutePath() + "\" isn't a directory. Using parent file instead.");
			gamesDirectory = gamesDirectory.getParentFile();
		}
		
		for(File gameFile: gamesDirectory.listFiles()) {
			
			if(!gameFile.isDirectory()) continue;
			
			try {
				
				GameProperties gameProperties = FileLoader.loadGameProperties(gameFile);
				
				Game game = new Game(gameProperties, gameFile);
				games.add(game);
				
			} catch(Exception ex) {
				Logger.getLogger(getClass().getName()).log(Level.WARNING, "Failed to load game.", ex);
			}
			
		}
		
	}
	
	private void addGames() {
		
		gameIconHolder.getChildren().clear();
		
		for(Game game: games) {
			
			GameIcon gameIcon = new GameIcon(game);
			gameIcon.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
				
				if(game.isRunning()) return;
				
				Thread gameThread = new Thread(() -> {
					launchGame(game);
				}, game.getGameProperties().getName() + " Thread");
				gameThread.setDaemon(true);
				gameThread.start();
				
			});
			
			gameIconHolder.getChildren().add(gameIcon);
			
		}
		
	}
	
	private void addGuiListeners() {
		
		for(Node gameIcon: gameIconHolder.getChildren()) {
			gameIcon.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
				if(mouseEvent.isPrimaryButtonDown()) stage.setIconified(true);
			});
		}
		
	}
	
	@FXML private void refreshGamesList() {
		
		findGames(configs.getGamesDirectory());
		addGames();
		addGuiListeners();
		
	}
	
	private void launchGame(Game game) {
		
		GameProperties properties = game.getGameProperties();
		
		Logger.getLogger(getClass().getName()).log(Level.INFO, "Launching " + properties.getName() + "...");
		
		String jarPath = "" + game.getGameFile().toPath().resolve(properties.getJar().getPath()).toString() + "";
		
		try {
			
			ProcessBuilder gameProcessBuilder = new ProcessBuilder("java", "-jar", jarPath);
			gameProcessBuilder.redirectOutput(Redirect.INHERIT);
			gameProcessBuilder.redirectError(Redirect.INHERIT);
			
			Process gameProcess = gameProcessBuilder.start();
			
			game.setRunning(true);
			
			int exit = gameProcess.waitFor();
			
			Logger.getLogger(getClass().getName()).log(Level.INFO, properties.getName() + " exited with a code of "
					+ exit);
			
		} catch (IOException ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Failed to launch " + properties.getName(), ex);
		} catch(InterruptedException ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING, "Error waiting for " + properties.getName()
			+ " to exit", ex);
		} finally {
			game.setRunning(false);
			Platform.runLater(() -> stage.show());
		}
		
	}
	
	@FXML private void changeGamesDirectory() {
		
		DirectoryChooser fileChooser = new DirectoryChooser();
		
		fileChooser.setInitialDirectory(configs.getGamesDirectory().getAbsoluteFile().getParentFile());
		fileChooser.setTitle("Chooser Game Directory");
		
		File newGamesDir = fileChooser.showDialog(stage);
		
		if(newGamesDir == null) return;
		
		configs.setGamesDirectory(newGamesDir);
		
		refreshGamesList();
		
	}
	
	@FXML private void requestClose() {
		
		FileLoader.saveGameLauncherConfigs(configs);
		
		stage.close();
		
	}
	
	public BorderPane getRoot() {
		return root;
	}
	
}
