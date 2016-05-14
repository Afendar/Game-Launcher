package com.rawad.gamelauncher.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.rawad.gamehelpers.resources.ResourceManager;
import com.rawad.gamehelpers.resources.TextureResource;

public class IconHolder extends JPanel {
	
	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 6945210026482067764L;
	
	private TextureResource icon;
	
	public IconHolder(final int iconLocation) {
		
		icon = ResourceManager.getTextureObject(iconLocation);//new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
		
		icon.setOnloadAction(new Runnable() {
			
			@Override
			public void run() {
				IconHolder.this.setPreferredSize(new Dimension(ResourceManager.getTexture(iconLocation).getWidth(), 
						ResourceManager.getTexture(iconLocation).getHeight()));
			}
			
		});
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(icon.getTexture(), 0, 0, null);
		
	}
	
}
