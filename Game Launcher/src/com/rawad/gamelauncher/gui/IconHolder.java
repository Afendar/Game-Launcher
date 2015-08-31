package com.rawad.gamelauncher.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class IconHolder extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6945210026482067764L;

	private BufferedImage icon;

	public IconHolder(BufferedImage icon) {
		this.icon = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);

		if (icon != null) {
			this.icon = icon;
		}

		setPreferredSize(new Dimension(this.icon.getWidth(),
				this.icon.getHeight()));

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(icon, 0, 0, null);

	}

}
