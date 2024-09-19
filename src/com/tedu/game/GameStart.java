package com.tedu.game;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.tedu.controller.GameListener;
import com.tedu.controller.GameThread;
import com.tedu.show.GameJFrame;
import com.tedu.show.GameMainJpanel;
/**
 * 程序的唯一入口
 */
public class GameStart {
	static GameMainJpanel jp = new GameMainJpanel(); 
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		GameJFrame gj = new GameJFrame();
		/**实例化面板，注入到jframe中*/
		GameListener listener = new GameListener();
//		实例化监听
		GameThread th = new GameThread();
//		实例化主线程
		jp.setBackground(Color.BLACK);
//		注入
		gj.setjPanel(jp);
		gj.setKeyListener(listener);
		gj.setThread(th);
		gj.start();
		
		
	}

}
