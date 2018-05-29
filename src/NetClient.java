import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
 

public class NetClient {
	private static int udpport = 7777;
	private int udp_port;
	test tc;
	DatagramSocket ds;
	int ID;
	public NetClient (test tc )
	{
		udp_port = udpport ;
		this.tc = tc;
		try {
			ds = new DatagramSocket (udp_port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void connect (String IP,int port)
	{
		try {
			Socket s = new Socket(IP,port);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeInt(udpport);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			
			int id  = dis.readInt();
			
			 ID = id ;
			
			
			s.close();
		} catch (IOException e) {
 			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		TankMesssage tkm  = new TankMesssage(tc.tTank[0]);
		tc.tTank[0].ID = ID;
		 
		send(tkm);
		new Thread (new udprecThread()).start(); 
	}
	 
	
	public void send (msg msg)
	{
		try {
			msg.send(ds,"127.0.0.1",udp_port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	private class udprecThread implements Runnable {
		byte[] buf = new byte [1024];
		public void run() {
			
			while (ds!=null)
			{
				DatagramPacket dp = new DatagramPacket(buf , buf.length);
				
				try {
					ds.receive(dp);
					pass(dp);
 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
	}
		public void pass(DatagramPacket dp) {
			// TODO Auto-generated method stub
			ByteArrayInputStream bais = new ByteArrayInputStream(buf ,0, dp.getLength());
			DataInputStream dis = new DataInputStream(bais);
			int msgtype = 0;
			try {
				msgtype = dis.readInt();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg  Msg = null ;
			 
			switch (msgtype)
			{
			case 1:
				
				
				
				Msg = new TankMesssage( tc);
				Msg.pass(dis);
				break;
			case 2:
				
				Msg = new TankMove( tc);
				Msg.pass(dis);
				break;
			case 3:
				
				Msg = new Bulletnewmeg( tc);
				Msg.pass(dis);
				break;
			}
			
			 
			
			
		}

	}
	
}
