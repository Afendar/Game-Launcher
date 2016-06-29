package com.rawad.gamelauncher.gui;

import com.rawad.gamehelpers.resources.ResourceManager;
import com.rawad.gamehelpers.resources.TextureResource;

import javafx.scene.image.ImageView;

public class IconHolder extends ImageView {
	
	private TextureResource icon;
	
	public IconHolder(final int iconLocation) {
		super();
		
		icon = ResourceManager.getTextureObject(iconLocation);
		
		icon.setOnloadAction((texture) -> setImage(texture.getTexture()));
		
	}
	
}
