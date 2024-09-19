package com.tedu.element;
/**
 * 所有元素的基类
 * 
 *
 */

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public abstract class ElementObj {
	private int x;
	private int y;
	private int w;
	private int h;
	private ImageIcon icon;
	private int hp = 1;
	private boolean live = true;
	private int type = 0; 
	/**
	 * type
	 * 
	 * 1 GRASS
	 * 2 RIVER
	 * 3 Die
	 * 
	 * 4 BOSS
	 * 
	 * 
	 * 6 PlayFile
	 * 7 Enemy
	 * 
	 * 
	 * 9 冻结道具
	 * 10 无敌道具
	 * @return
	 */
	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ElementObj() {
		
	}
	
	public void beatt() {
		this.hp = this.hp - 1;
		if (this.hp == 0)
			this.live = false;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.icon = icon;
	}
	
	public abstract void showElement(Graphics g);
	
	/**
	 * 使用父类定义接受键盘事件的方法
	 * 
	 * 只有需要键盘监听的子类，重写这个方法
	 * 
	 * 点击类型 true代表按下 false松开
	 * key代表出发的code指
	 * 
	 */
	
	public void keyClick(boolean bl, int key) {
		
	}
	
	protected void move() {
		
	}
	
	public void rmove() {
		
	}
	
	public final void model() {
		updateImage();
		move();
		add();
	}
	public ElementObj createElement(String str) {
		return null;
	}
	
	protected void add() { 
		// TODO 自动生成的方法存根
		
	}

	protected void updateImage() {
		// TODO 自动生成的方法存根
		
	}

	public Rectangle getreRectangle() {
		return new Rectangle(x, y, w, h);
	}
	
	public boolean pk(ElementObj obj) {
		return this.getreRectangle().intersects(obj.getreRectangle());
	}
	
	
	public void die() {
		
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
	
}
