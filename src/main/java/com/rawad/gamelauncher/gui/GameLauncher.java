package com.rawad.gamelauncher.gui;

import com.rawad.ballsimulator.client.Client;
import com.rawad.gamehelpers.game.Game;
import com.rawad.gamehelpers.game.GameManager;
import com.rawad.gamehelpers.resources.Loader;
import com.rawad.gamehelpers.resources.ResourceManager;
import com.rawad.gamehelpers.utils.Util;

import javafx.application.Platform;
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
	
	public void init(GameManager gameManager, String... args) {
		
		root = new BorderPane();
		
		FXMLLoader loader = new FXMLLoader(Loader.getFxmlLocation(getClass()));
		loader.setController(this);
		loader.setRoot(root);
		
		try {
			loader.load();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		ResourceManager.init(Util.parseCommandLineArguments(args));
		
		for(Game game: gameManager.getGames()) {
			
			// Register game icon texture for use by GameIcon.
			
			GameIcon gameIcon = new GameIcon(game);
			
			gameIcon.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
				Stage gameStage = new Stage();
				Client client = game.getProxies().get(Client.class);
				
				Platform.runLater(() -> {
					client.setStage(gameStage);
					client.initGui();
				});
				
				gameManager.launchGame(game);
				
			});
			
			gameIconHolder.getChildren().add(gameIcon);
			
		}
		
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
