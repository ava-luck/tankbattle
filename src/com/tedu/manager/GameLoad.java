package com.tedu.manager;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.naming.InitialContext;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import com.tedu.element.Boss;
import com.tedu.element.ElementObj;
import com.tedu.element.Enemy;
import com.tedu.element.Mapobj;
import com.tedu.element.Play;

//加载器


public class GameLoad {
	
	private static ElementManager em = ElementManager.getManager();
	public static Map<String, ImageIcon> imgMap = new HashMap<>();
	public static Map<String, List<ImageIcon>> imgMaps;

	public static void init() {
		em.clear();
		em.putt();
	}
	
	/**
	 * 传入地图id，自动生成地图
	 * 
	 */
	//读取文件的类
	private static Properties pro = new Properties();
	
	public static void loadPlay() {
		String playstr = "460,540,up";
		ElementObj play = new Play().createElement(playstr);
		em.addElement(play, GameElement.PLAY);
	}
	
	public static void loadBoss() {
		String bossstr = "390,540,BASE";
		ElementObj boss = new Boss().createElement(bossstr);
		em.addElement(boss, GameElement.BOSS);
		for (int i = 0; i < 4; i++) {
			ElementObj temp = new Mapobj().createElement("BRICK," + (370 + i * 20) + ",520");
			em.addElement(temp, GameElement.MAPS);
		}
		for (int i = 0; i < 2; i++) {
			ElementObj temp = new Mapobj().createElement("BRICK,370," + (540 + i * 20));
			em.addElement(temp, GameElement.MAPS);
		}
		for (int i = 0; i < 2; i++) {
			ElementObj temp = new Mapobj().createElement("BRICK,430," + (540 + i * 20));
			em.addElement(temp, GameElement.MAPS);
		}
	}
	
	
	public static void loadEnemy() {
		int a[] = {10, 400, 760};
		List<ElementObj> map = em.getElementsByKey(GameElement.MAPS);
		for (int i = 0; i < 3; i++) {
			ElementObj en = new Enemy().createElement(a[i] + ",10,bot_down");
			boolean flag = true;
			for (int j = 0; j < map.size(); j++) {
				if (map.get(j).pk(en)) {
					flag = false;
					break;
				} 
			}
			if (flag) em.addElement(new Enemy().createElement(a[i] + ",10,bot_down"), GameElement.ENEMY);
		}
	}
	
	
	public static void MapLoad(int mapId) {
		String mapName = "com/tedu/text/" + mapId + ".map";
		//使用io流获取文件对象
		
		ClassLoader classLoader =  GameLoad.class.getClassLoader();
		InputStream maps = classLoader.getResourceAsStream(mapName); 
		if (maps == null) {
			System.out.println("配置文件读取异常，重新安装");
			return;
		}
		
		try {
			pro.clear();
			pro.load(maps);
			Enumeration<?> names = pro.propertyNames();
			while (names.hasMoreElements()) {
				String key =  names.nextElement().toString();
				String[] arrs = pro.getProperty(key).split(";");
				for (int i = 0; i < arrs.length; i++) {
					ElementObj element = new Mapobj().createElement(key + "," + arrs[i]);
					em.addElement(element, GameElement.MAPS);
				}
			}
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	public static void loadImg() {
		String texturl = "com/tedu/text/GameData.pro";
 		ClassLoader classLoader =  GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
		
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set =  pro.keySet();
			for (Object o : set) {
				String url = pro.getProperty(o.toString());
				imgMap.put(o.toString(), new ImageIcon(url));
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
