package com.rawad.gamelauncher.gui;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconHolder extends ImageView {
	
	public IconHolder(File iconFile) {
		super();
		
		Image image = new Image(iconFile.getAbsolutePath());
		setImage(image);
		
	}
	
}
