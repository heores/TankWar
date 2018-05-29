 
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Tank extends JPanel {
	public static final char L = 'L';
	public static final char U = 'U';
	public static final char D = 'D';
	public static final char R = 'R';
	public static final char S = 'S';
	int ID;
	boolean good=true;
	boolean live = true;
	private int level;
	  int zds=0;
	test tk;
	Bullet[] bl =new Bullet[100];
	public final int width= 35,length = 35;
	public static  int speedX  , speedY  ;
	private int x, y;
	static Image imgtankU,imgtankD,imgtankL,imgtankR;
	static Image imghtankU,imghtankD,imghtankL,imghtankR;
	  char fangxiang = S;  
	private char zdfangxiang ;
	private boolean bL = false, bU = false, bR = false, bD = false,fS = false;
	private int oldx;
	private int oldy;
	private char Kfangxiang= U;
	static URL cb = null; 
	static AudioClip aau; 
	static {
		
		File f = new File("fs.wav"); //����������������ļ����ڵľ���¹��
		try {
			cb = f.toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		aau = Applet.newAudioClip(cb); 
	}
	static {
		try {
			 imgtankU = ImageIO.read(new File ("Images/tankU.gif"));
			 imgtankD = ImageIO.read(new File ("Images/tankD.gif"));
			 imgtankL = ImageIO.read(new File ("Images/tankL.gif"));
			 imgtankR = ImageIO.read(new File ("Images/tankR.gif"));
			 
			 
			 imghtankU = ImageIO.read(new File ("Images/htankU.gif"));
			 imghtankD = ImageIO.read(new File ("Images/htankD.gif"));
			 imghtankL = ImageIO.read(new File ("Images/htankL.gif"));
			 imghtankR = ImageIO.read(new File ("Images/htankR.gif"));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
	
	
	public Tank(int x, int y,test tk,boolean good,boolean live,int level) {// Tank�Ĺ��캯��1
		 
		this.x = x;
		this.y = y;
		this.tk = tk;
		this.good= good;
		this.live = live;
		this.level = level;
		
		
		
		
		
		  
	}
		
		
		
	 
	
	public void paint(Graphics g) {
		 
		if(live==false)
			return;
		
			 
		
		
		 switch (Kfangxiang) {  //ѡ���ƶ�����
			case L:
				if (good)
				{
					 g.drawImage(imgtankL,  x, y, null);
				}
				else
					 g.drawImage(imghtankL,  x, y, null);
				 
				 zdfangxiang = 'L';
				break;
			case U:
				if (good)
				{
					 g.drawImage(imgtankU,  x, y, null);
				}
				else
					 g.drawImage(imghtankU,  x, y, null);
				 zdfangxiang = 'U';
				break;
			case R:
				if (good)
				{
					 g.drawImage(imgtankR,  x, y, null);
				}
				else
					 g.drawImage(imghtankR,  x, y, null);
				 zdfangxiang = 'R';
				break;
			case D:
				if (good)
				{
					 g.drawImage(imgtankD,  x, y, null);
				}
				else
					 g.drawImage(imghtankD,  x, y, null);
				 zdfangxiang = 'D';
				break;
			 
			}
		 
		 move();
		}

	public void keyReleased(KeyEvent e) {
		// TODO �Զ����ɵķ������
		int key = e.getKeyCode();
	
		if (tk.doubleman == true)
		{
			if (good)
			{
				switch (key) {
				case KeyEvent.VK_RIGHT: //�������Ҽ�
					bR = false;
					break;
					
				case KeyEvent.VK_LEFT://���������
					bL = false;
					break;
				
				case KeyEvent.VK_UP:  //�������ϼ�
					bU = false;
					break;
				
				case KeyEvent.VK_DOWN://�������¼�
					bD = false;
					break;
				case KeyEvent.VK_SHIFT://�������¼�
					if (live)
					{
						aau.stop();
						aau.play();
						
						bl[zds]= fase();
						zds = zds+1;
					}
					break;
				
				 
				}
				decideDirection();
			}
			else
			{
				switch (key) {
				case KeyEvent.VK_D: //�������Ҽ�
					bR = false;
					break;
					
				case KeyEvent.VK_A://���������
					bL = false;
					break;
				
				case KeyEvent.VK_W:  //�������ϼ�
					bU = false;
					break;
				
				case KeyEvent.VK_S://�������¼�
					bD = false;
					break;
				case KeyEvent.VK_J://�������¼�
					if (live)
					{
						aau.stop();
						aau.play();
						
						bl[zds]= fase();
						zds = zds+1;
					}
					break;
				
				 
				}
				decideDirection();
			}
		}
		else
		{
			switch (key) {
			case KeyEvent.VK_RIGHT: //�������Ҽ�
				bR = false;
				break;
				
			case KeyEvent.VK_LEFT://���������
				bL = false;
				break;
			
			case KeyEvent.VK_UP:  //�������ϼ�
				bU = false;
				break;
			
			case KeyEvent.VK_DOWN://�������¼�
				bD = false;
				break;
			case KeyEvent.VK_SPACE://�������¼�
				if (live)
				{
					aau.stop();
					aau.play();
					
					bl[zds]= fase();
					zds = zds+1;
				}
				break;
			 
			}
			decideDirection();
		}
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO �Զ����ɵķ������
		int key = e.getKeyCode();
		 
		if (tk.doubleman == true)
		{
			if (good == true)
			{
				switch (key) {
				case KeyEvent.VK_RIGHT: //�������Ҽ�
					bR = true;
					break;
					
				case KeyEvent.VK_LEFT://���������
					bL = true;
					break;
				
				case KeyEvent.VK_UP:  //�������ϼ�
					bU = true;
					break;
				
				case KeyEvent.VK_DOWN://�������¼�
					bD = true;
					break;
				case KeyEvent.VK_SHIFT://�������¼�
					 fS = true;
					break;
				}
				decideDirection();
			}
			else
			{
				switch (key) {
				case KeyEvent.VK_D: //�������Ҽ�
					bR = true;
					break;
					
				case KeyEvent.VK_A://���������
					bL = true;
					break;
				
				case KeyEvent.VK_W:  //�������ϼ�
					bU = true;
					break;
				
				case KeyEvent.VK_S://�������¼�
					bD = true;
					break;
				case KeyEvent.VK_J://�������¼�
					 fS = true;
					break;
				}
				decideDirection();
			}
		}
		else
		{
			
			switch (key) {
			case KeyEvent.VK_RIGHT: //�������Ҽ�
				bR = true;
				break;
				
			case KeyEvent.VK_LEFT://���������
				bL = true;
				break;
			
			case KeyEvent.VK_UP:  //�������ϼ�
				bU = true;
				break;
			
			case KeyEvent.VK_DOWN://�������¼�
				bD = true;
				break;
			case KeyEvent.VK_SPACE://�������¼�
				 fS = true;
				break;
			}
			decideDirection();
			
			}
		}
	 
		
		
	 

	  void decideDirection() {
		  char oldfangxiang = this.fangxiang;
		  if (!bL && !bU && bR && !bD)  //�����ƶ�
			fangxiang = R;
			
		else if (bL && !bU && !bR && !bD)   //������
			fangxiang = L;

		else if (!bL && bU && !bR && !bD)  //�����ƶ�
			fangxiang =  U;

		else if (!bL && !bU && !bR && bD) //�����ƶ�
			fangxiang =  D;

		else if (!bL && !bU && !bR && !bD)
			fangxiang =  S;  //û�а������ͱ��ֲ���
		
		
		if (fangxiang != oldfangxiang )
		{
			 
			TankMove msg = new TankMove ( ID , fangxiang,x,y);
			tk.nc.send(msg);
		}
	}
	  
	  void move() {
		  if (good)
			{
				speedX = 6;
				speedY = 6;
				
			}
		  if(!good)
			{
				if (level==1)
				{
					speedX = 6;
					speedY = 6;
				}
				if (level  == 2)
				{
					speedX = 10;
					speedY = 12;
					
				}
				 if (level == 3)
				 {
					 speedX = 17;
					 speedY = 17;
				 }
			}
		  oldx = this.getx();
			oldy = this.gety();
		  

			switch (fangxiang) {  //ѡ���ƶ�����
			case L:
				x -= speedX;
				break;
			case U:
				y -= speedY;
				break;
			case R:
				x += speedX;
				break;
			case D:
				y += speedY;
				break;
			case S:
				break;
			}
			if (this.fangxiang != S) {
				this.Kfangxiang = this.fangxiang;
			}
			
			
			
			if (x<0)
			  {  x=0;
				  return;
			  }
			  if (x>800-35)
			  {  x=800-35;
				  return;
			  }
			  if (y<0+35)
			  {  y=0+35;
				  return;
			  }
			  if (y>600-35)
			  {  y=600-35;
				  return;
			  }
	  }

		public void setx(int x){
			  
			 this .x = x;
			 
			 
		}
		public void setfangxiang(char fx){
			  
			 this.fangxiang = fx;
			 
			 
		}
		public void sety(int y){
			  
			 this .y = y;
			 
			 
		}
		public int getx(){
			  
			 return this.x;
			 
		}
		

		public int gety(){
			  
			 return this.y;
			 
		}
		
		public void peng(){
		 x = oldx;
		 y=oldy;
		}
		public char getfangxiang(){
			  
			 return this.zdfangxiang;
			 
		}
		public Bullet fase ()
		{
			Bullet zd =  new Bullet(x,y,zdfangxiang,tk,good);
			System.out.println(tk.tTank[0].ID+"##");
			Bulletnewmeg msg = new Bulletnewmeg (zd,tk.tTank[0].ID);
			tk.nc.send(msg);
			return zd;
		}
		 
}
	

 
