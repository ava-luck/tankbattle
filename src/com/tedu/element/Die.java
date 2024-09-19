package com.tedu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.manager.GameLoad;

public class Die extends ElementObj{

	private long time = 0;
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getH(), this.getW(), null);
	}
	@Override
	public ElementObj createElement(String str) {
		
		String[] split = str.split(",");
		this.setX(new Integer(split[0]));
		this.setY(new Integer(split[1]));
		ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
		this.setH(icon2.getIconHeight());
		this.setW(icon2.getIconWidth());
		this.setIcon(icon2);
		this.setType(3);
		return this;
	}

	@Override
	protected void move() {
		time++;
		if (time >= 6)
			this.setLive(false);
	}
	
}
