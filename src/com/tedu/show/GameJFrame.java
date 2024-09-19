package com.tedu.show;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 窗体
 *
 *
 */
public class GameJFrame extends JFrame {
   public static int GameX = 820;
   public static int GameY = 627;
   private JPanel jPanel = null; // 正在显示的面板
   private KeyListener keyListener = null;
   private MouseMotionListener mouseMotionListener = null;
   private MouseListener mouseListener = null;
   private Thread thread = null; 
   
   
   public GameJFrame() {
	   init();
   }
   
   public void init() {
	   this.setSize(GameX, GameY);
	   this.setTitle("测试游戏");
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置退出并关闭
	   this.setLocationRelativeTo(null);
   }
   
	/*
	 * public void addButton() { this.setLayout(getLayout()); }
	 */
   
   
   public void start() {
	   if (jPanel != null) {
		   this.add(jPanel);
	   }
	   if (keyListener != null) {
		   this.addKeyListener(keyListener);
	   }
	   if (thread != null) {
		   thread.start();
	   }
	   this.setVisible(true);
	   if (this.jPanel instanceof Runnable) {
		   new Thread((Runnable)this.jPanel).start();
	   }
   }
   
   
   //set注入
   
	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}
	
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
	
	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}
	
	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	   
   
   
}
