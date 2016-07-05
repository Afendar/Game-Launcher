package com.rawad.gamelauncher.gui;

import com.rawad.gamelauncher.files.GameProperties;
import com.rawad.gamelauncher.game.Game;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class GameIcon extends StackPane {
	
	private Game game;
	
	private BorderPane iconLayer;
	
	private IconHolder iconHolder;
	private Label nameHolder;
	
	public GameIcon(Game game) {
		super();
		
		this.game = game;
		
		GameProperties gameProperties = game.getGameProperties();
		
		iconLayer = new BorderPane();
		
		iconHolder = new IconHolder(game.getGameFile(), gameProperties.getIcon());
		nameHolder = new Label(gameProperties.getName() + " (v" + gameProperties.getVersion() + ")");
		
		iconLayer.setCenter(iconHolder);
		iconLayer.setBottom(nameHolder);
		
		getChildren().addAll(iconLayer);
		
	}
	
	public Game getGame() {
		return game;
	}
	
}
