import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.*;

public class test extends Frame  implements ActionListener {
	private int ID;
	private int oldx;
	private int oldy;
	private boolean fS;
	boolean cj = false;
	int dj = 0;
	private boolean begin = false;
	boolean doubleman ;
	static int level=1;
	boolean dz = false;
	static JButton useb;
	static JTextField uset;
	static JFrame user = new JFrame ();
	static{
		user.setBounds(200,200,400,100);
		user.setLayout(null);
		  uset = new JTextField();
		uset.setBounds(140,10,140,20);
		user.add(uset);
		JLabel use = new JLabel("请输入你的用户名");
		use.setBounds(10,10,140,20);
		user.add(use);
		  useb = new JButton("确定");
		useb.setBounds(100,40,70,20);
		user.add(useb);
	}
	int fs=0;
	int mts = 7;
	int cws = 7;
	int rs = 1;
	 int tcs ;
	int zdx,zdy;
	int z =0;
	Thread th = null;
	Thread thr = null ;
	String fangxiang;
	static URL cb = null; 
	Boom b = new Boom(0,0);
	static AudioClip aau; 
	static {
		
		File f = new File("bj.aif");  
		try {
			cb = f.toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		aau = Applet.newAudioClip(cb); 
	}
	
	static URL cbs = null; 
	static AudioClip aaus; 
	static {
		
		File f = new File("d.wav"); //引号里面的是音乐文件所在的绝对鹿筋
		try {
			cbs = f.toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		aaus = Applet.newAudioClip(cbs); 
	}
	
	 
	
	Tank[] tTank = new Tank[tcs];
	
	
	River[] river = new River [rs];
	
	MetalWall[] mw =new MetalWall[mts];
	commonWall[] cw =new commonWall[cws];
	int j = 35;
	 
	NetClient nc  = new NetClient(this);
	Image screenImage = null;
	public void update(Graphics g) {
		
		
		screenImage = this.createImage(800, 600);

		Graphics gps = screenImage.getGraphics();
		
		gps.setColor(Color.GRAY);	
		gps.fillRect(0, 0, 800, 600);
		
		framPaint(gps);
		g.drawImage(screenImage, 0, 0, null);
	}
	private void framPaint(Graphics g) {
		if (dz==false)
		{
			if(tTank[0].live==false)
			{
				
				if ( doubleman == false )
				{
					Color c = g.getColor();
					g.setColor(Color.red);  

					Font f1 = g.getFont();
					g.setFont(new Font("TimesRoman", Font.BOLD, 31));
					g.drawString("你输了 "+"分数为"+fs, 200, 270);
					user.setVisible(true);
					begin = false;
				}
				
			}
		}
		boolean win = true;
		
		for (int i=0;i<tcs;i++)
		{
			if (!tTank[i].good)
			{
				if (tTank[i].live)
					win = false;
					
			}
		}
		
		if (dz == false)
		{
			if (win)
			{
				if (doubleman == false)
				{
					Color c = g.getColor();
					g.setColor(Color.red);  

					Font f1 = g.getFont();
					g.setFont(new Font("TimesRoman", Font.BOLD, 31));
					g.drawString("你赢啦 "+"分数为"+fs, 200, 270);
					user.setVisible(true);
					begin = false;
				}
				
			}
		}
		Color c = g.getColor();
		g.setColor(Color.red);  

		Font f1 = g.getFont();
		g.setFont(new Font("TimesRoman", Font.BOLD, 31));
		g.drawString(fs+"", 00, 70);
		
		for (int i =0; i<mts;i++)
		{
			
			mw[i].paint(g); 
		}
		for (int i =0; i<cws;i++)
		{
			if(cw[i].live)
				cw[i].paint(g); 
		}
	 
		for (int i =0; i<rs;i++)
		{
			river[0].paint(g);	
		}
		
		
		for (int i =0; i<tcs;i++)
		{ 
			tTank [i].paint(g);
		}
		
		for (int i =0; i<tcs;i++)
		{
			for (int j = 0;j<tTank[i].zds;j++)
			{
				if( tTank[i].bl[j].boon())
				{
					b.setx(tTank[i].bl[j].x);
					b.sety(tTank[i].bl[j].y);
					b.paint(g);
					tTank[i].bl[j].boom=false;
				}
			}
		}
		
		 
		
		
		
		for (int i =0; i<mts;i++)
		{
			for (int j =0; j<tcs;j++)
			{
				if (tTank [j].getx()+33>mw[i].getx()&&tTank [j].getx()<mw[i].getx()+33&&tTank [j].gety()+33>mw[i].gety()&&tTank [j].gety()<mw[i].gety()+33)
				{
					tTank [j].peng();
				}
			}
	 		 
		}
		for (int i =0; i<cws;i++)
		{
			for (int j =0; j<tcs;j++)
			{
				if (cw[i].live==true)
				{
					if (tTank [j].getx()+33>cw[i].getx()&&tTank [j].getx()<cw[i].getx()+21&&tTank [j].gety()+33>cw[i].gety()&&tTank [j].gety()<cw[i].gety()+21)
					{
						tTank [j].peng();
					}
				}
			}
	 		 
		}
		for (int i =0; i<tcs;i++)
		{
			for (int j =0; j<tcs;j++)
			{
				if (j!=i)
				{
					if (tTank [j].getx()+33>tTank[i].getx()&&tTank [j].getx()<tTank[i].getx()+33&&tTank [j].gety()+33>tTank[i].gety()&&tTank [j].gety()<tTank[i].gety()+33)
					{
						if(tTank[i].live)
						{
							tTank [j].peng();
						}
						
					}
				}
			}
		  
		}
		
		for (int i =0; i<rs;i++)
		{
			for (int j =0; j<tcs;j++)
			{
				if (tTank [j].getx()+33>river[i].getx()&&tTank [j].getx()<river[i].getx()+55&&tTank [j].gety()+33>river[i].gety()&&tTank [j].gety()<river[i].gety()+150)
				{
					tTank [j].peng();
				}
			}
			
				
			 
		}
	  
		for (int j =0; j<tcs;j++)
		{
			for(int i = 0;i<tTank [j].zds;i++)
			{
				 
				 
				if (tTank [j].bl[i].getlive()==true)
				{
					if(tTank[j].live)
					{
						try
						{
								tTank [j].bl[i].paint(g); 
						}
						catch(Exception e)
						{
						 
							
						}
						

					}
												
				}		
					

			}
		}
		

		
	}
	
	
	public static void main(String[] args) {
		new test();  
		
	}
	 
	
	
	public test ()
	{	 
		
		ButtonHandler bt1=new ButtonHandler(this);
		useb.addActionListener(bt1);
 
		 
		
		aau.loop();
		
		MenuBar jmb = null;
		Menu jm1 = null, jm2 = null, jm3 = null, jm4 = null, jm5 = null,jm6 = null;
		MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null,
				jmi6 = null, jmi7 = null, jmi8 = null, jmi9 = null,jmi10 = null,jmi11 = null,jmi12 = null,jmi13=null;
		jmb = new MenuBar();
		jm1 = new Menu("游戏");
		jm2 = new Menu("暂停/继续");
		jm3 = new Menu("帮助");
		jm4 = new Menu("游戏级别");
		jm5 = new Menu("排行");
		jm6 = new Menu("场景");
		
		jmi1 = new MenuItem("开始新游戏");
		jmi2 = new MenuItem("退出");
		jmi3 = new MenuItem("暂停");
		jmi4 = new MenuItem("继续");
		jmi5 = new MenuItem("游戏说明");
		jmi6 = new MenuItem("级别1");
		jmi7 = new MenuItem("级别2");
		jmi8 = new MenuItem("级别3");
		jmi9 = new MenuItem("排行");
		jmi10 = new MenuItem("双人小游戏");
		jmi13 = new MenuItem("多人小游戏");
		jmi11 = new MenuItem("场景");
		jmi12 = new MenuItem("自定义级别");
		
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi10);
		jm1.add(jmi13);
		jm2.add(jmi3);
		jm2.add(jmi4);
		jm3.add(jmi5);
		jm4.add(jmi6);
		jm4.add(jmi7);
		jm4.add(jmi8);
		jm5.add(jmi9);
		jm6.add(jmi11);
		jm4.add(jmi12);
		
		
		jmb.add(jm1);
		jmb.add(jm2);
		
		jmb.add(jm4);
		jmb.add(jm3);
		jmb.add(jm5);
		jmb.add(jm6);
		jmi1.addActionListener(this);
		jmi1.setActionCommand("NewGame");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("Exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("Stop");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("Continue");
		jmi5.addActionListener(this);
		jmi5.setActionCommand("help");
		jmi6.addActionListener(this);
		jmi6.setActionCommand("level1");
		jmi7.addActionListener(this);
		jmi7.setActionCommand("level2");
		jmi8.addActionListener(this);
		jmi8.setActionCommand("level3");
		jmi9.addActionListener(this);
		jmi9.setActionCommand("top");
		jmi10.addActionListener(this);
		jmi10.setActionCommand("double");
		this.setMenuBar(jmb);
		jmi11.addActionListener(this);
		jmi11.setActionCommand("cj");
		
		jmi12.addActionListener(this);
		jmi12.setActionCommand("zdy");
		jmi13.addActionListener(this);
		jmi13.setActionCommand("many");
		
		river[0]= new River(100 ,200);
		for (int i =0; i<mts;i++)
		{
			mw[i]= new MetalWall(200+j,100);
			 
			j=j+35;
		}
		j=0;
		for (int i =0; i<cws;i++)
		{
			cw[i]= new commonWall(400,300+j);
			 
			j=j+21;
		}
		j=0;
		
		
		
		
		
		this.setVisible(true);
		//屏幕中间
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)screensize.getWidth() ,(int)screensize.getHeight() ,800,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		//关窗口 
		addWindowListener(new WindowAdapter(){
			 public void windowClosing (WindowEvent e1){
				 System.exit(0);
			 }
			
		});
		
		
		 
		this.addKeyListener(new KeyMonitor());
		
		
		
		
	 
	}
	 
	private class PaintThread implements Runnable {
		public void run() {
			// TODO Auto-generated method stub
			 
				
				while(begin)
				{
					
					 repaint();
					  
					try {	
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			 
		}
	}
	 
	public class RootThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Random ra =new Random();
			Random ra1 =new Random();
			
			if (level==1)
				dj =100;
			if (level==2)
				dj =70;
			if (level==3)
				dj =10;
			if (level == 4)
			{
				
			}
			while(begin)
			{
				
				int i = ra.nextInt(tcs-1)+1;
				int roots =  ra1.nextInt(5)+1;
				 
					if (roots==1)
					{
						if (tTank[i].gety()>37)
						{
							tTank[i].setfangxiang('U');
							tTank[i].sety(tTank[i].gety()-6);
						}
						
						 
					}
					if (roots==2)
					{
						if (tTank[i].gety()<600-37)
						{
							tTank[i].setfangxiang('D');
							tTank[i].sety(tTank[i].gety()+6);
						}
						
						
						 
					}
					if (roots==3)
					{
						tTank[i].setfangxiang('L');
						tTank[i].setx(tTank[i].getx()-6);
						
						 
					}
					if (roots==4)
					{
						tTank[i].setfangxiang('R');
						tTank[i].setx(tTank[i].getx()+6);
						
						 
					}
					if (roots==5)
					{
					 
						tTank[i].bl[tTank[i].zds]=tTank[i].fase();
						
						tTank[i].zds++;
					}
					
					if (tTank[i].getx()>tTank [0].getx()-15&&tTank[i].getx() < tTank [0].getx()+15||tTank[i].gety() < tTank [0].gety()-15&&tTank[i].gety() > tTank [0].gety()+15)
					{
						if (tTank[i].getx() < tTank [0].getx())
						{
							tTank[i].setfangxiang('R');
						}
						if (tTank[i].getx() > tTank [0].getx())
						{
							tTank[i].setfangxiang('L');
						}
						if (tTank[i].gety() > tTank [0].gety())
						{
							tTank[i].setfangxiang('U');
						}
						if (tTank[i].gety() <tTank [0].gety())
						{
							tTank[i].setfangxiang('D');
						}
						tTank[i].bl[tTank[i].zds]=tTank[i].fase();
						tTank[i].zds++;
					}
					
					try {	
						 
						Thread.sleep(dj);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 
				 
				
			}
		}

	}
	 
	private class KeyMonitor extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			 // 监听键盘释放
			tTank [0].keyReleased(e);
			if (doubleman == true)
			{
				tTank [0].keyReleased(e);
				tTank[1].keyReleased(e);
			}
		}

		public void keyPressed(KeyEvent e) {
			 // 监听键盘按下
			tTank [0].keyPressed(e);
			if (doubleman == true)
			{
				tTank [0].keyPressed(e);
				tTank[1].keyPressed(e);
			}
		}

	}
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getActionCommand().equals("Exit")) 
		{
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要退出吗", "",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
			if (response == 0) {
			 
				System.exit(0);
			}
		}
		if (e.getActionCommand().equals("zdy")) 
		{
			 JFileChooser chooser = new JFileChooser(".");  
		        chooser.showOpenDialog(this);  
		        String filePath = chooser.getSelectedFile().getAbsolutePath();  
		        System.out.println(filePath);  
		        String s = null;
		        int zj = 0;
		        int sl = 0;
		        fs = 0;
				aau.loop();
				begin = true;
				level = 4;
				 if (th!=null)
					{
						
						th.stop();
						thr.stop();
						 
					 
					}
		    	BufferedReader bf = null;
				try {
					bf = new BufferedReader(new FileReader(filePath));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        try {
		        	 
		        	while((s = bf.readLine())!=null)
					{
		        		 
						
						 if (zj==0)
							{
								
								sl = Integer.parseInt(s.replace("tk=", "")); 
								tcs=sl;
								 tTank = new Tank[sl];
								tTank[0]= new Tank(300,300,this,true,true,level);
								
								 
								 
							}
						 
						if (zj==1)
						{
							dj = Integer.parseInt(s.replace("dj=", ""));
						}
					 
					 
						if (zj>1)
						{
							s  =  s.replace("tk", "") ; 
							int x =  Integer.parseInt(s.split(" ")[0]); 
							int y =  Integer.parseInt(s.split(" ")[1]); 
							 
							tTank[zj-1]= new Tank(x,y,this,false,true,level);
						}
						zj++;
					
						 
						 
						 
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 
		    	PaintThread pt = new PaintThread();
				  th = new Thread(pt);
				RootThread root = new RootThread();
				  thr = new Thread(root);
				th.start();
				thr.start();
		        
		}
		if (e.getActionCommand().equals("help")) {
			 
			JOptionPane.showMessageDialog(null, "用→ ← ↑ ↓控制方向，空格键发射",
					"提示！", JOptionPane.INFORMATION_MESSAGE);
		 
		}
		if (e.getActionCommand().equals("cj")) {
			 cj = true;
			 JFileChooser chooser = new JFileChooser(".");  
		        chooser.showOpenDialog(this);  
		        String filePath = chooser.getSelectedFile().getAbsolutePath();  
		        System.out.println(filePath);  
		        BufferedReader bf = null;
		        String s = null;
		        int sl = 0;
		        int j=0;
				try {
					bf = new BufferedReader(new FileReader(filePath));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        try {
		        	 
		        	while((s = bf.readLine())!=null)
					{
						
						if (s.contains("cw"))
						{
							
							if (j==0)
							{
								
								sl = Integer.parseInt(s.replace("cw=", "")); 
								cw = new commonWall [sl];
								cws = sl;
								 
							}
							 
							
							if (j>0)
							{
								s  =  s.replace("cw", "") ; 
								int x =  Integer.parseInt(s.split(" ")[0]); 
								int y =  Integer.parseInt(s.split(" ")[1]); 
								 
								cw[j-1]= new commonWall(x,y);
							}
							j++;
						}
						if (s.contains("mw"))
						{
							if (j==0)
							{
								
								sl = Integer.parseInt(s.replace("mw=", "")); 
								 mw = new MetalWall[sl];
								mts = sl;
							}
							 
							
							if (j>0)
							{
								s  =  s.replace("mw", "") ; 
								int x =  Integer.parseInt(s.split(" ")[0]); 
								int y =  Integer.parseInt(s.split(" ")[1]); 
								 
								mw[j-1]= new MetalWall(x,y);
							}
							j++;
						}
						if (s.contains("r"))
						{
							if (j==0)
							{
								sl = Integer.parseInt(s.replace("r=", "")); 
								river = new River[sl];
						 
								rs = sl;
							}
							 
							
							if (j>0)
							{
								s  =  s.replace("r", "") ; 
								int x =  Integer.parseInt(s.split(" ")[0]); 
								int y =  Integer.parseInt(s.split(" ")[1]); 
								 
								river[j-1]= new River(x ,y);
							}
							j++;
						}
						 
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 
		}
		if (e.getActionCommand().equals("Stop")) {
			 
			 
				begin = false;
				
			 	 
			}
		if (e.getActionCommand().equals("Continue")) {
			 
			 
				begin = true;
				Thread th;
				Thread thr ;
				PaintThread pt = new PaintThread();
				  th = new Thread(pt);
				RootThread root = new RootThread();
				  thr = new Thread(root);
				th.start();
				 thr.start();
			 	 
			}  
		if (e.getActionCommand().equals("NewGame")) {
			 
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要开始新游戏！", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				fs = 0;
				aau.loop();
				level = 1;
				begin = true;
				tcs=10;
				tTank = new Tank[tcs];
				if (th!=null)
				{
					
					th.stop();
					thr.stop();
					 
				 
				}
				
				tTank[0]= new Tank(300,300,this,true,true,level);
				for (int i=1;i<10;i++)
				{
					tTank[i]= new Tank(70*i,0,this,false,true,level);
					
				}
				 
				begin = true;
				dz = false;
				PaintThread pt = new PaintThread();
				  th = new Thread(pt);
				RootThread root = new RootThread();
				  thr = new Thread(root);
				th.start();
				thr.start();
//				 
			}	 
			  
			}
		
		if (e.getActionCommand().equals("many")) {
			 
			Object[] options = { "白", "红" };
			int response = JOptionPane.showOptionDialog(this, "请选择一方", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				fs = 0;
				aau.loop();
				 
				begin = true;
			 
				tcs=1;
				dz = true;
				level =1 ;
				tTank = new Tank[5];
				tTank[0]= new Tank(300,300,this,true,true,level);
				 
				
				nc.connect("116.252.254.221", TankServer.Tcp_port);
				PaintThread pt = new PaintThread();
				 
				th = new Thread(pt);
				th.start();
			 
 				 
			}
			else{
				fs = 0;
				aau.loop();
				 
				begin = true;
				dz = true;
				tcs=1;
				level =1 ;
				tTank = new Tank[5];
				tTank[0]= new Tank(300,300,this,false,true,level);
				 
				
				nc.connect("116.252.254.221", TankServer.Tcp_port);
				PaintThread pt = new PaintThread();
				 
				th = new Thread(pt);
				th.start();
			}
			  
			}
		
		if (e.getActionCommand().equals("double")) {
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要开始新游戏！", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				doubleman  = true;
				fs = 0;
				aau.loop();
				level = 1;
				begin = true;
				tcs=2;
				
				 tTank = new Tank[tcs];
				 if (th!=null)
					{		
						th.stop();			 
					}
				
				 tTank[0]= new Tank(300,300,this,true,true,level);
					tTank[1]= new Tank(0,300,this,false,true,level);
				
					PaintThread pt = new PaintThread();
					  th = new Thread(pt);
				 
					th.start();
			
			}
		}
		
		if (e.getActionCommand().equals("level1")) {
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要开始新游戏！", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				
				fs = 0;
				aau.loop();
				level = 1;
				begin = true;
				tcs=10;
				 tTank = new Tank[tcs];
				 if (th!=null)
					{
						
						th.stop();
						thr.stop();
						 
					 
					}
				
				tTank[0]= new Tank(300,300,this,true,true,level);
				for (int i=1;i<10;i++)
				{
					tTank[i]= new Tank(70*i,0,this,false,true,level);
					
				}
				 
				begin = true;
				PaintThread pt = new PaintThread();
				  th = new Thread(pt);
				RootThread root = new RootThread();
				  thr = new Thread(root);
				th.start();
				thr.start();
//				 
			}	 
			  
			
			 
			 
					
				}
		if (e.getActionCommand().equals("top")) {
			DataCon dc= new DataCon( );
			dc.jiazai();
			dc.getConn();
			ResultSet rs = dc.selectSql();
			String name ="" ;
			String fs="" ;
			String ph = "";
			int   i=1;
			try {
				while (rs.next())
				{
					name =  rs.getString(1);
					
					fs =   rs.getString (2) ;
					ph  +="第"+i+"名 "+ name +fs +"\n";
					i++;
					
					 
				}
				
				Object[] options = { "确定" };
				int response = JOptionPane.showOptionDialog(this, ph, "前十名",
						JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						options, options[0]);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("level2")) {
			
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要开始新游戏！", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				fs = 0;
				aau.loop();
				begin = true;
				level = 2;
				tcs=20;
				 tTank = new Tank[tcs];
				 if (th!=null)
					{
						
						th.stop();
						thr.stop();
						 
					 
					}
				 
				tTank[0]= new Tank(300,300,this,true,true,level);
				for (int i=1;i<10;i++)
				{
					tTank[i]= new Tank(70*i,0,this,false,true,level);
					
				}
				int j=0;
				for (int i=10;i<20;i++)
				{
					tTank[i]= new Tank(70*j,600,this,false,true,level);
					j++;
				}
				begin = true;
				PaintThread pt = new PaintThread();
				  th = new Thread(pt);
				RootThread root = new RootThread();
				  thr = new Thread(root);
				th.start();
				thr.start();
//				 
			}	 
			  
		 
			 
					
				}
		if (e.getActionCommand().equals("level3")) {
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要开始新游戏！", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				fs = 0;
				level = 3;
				tcs=20;
				 tTank = new Tank[tcs];
				 if (th!=null)
					{
						
						th.stop();
						thr.stop();
						 
					 
					}
				 tTank[0]= new Tank(300,300,this,true,true,level);
					for (int i=1;i<10;i++)
					{
						tTank[i]= new Tank(70*i,0,this,false,true,level);
						
					}
					int j=0;
					for (int i=10;i<20;i++)
					{
						tTank[i]= new Tank(70*j,600,this,false,true,level);
						j++;
					}
					begin = true;
					PaintThread pt = new PaintThread();
					  th = new Thread(pt);
					RootThread root = new RootThread();
					  thr = new Thread(root);
					th.start();
					thr.start();
//					 
				 
				 
				 
				
//					 
				 
				
			}	 
			  
			 
		}
	 
			 
	}

}
 
