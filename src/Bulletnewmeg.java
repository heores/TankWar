import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Bulletnewmeg implements msg{
	int msgtype = msg.BULLETNEW;
	test test;
	Bullet b ;
	int ID ;
	
	public Bulletnewmeg (Bullet b,int  ID)
	{
		this .b = b;
		this.ID = ID ;
		
	}
	public Bulletnewmeg (test test)
	{
		this .test = test; 	
		
	}
	@Override
	public void send(DatagramSocket ds, String IP, int udpport) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeInt(msgtype);
		dos.writeInt(ID);
		dos.writeInt(b.getx());
		dos.writeInt(b.gety());
		dos.writeByte(b.fangxiang) ;
		dos.writeBoolean(b.good);
		 
	 
		
		
		
		byte[] buf =baos.toByteArray();
		DatagramPacket dp = new DatagramPacket(buf , buf.length,new InetSocketAddress(IP,TankServer.Udp_port));
		ds.send(dp);
		
	}

	@Override
	public void pass(DataInputStream dis) {
		try {
			int id = dis .readInt();
			int x = dis .readInt();
			int y = dis .readInt();
			char fangxiang = (char) dis.readByte() ;
			boolean good = dis .readBoolean();
			boolean exist = false;
			 
			for (int i = 0 ; i < test.tcs;i++)
			{
				System.out.println(test.tTank[i].ID+" "+id);
				if (id ==  test.tTank[i].ID&&id!= test.tTank[0].ID)
				{
					
					 Bullet b = new Bullet(x-15, y-20, fangxiang, test, good);
					 test.tTank[i] .zds++;
					 test.tTank[i].bl[test.tTank[i] .zds-1] = b ;
				}
			}
			 
			
			 
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		
	}
		
	 

}
