import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
 

public class TankServer {
	public static final int Tcp_port =8887;
	public static final int Udp_port =6667;
	ArrayList<Client> client = new ArrayList<Client>();
	private int ID=1;
	public void start ()//tcp
	{	System.out.println("`12");
		new Thread (new udpThreed()).start();//udp
		try {
			ServerSocket ss = new ServerSocket (Tcp_port);
			System.out.print(ss.getInetAddress()+"`12");
			while (true)
			{
				Socket s = ss.accept();
//System.out.println(s.getInetAddress()+" "+s.getPort());
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String IP = s.getLocalAddress().getHostAddress();
				int udpport = dis.readInt();
				System.out.println(udpport);
				Client c = new Client (IP,udpport);
				client.add(c);
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt(ID++);
				s.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("`12");
		new TankServer().start();
		
		

	}
	private class Client {
		String IP;
		int udpport;
		public Client (String IP,int udpport)
		{
			this.IP = IP;
			this.udpport = udpport;
		}
	}
	private class udpThreed implements Runnable 
	{
		byte[] buf = new byte [1024];
		
		DatagramSocket ds = null;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				  ds = new DatagramSocket(Udp_port);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (ds!=null)
			{
				DatagramPacket dp = new DatagramPacket(buf , buf.length);
				
				try {
					ds.receive(dp);
					for(int i=0;i<client.size();i++)
					{
						Client c = client.get(i);
						dp.setSocketAddress(new InetSocketAddress(c.IP,c.udpport));
						ds.send(dp);
					}
					 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
