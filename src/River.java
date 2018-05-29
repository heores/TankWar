import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class River {
	private int x=0,y=0;
	static   Image riverImage;
	static  {
		try {
			riverImage = ImageIO.read(new File ("Images/river.jpg"));
		 
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
}
	 
	 
	public River(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g){
		  
		 g.drawImage(riverImage,  x, y, null);
	}
	public int getx(){
		  
		 return this.x;
		 
	}
	

	public int gety(){
		  
		 return this.y;
		 
	}

}
