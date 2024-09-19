package com.tedu.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tedu.element.ElementObj;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

/**
 *  监听类，用于监听用户的操作Key listener
 * 
 *
 */
public class GameListener  implements KeyListener{

	private ElementManager em = ElementManager.getManager();
	
	
	
	private Set<Integer> set = new HashSet<Integer>(); 
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
	/**
	 * 按下: 左37 上38 右39 下40    按tab没有反应
	 * 实现主角的移动
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		
		int key= e.getKeyCode();
		if (set.contains(key)) {
			return;
		}
		set.add(key);
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
		for (ElementObj obj : play) {
			obj.keyClick(true, e.getKeyCode());
		}
	}
	/**松开*/
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		
		if (!set.contains(e.getKeyCode())) {//如果这个不存在，就停止
			return;
		}//存在(已经按过这个案件)
		set.remove(e.getKeyCode());//移除数据
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
		for (ElementObj obj : play) {
			obj.keyClick(false, e.getKeyCode());
		}
	}

}
