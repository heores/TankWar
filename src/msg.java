import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramSocket;

public interface msg {
	public static final int TANKNEW = 1;
	public static final int TANKMOVE  = 2;
	public static final int BULLETNEW  = 3;
	
	public void send (DatagramSocket ds,String IP ,int udpport) throws IOException;
	public void pass(DataInputStream dis );
}
