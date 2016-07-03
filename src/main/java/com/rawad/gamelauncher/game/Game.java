package com.rawad.gamelauncher.game;

import java.io.File;

import com.rawad.gamelauncher.files.GameProperties;

public class Game {
	
	private final GameProperties gameProperties;
	private final File gameFile;
	
	private boolean running;
	
	public Game(GameProperties gameProperties, File gameFile) {
		super();
		
		this.gameProperties = gameProperties;
		this.gameFile = gameFile;
		
	}
	
	/**
	 * @return the gameProperties
	 */
	public GameProperties getGameProperties() {
		return gameProperties;
	}
	
	/**
	 * @return the gameFile
	 */
	public File getGameFile() {
		return gameFile;
	}
	
	/**
	 * @return the running
	 */
	public synchronized boolean isRunning() {
		return running;
	}
	
	/**
	 * @param running the running to set
	 */
	public synchronized void setRunning(boolean running) {
		this.running = running;
	}
	
}
