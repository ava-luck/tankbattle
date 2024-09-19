package com.tedu.element;

import java.awt.Graphics;
import java.time.Year;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.security.auth.x500.X500Principal;
import javax.swing.ImageIcon;

import com.tedu.manager.GameLoad;

public class Mapobj extends ElementObj{

	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}
	
	//str 传入   墙,x,y
	

	
	@Override
	public ElementObj createElement(String str) {
		String[] arr = str.split(","); 
		ImageIcon icon = GameLoad.imgMap.get(arr[0]);
		switch (arr[0]) {
			case "GRASS": this.setType(1); break;
			case "IRON" : this.setHp(4); break;
			case "RIVER": this.setType(2); break;
		}

		int x = Integer.parseInt(arr[1]);
		int y = Integer.parseInt(arr[2]);
		int h = icon.getIconHeight();
		int w = icon.getIconWidth();
		this.setH(h);
		this.setX(x);
		this.setY(y);
		this.setW(w);
		this.setIcon(icon);
		return this;
	}
	
}
