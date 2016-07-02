package com.rawad.gamelauncher.gui;

import com.rawad.gamelauncher.files.GameProperties;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameIcon extends BorderPane {

	private IconHolder iconHolder;
	private Label nameHolder;
	
	public GameIcon(GameProperties gameProperties) {
		super();
		
		iconHolder = new IconHolder(gameProperties.getIcon());
		this.setCenter(iconHolder);
		
		nameHolder = new Label(gameProperties.getName() + "(v" + gameProperties.getVersion() + ")");
		this.setBottom(nameHolder);
		
	}
	
}
