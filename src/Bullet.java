import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Images.boom;

public class Bullet {
	static Image imgbulletU;
	static Image imgbulletD;
	static Image imgbulletL;
	static Image imgbulletR;
	private boolean isLive = true;
	boolean good;
	boolean boom=false;
	int x,y;
	test tk;
	char fangxiang;
	static  {
		try {
			 imgbulletU = ImageIO.read(new File ("Images/bulletU.gif"));
			 imgbulletD = ImageIO.read(new File ("Images/bulletD.gif"));
			 imgbulletL = ImageIO.read(new File ("Images/bulletL.gif"));
			 imgbulletR = ImageIO.read(new File ("Images/bulletR.gif"));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
	public   Bullet(int x,int y,char fangxiang,test tk,boolean good)
	{
		this.good = good;
		this.tk = tk;
		this.x = x+15;
		this.y=y+15;
		this.fangxiang = fangxiang;
	}
	public void paint(Graphics g){
		
		try
		{
			switch (fangxiang) {
			case 'L':
				 g.drawImage(imgbulletL,  x, y, null);
				break;
			case 'U':
				 
				 g.drawImage(imgbulletU,  x, y, null);
				break;
			case 'R':
				 g.drawImage(imgbulletR,  x, y, null);
				break;
			case 'D':
				 g.drawImage(imgbulletD,  x, y, null);
				break;
			 
			}
		}
		catch(Exception e)
		{
			 
			
		}
		 
		move();
		
	}
	
	public int getx(){
		  
		 return this.x;
		 
	}
	

	public int gety(){
		  
		 return this.y;
		 
	}
	public void move(){
		
		if (fangxiang == 'R')
		{
			 
			 
			this.x +=12;
		}
		if (fangxiang == 'U')
		{
			
			 
			this.y -=12;
		}	
		if (fangxiang == 'L')
		{
			 
			this.x -=12;
		}
		if (fangxiang == 'D')
		{
			 
			 
			this.y +=12;
		}

		if (this.x<0||this.x>800||this.y<0||this.y>600)
		{
			this.live(false);
	 
		} 
		//打到墙
		for (int i =0; i<tk.mts;i++)
		{
			if (this.x  >tk.mw[i].getx()&&this.x <tk.mw[i].getx()+33&&this.y  >tk.mw[i].gety()&&this.y <tk.mw[i].gety()+33)
			{
				this.live(false);
			}
		}
		//打到墙
		//打到水
		for (int i =0; i<tk.rs;i++)
		{
			if (this.x >tk.river[i].getx()&&this.x <tk.river[i].getx()+55&&this.y >tk.river[i].gety()&&this.y<tk.river[i].gety()+150)
			{
				this.live(false); 
			}
		}
		//打到水
		
		//能打墙
		for (int i =0; i<tk.cws;i++)
		{
			if (this.x >tk.cw[i].getx()&&this.x <tk.cw[i].getx()+21&&this.y >tk.cw[i].gety()&&this.y<tk.cw[i].gety()+21)
			{
				if (tk.cw[i].live == true)
				{
					this.live(false); 
					tk.cw[i].live = false;
				}
			}
		}
		for (int i =0; i<tk.tcs;i++)
		{
			
				
			if (tk.tTank[i].live)
			{
				if (this.x  >tk.tTank[i].getx()&&this.x <tk.tTank[i].getx()+33&&this.y  >tk.tTank[i].gety()&&this.y <tk.tTank[i].gety()+33)
				{
					if(good!=tk.tTank[i].good)
					{ 
						this.live(false);
						tk.tTank[i].live=false;
						
						boom=true;
						
						if (tk.tTank[i].good==false)
						{
							tk.fs ++;
						}
						if (tk.tTank[i].good )
						{
							tk.aau.stop();
							tk.aaus.play();
						}
					}
						
					}
				}
				
				 
			
			
		}
	}
	public void live(boolean live)
	{
		this.isLive=live;
	}
	public boolean getlive(){
		  
		 return this.isLive;
		 
	}
	public boolean boon()
	{
		return boom;
		 
	}
	
}
