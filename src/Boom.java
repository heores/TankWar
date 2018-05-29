import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boom {
	static Image boom;
	int x,y;
	static 
	{
		try {
			 	boom = ImageIO.read(new File ("Images/4.gif")); 
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}		
	}
	public Boom(int x,int y)
	{
		this.x= x;
		this.y=y;
	}
	
public void paint(Graphics g){
		
		 
			 g.drawImage(boom,  x, y, null);
			 
	}
public void setx(int x){
	this.x = x+15;
}
public void sety(int y){
	this.y = y-8;
}
}
