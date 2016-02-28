package com.rawad.gamelauncher.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rawad.gamehelpers.game.Game;

public class GameIcon extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4465384049242652039L;

	private Game game;

	private IconHolder iconHolder;
	private JLabel nameHolder;

	public GameIcon(Game game) {
		super();

		this.game = game;

		initialize();
		
	}

	private void initialize() {
		
		setLayout(new BorderLayout(0, 0));
		
		iconHolder = new IconHolder(game.getIconLocation());
		add(iconHolder, BorderLayout.CENTER);
		
		nameHolder = new JLabel();
		nameHolder.setText(game.toString());
		add(nameHolder, BorderLayout.SOUTH);
		
	}

	public Game getGame() {
		return game;
	}

}
