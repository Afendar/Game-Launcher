package com.rawad.gamelauncher.gui;

import com.rawad.gamehelpers.client.GameTextures;
import com.rawad.gamehelpers.game.Game;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameIcon extends BorderPane {
	
	private Game game;

	private IconHolder iconHolder;
	private Label nameHolder;
	
	public GameIcon(Game game) {
		super();
		
		this.game = game;
		
		iconHolder = new IconHolder(GameTextures.findTexture(GameTextures.GAME_ICON));
		this.setCenter(iconHolder);
		
		nameHolder = new Label(game.getName());
		this.setBottom(nameHolder);
		
	}
	
	public Game getGame() {
		return game;
	}
	
}
