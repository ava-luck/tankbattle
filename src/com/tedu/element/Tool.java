package com.tedu.element;

import java.awt.Graphics;
import java.util.Spliterator;

import javax.swing.ImageIcon;

import com.tedu.manager.GameLoad;

public class Tool extends ElementObj{

	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		this.setX(new Integer(split[0]));
		this.setY(new Integer(split[1]));
		ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
		this.setH(icon2.getIconHeight());
		this.setW(icon2.getIconWidth());
		this.setIcon(icon2);
		if ("01".equals(split[2])) this.setType(9);
		else this.setType(10);
		return this;
	}
	
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}

}
