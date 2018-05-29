import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankMesssage implements msg{
	int msgtype = msg.TANKNEW;
	Tank tank;
	test test;
	NetClient netClient;
//	public TankMesssage(Tank tank, NetClient netClient  )
//	{
//		this.tank = tank;
//		this.netClient = netClient;
//	}
	public TankMesssage(test test)
	{
		this.test = test;
		
	}
	public TankMesssage(Tank tank  )
	{
		this.tank = tank;
	 
	}
	public void send(DatagramSocket ds,String IP ,int udpport) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeInt(msgtype);
		dos.writeInt(tank.ID);
		dos.writeInt(tank.getx());
		dos.writeInt(tank.gety());
		dos.writeByte(tank.fangxiang) ;
		dos.writeBoolean(tank.live);
		dos.writeBoolean(tank.good);
	 
		
		
		
		byte[] buf =baos.toByteArray();
		DatagramPacket dp = new DatagramPacket(buf , buf.length,new InetSocketAddress(IP,TankServer.Udp_port));
		ds.send(dp);
	}
	public void pass(DataInputStream dis ) {
		 
		
		try {
			
			int id = dis .readInt();
			
			
			if (test.tTank[0].ID==id)
			{
				return;
			}
			
			
			
			int x = dis .readInt();
			int y = dis .readInt();
			char fangxiang = (char) dis.readByte() ;
			boolean live = dis.readBoolean();
			boolean good = dis.readBoolean();
			
			boolean exist = false ;
			for (int i = 0 ;i<test.tcs;i++)
			{
				Tank t = test.tTank[i];
				if (t.ID==id)
				{
					exist = true;
					break;
				}
			}
			if (!exist)
			{
				TankMesssage tkm  = new TankMesssage(test.tTank[0]);
				test.nc.send(tkm);
				Tank t = new Tank (x,y,test,good,live,1);
				System.out.println(t.ID+"##");
				t.ID = id;
				test.tcs++;
				test.tTank[test.tcs-1 ]= t;
				
			}
			
			
			
			
			
			
			
			
			
			
			 
			
			
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	
		
	}
}
