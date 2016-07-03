package com.rawad.gamelauncher.gui;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconHolder extends ImageView {
	
	public IconHolder(File gameFile, File iconFile) {
		super();
		
		String path = "file:" + gameFile.toPath().resolve(iconFile.getPath());
		
		Image image = new Image(path);
		setImage(image);
		
	}
	
}
