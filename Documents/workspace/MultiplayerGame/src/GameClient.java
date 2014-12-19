import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class GameClient extends Thread{
	DatagramSocket awesome;
	private InetAddress ipAddress;//This is the recieving address, not yours. :P
	PlayerMP playerMP;
	String username;
	static int port = 1331;
	public GameClient(String ipAddress, PlayerMP playerMP, int yourport) {
		// TODO Auto-generated constructor stub
		this.playerMP = playerMP;
		try {
			awesome = new DatagramSocket(yourport);
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run()
	{
		while(playerMP.isLoggedIn)
		{
			byte[] data = new byte[1024];
			DatagramPacket p = new DatagramPacket(data, data.length);
			
			try {
				awesome.receive(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parsePacket(data);
		}
		
	}
	/**
	 * Packet types:
	 * 
	 *
	 * @param data
	 */
	private void parsePacket(byte[] data) {
		// TODO Auto-generated method stub
		Types type = Packet.lookupPacket(Packet.getType(data));
		switch(type){
		default:
		case INVALID:
			break;
		case UPDATE:
			System.out.print("Recieved");
			Player p = new UpdatePacket(data).getPlayer();
			playerMP.updateModel(p);
			break;
		case CARNAGE:
			CarnagePacket p2 = new CarnagePacket(data);
			if(p2.getUsername().equals(playerMP.username) && p2.getPassword().equals(playerMP.password))
			{
				playerMP.isLoggedIn = false;
				
			}
			break;
		}
		
	}
	public void sendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            awesome.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
