package com.rawad.gamelauncher.gui;

import com.rawad.gamelauncher.files.GameProperties;
import com.rawad.gamelauncher.game.Game;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameIcon extends BorderPane {

	private Game game;
	
	private IconHolder iconHolder;
	private Label nameHolder;
	
	public GameIcon(Game game) {
		super();
		
		this.game = game;
		
		GameProperties gameProperties = game.getGameProperties();
		
		iconHolder = new IconHolder(game.getGameFile(), gameProperties.getIcon());
		this.setCenter(iconHolder);
		
		nameHolder = new Label(gameProperties.getName() + " (v" + gameProperties.getVersion() + ")");
		this.setBottom(nameHolder);
		
	}
	
	public Game getGame() {
		return game;
	}
	
}
