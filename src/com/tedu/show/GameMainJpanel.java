package com.tedu.show; 
/**
 * 游戏的主要面板
 *
 * 主要进行元素的显示，同时进行界面的刷新
 */

import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import com.tedu.element.ElementObj;
import com.tedu.element.Play;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

public class GameMainJpanel extends JPanel implements Runnable{

	private ElementManager em;
	 
	public GameMainJpanel() {
		init();
	}
	public void init() {
		em = ElementManager.getManager();
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		Map<GameElement, List<ElementObj>> all = em.getGameElements();
		
		for (GameElement ge : GameElement.values()) { 
			List<ElementObj> list = all.get(ge);
			for (int i = 0; i < list.size(); i++) {
				ElementObj obj = list.get(i);
				obj.showElement(g);
			}
		}
	
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while (true) {
			this.repaint();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace(); 
			}
		}
	}
	
}
