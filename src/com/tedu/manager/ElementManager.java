package com.tedu.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tedu.element.ElementObj;

/**
 * @说明：  本类是元素管理器，专门存储所有的元素，同时提供方法给予视图和控制获取数据
 
 *
 *1.存储所有元素数据，怎么存放？
 *2.管理器是试图和控制要访问，只有一个，单例模式
 *
 */


public class ElementManager {
	
	
	/**
	 * String 作为key，匹配所有的
	 * play -》 1
	 * enemy -> listenemy
	 * 枚举类型，当作map的key用来区分不一样的资源，用于获取资源
	 * 
	 */
	
	private Map<GameElement, List<ElementObj>> gameElements;

	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	} 
	
	public void addElement(ElementObj obj, GameElement ge) {
		gameElements.get(ge).add(obj);
	}
	
	
	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}
	
	public void clear() {
		gameElements.clear();
	}
	
	private static ElementManager EM;
	public static synchronized ElementManager getManager() {
		if (EM == null) {
			EM = new ElementManager();
		}
		return EM;
	}
	private ElementManager() {
		// TODO 自动生成的构造函数存根
		init();
	}
	public void init() {
		gameElements = new HashMap<GameElement, List<ElementObj>>();
		putt();
	}
	public void putt() {
		for (GameElement g : GameElement.values()) {
			gameElements.put(g, new ArrayList<ElementObj>());
		}
	}
}
