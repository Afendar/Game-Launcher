package com.rawad.gamelauncher.main;

import com.rawad.ballsimulator.client.Client;
import com.rawad.ballsimulator.game.BallSimulator;
import com.rawad.gamehelpers.game.GameManager;
import com.rawad.gamelauncher.gui.GameLauncher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameLauncherStart extends Application {
	
	private static final GameManager gameManager = GameManager.instance();
	
	private static final Client client = new Client();
	
	private static final GameLauncher gameLauncher = new GameLauncher();
	
	public static void main(String... args) {
		
		BallSimulator ballSim = new BallSimulator();
		ballSim.getProxies().put(client);
		
		gameManager.registerGame(ballSim);
		
		gameLauncher.init(gameManager, args);
		
		Application.launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		gameLauncher.setStage(primaryStage);
		
		Scene scene = new Scene(gameLauncher.getRoot());
		
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		
	}
	
}
