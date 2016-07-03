package com.rawad.gamelauncher.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameLauncherStart extends Application {
	
	private static final GameLauncher gameLauncher = new GameLauncher();
	
	private static final String TITLE = "Game Launcher";
	
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	
	public static void main(String... args) {
		
		gameLauncher.init();
		
		Application.launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		gameLauncher.setStage(primaryStage);
		
		Scene scene = new Scene(gameLauncher.getRoot(), WIDTH, HEIGHT);
		
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		
	}
	
}
