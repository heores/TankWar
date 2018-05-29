package Images;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class boom {
	static Image boom;
	int x,y;
	static 
	{
			try {
				 boom = ImageIO.read(new File ("Images/2.gif")); 
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
		}
	public boom(int x,int y)
	{
		this.x= x;
		this.y=y;
	}
public void paint(Graphics g){
		
		 
			 g.drawImage(boom,  x, y, null);
			 
	}
	
}
