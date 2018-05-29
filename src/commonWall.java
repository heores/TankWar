import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class commonWall {
	private int x=0,y=0;
	static   Image MetalWallImage;
	boolean live = true;
	static  {
		try {
			MetalWallImage = ImageIO.read(new File ("Images/commonWall.gif"));
		 
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
}
	public commonWall(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g){
		  
		 g.drawImage(MetalWallImage,  x, y, null);
	}
	
	public int getx(){
		  
		 return this.x;
		 
	}
	

	public int gety(){
		  
		 return this.y;
		 
	}
}
