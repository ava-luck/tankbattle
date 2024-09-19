package com.tedu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Background extends ElementObj{

	
	public ElementObj createElement(String str) {
		
		this.setIcon(new ImageIcon("image/login_background.png"));
		this.setH(this.getIcon().getIconHeight());
		this.setW(this.getIcon().getIconWidth());
		
		
		return this;
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(this.getIcon().getImage(), 0, 0, this.getW(), this.getH(), null);
	}

}
