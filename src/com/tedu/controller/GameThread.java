package com.tedu.controller;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.LongToDoubleFunction;

import javax.lang.model.element.Element;
import javax.security.auth.kerberos.KerberosKey;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.tools.ForwardingJavaFileManager;

import com.tedu.element.Background;
import com.tedu.element.Die;
import com.tedu.element.ElementObj;
import com.tedu.element.Enemy;
import com.tedu.element.Play;
import com.tedu.element.Tool;
import com.tedu.game.GameStart;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

/**
 * 游戏主线程
 * 用于控制游戏加载，游戏关卡，游戏运行自动化
 * 游戏判定： 地图切换，资源释放，重新读取
 */

public class GameThread extends Thread{
	
	private ElementManager em;
	private long freetime = 0; // 吃到闹钟道具时的冻结时间
	private long wudi = 0;  // 吃到铲子时boss旁边的brick变成iron
	
	public GameThread() {
		em = ElementManager.getManager();
	}
	@Override
	public void run() {//游戏的run方法  主线程
		// TODO 自动生成的方法存根
		int t = 0;
		while(true) {
			
		
		//游戏开始前进度条
			t = gameLoad(t);
		
		// 游戏进行时
			int ac = gameRun();
			
		//游戏场景结束
			int choose = gameOver(ac);
			if (choose == 0) {
				t = 0;
			}else if (choose == 2) {
				if (t == 10) t = 0; 
				else t++;
			}
			try {
				sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	

	// 游戏切换
	private int gameOver(int ac) {
		// TODO 自动生成的方法存根
		int s1;
		if (ac == 1) {
			s1 = JOptionPane.showOptionDialog(null, "游戏失败，请选择", "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,   null, new String[]{"选择关卡","重新开始"}, "选择关卡");
		}
		else {
			s1 = JOptionPane.showOptionDialog(null, "游戏胜利，请选择", "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,   null, new String[]{"选择关卡","重新开始","下一关"}, "选择关卡");
		}
		em.init();
		return s1;
			
	}
	// 游戏进行时
	private synchronized int gameRun() {
		// TODO 自动生成的方法存根
		long GameTime = 0L;
		int cntEnemy = 0;
		while (true) {
			Map<GameElement, List<ElementObj>> all = em.getGameElements();

			List<ElementObj> enemy = em.getElementsByKey(GameElement.ENEMY);
			List<ElementObj> file = em.getElementsByKey(GameElement.PLAYFILE);
			List<ElementObj> map = em.getElementsByKey(GameElement.MAPS);
			List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
			List<ElementObj> enemyfile = em.getElementsByKey(GameElement.ENEMYFILE);
			List<ElementObj> boss = em.getElementsByKey(GameElement.BOSS);
			List<ElementObj> tool = em.getElementsByKey(GameElement.TOOL);
			
			ElementPK(enemyfile,map);
			ElementPK(enemy, file);
			ElementPK(enemyfile, file);
			ElementPK(play, enemyfile);
			ElementPK(file, map);
			ElementPK(boss, enemyfile);
			ElementPK(boss, file);
			moveAndupdate(all, GameTime); // 游戏自动化
			PKK(play, map, 0);
			PKK(enemy, map, 0);
			PKK(play, enemy, 0);
			PKK(enemy, enemy, 1);
			PKK(enemy, boss, 0);
			PKK(play, boss, 0);
			
			checkpz(play, tool);
			
			
			GameTime++;
			if (wudi > 0) wudi--;
			if (wudi <= 0) {
				List<ElementObj> list = em.getElementsByKey(GameElement.MAPS);
				for (int k = 0; k < list.size(); k++) {
					ElementObj temp = list.get(k);
					if (temp.getX() >= 370 && temp.getX() <= 430 && temp.getY() >= 520) {
						temp.setIcon(GameLoad.imgMap.get("BRICK"));
						temp.setHp(1);
					}
				}
			}
			
			
			
			if (GameTime % 500 == 0) {
				int a[] = {10, 400, 760};
				Random ran = new Random();
				int random = ran.nextInt(3);
				Boolean flag = true;
				for (int i = 0; i < enemy.size(); i++) {
					if (enemy.get(i).getreRectangle().intersects(new Rectangle(a[random], 10, 35, 35))) {
						flag = false;
						break;
					}
				}
				for (int i = 0; i < map.size(); i++) {
					if (map.get(i).getreRectangle().intersects(new Rectangle(a[random], 10, 35, 35))) {
						flag = false;
						break;
					}
				}
				if (flag)
					em.addElement(new Enemy().createElement(a[random] + ",10,bot_down"), GameElement.ENEMY);
			}
			
			if (GameTime % 1000 == 0) {
				Random ran = new Random();
				em.addElement(new Tool().createElement(ran.nextInt(810) + "," + ran.nextInt(617) + ",0" + (ran.nextInt(2) + 1)), GameElement.TOOL);
			}
			
			if (boss.get(0).isLive() == false || play.isEmpty()) {
			//	System.out.println("游戏失败");
				return 1;
			}
			
			if (enemy.isEmpty()) {
			//	System.out.println("游戏胜利");
				return 2;
			}
			
			try {
				sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	
	
	// 检查道具和人物碰撞
	public void checkpz(List<ElementObj>a, List<ElementObj> b) {
		for (int i = 0; i < a.size(); i++) {
			ElementObj aa = a.get(i);
			for (int j = 0; j < b.size(); j++) {
				ElementObj bb = b.get(j);
				if (aa.pk(bb)) {
					if (bb.getType() == 9) {
						freetime = 1200;
						b.remove(j--);
					}else {
						wudi = 1200;
						b.remove(j--);
						List<ElementObj> list = em.getElementsByKey(GameElement.MAPS);
						for (int k = 0; k < list.size(); k++) {
							ElementObj temp = list.get(k);
							if (temp.getX() >= 370 && temp.getX() <= 430 && temp.getY() >= 520) {
								temp.setIcon(GameLoad.imgMap.get("IRON"));
								temp.setHp(4);
							}
						}
					}
					break;
				}
			}
		}
	}
	
	//检测碰撞，碰撞回退，即不移动
	public synchronized void PKK(List<ElementObj>a, List<ElementObj> b, int t) {
		
		for (int i = 0; i < a.size(); i++) {
			ElementObj aa = a.get(i);
			for (int j = 0; j < b.size(); j++) {
				if (t == 1 && i == j) continue;
				ElementObj bb = b.get(j);
				if(bb.getType() == 1) continue; 
				if (aa.pk(bb)) {
					aa.rmove();
					bb.rmove();
					break;
				}
			}
		}
	}
	
	
	
	// 检测子弹与物体碰撞，碰撞即爆炸
	public synchronized void ElementPK(List<ElementObj>a, List<ElementObj> b) {
		
		for (int i = 0; i < a.size(); i++) {
			ElementObj aa = a.get(i);
			for (int j = 0; j < b.size(); j++) { 
				ElementObj bb = b.get(j);
				if (bb.getType() == 2 || bb.getType() == 1) continue;
				if (aa.pk(bb)) {
					aa.beatt();
					bb.beatt();
					break;
				}
			}
		}
	}
	
	
	//游戏元素自动化
	public synchronized void moveAndupdate(Map<GameElement, List<ElementObj>> all, long GameTime) {
		for (GameElement ge : GameElement.values()) {
			List<ElementObj> list = all.get(ge);
			for (int i = 0; i < list.size(); i++) {
				ElementObj obj = list.get(i);
				if (!obj.isLive()) {
					if (obj.getType() == 4) {
						obj.setIcon(GameLoad.imgMap.get("BREAK_BASE"));   //boss死亡，不移除
						break;
					}
					if (obj.getType() != 3)
						em.addElement(new Die().createElement(obj.getX()+","+obj.getY()+",boom"), GameElement.DIE);  //其他死亡
					list.remove(i);
					i--;
					continue;
				}
				if (ge.toString() == "ENEMY" && freetime > 0) {
					freetime--; // 冻结时，continue，不让⑦移动
					continue;
				}
				obj.model();
			}
		}
	}
	
	
	// 游戏加载
	private int gameLoad(int t) {
		// TODO 自动生成的方法存根
		
		if (t == 0) {
			em.addElement(new Background().createElement("") , GameElement.BACKGROUND);
			Object[] options ={ "第1关", "第2关", "第3关", "第4关", "第5关", "第6关", "第7关", "第8关", "第9关", "第10关",};  
			String s = null;
			while (s == null)
				s = (String) JOptionPane.showInputDialog(null,"请选择你想玩的关卡:\n", "关卡", JOptionPane.PLAIN_MESSAGE, new ImageIcon("xx.png"), options, "xx");
			if ("第10关".equals(s)) t = 10; 
			else t = new Integer(s.substring(1, 2));
		}
		GameLoad.init();
		GameLoad.loadImg();
		GameLoad.MapLoad(t);
		GameLoad.loadPlay();
		GameLoad.loadEnemy();
		GameLoad.loadBoss();
		return t;
	}
	
	
	
}
