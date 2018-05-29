import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankMove implements msg{
	int msgtype = msg.TANKMOVE;
	int id ;
	int x;
	int y;
	
	char fangxiang;
	  test test;
	public TankMove( int id , char fangxiang, int x, int y)
	{
		this.id = id ;
		this.fangxiang = fangxiang ;
		this.x = x ;
		this.y = y ;
	 
		
	}
	public TankMove(test tc) {
		this.test = tc;
		
	}
	 
	@Override
	public void send(DatagramSocket ds, String IP, int udpport) throws IOException {
	 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeInt(msgtype);
		dos.writeInt(id);
		dos.writeByte(fangxiang) ;
		dos.writeInt(x);
		dos.writeInt(y);
		byte[] buf =baos.toByteArray();
		DatagramPacket dp = new DatagramPacket(buf , buf.length,new InetSocketAddress(IP,TankServer.Udp_port));
		ds.send(dp);
	 
	}
	@SuppressWarnings("unused")
	@Override
	public void pass(DataInputStream dis) {
		 
		try {
			int id = dis .readInt();
			 
			
			if (test.tTank[0].ID==id)
			{
				return;
			}
			 
			char fangxiang = (char) dis.readByte() ;
			int x = dis .readInt();
			int y = dis .readInt();
			 boolean exist = false;
			 for (int i =1 ; i < test.tcs;i++)
			 {
				 Tank t = test.tTank[i];
				
				 
				 t.fangxiang = fangxiang ;
				 t.setx(x);
				 t.sety(y);
				 exist = true;
				 break;
			 }
			 
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		
	}
}
