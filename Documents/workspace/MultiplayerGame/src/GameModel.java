import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;



public class GameModel extends Thread implements Serializable {
//Design philosophy:
ArrayList<PlayerMP> currentplayerlist = new ArrayList<PlayerMP>();
ArrayList<Player> accountlist = new ArrayList<Player>();
DatagramSocket awesome;

public GameModel()
{
	try {
		awesome = new DatagramSocket(1331);
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
boolean registerAccount(String username, String password)
{
	for(Player p : accountlist)
	{
		if(p.username.equals(username))
			return false;
	}
	accountlist.add(new Player(username, password));
	return true;
}
public void run()
{
	while(true)
	{
		//System.out.println("Server running?");
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
	    try {
	        awesome.receive(packet);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
	}
}

private void parsePacket(byte[] data, InetAddress ipAddress, int port) {
	// TODO Auto-generated method stub
	
	Types type = Packet.lookupPacket(Packet.getType(data));
	//System.out.println(type);
	switch(type){
	default:
	case INVALID:
		break;
	case UPDATE:
		Player p = new UpdatePacket(data).getPlayer();
		updateAll(p);
		break;
	case LOGIN:
		LoginPacket p2 = new LoginPacket(data);
		Player newpl = login(p2.getUsername(), p2.getPassword(), ipAddress, port);
		if(newpl != null)
		{
			
			//sendData(, ipAddress, port);
		}
		else
		{
			sendData(new LoginPacket().getData(), ipAddress, port);
		}
		break;
	case CREATE:
		CreatePacket p3 = new CreatePacket(data);
		if(registerAccount(p3.getUsername(), p3.getPassword()))
			sendData(data, ipAddress, port);
		else
			sendData(new CarnagePacket().data, ipAddress, port);
		break;
	case CARNAGE:
		CarnagePacket p4 = new CarnagePacket(data);
		if(remove(p4.getUsername(),p4.getPassword()))
			updateAll(data);
		break;
	}
	
	

}
/**
 * @param un
 * @param PW
 * @param ipAddress
 * @param port
 * @return true if success
 */
Player login(String un, String pw, InetAddress ipAddress, int port)
{
	if(un != null && pw != null)
		for(Player p : accountlist)
		{
			
			if(p.username.equals(un) && p.password.equals(pw))
			{
				for(PlayerMP pl: currentplayerlist)
					if(pl.username.equals(p.username))
					{
						return null;//already logged in
					}
				currentplayerlist.add(new PlayerMP(p, ipAddress, port, pw));
				
				updateAll(p);//The first update they recieve should be the account. What if they miss it? EDIT: don't do here. do in parent method.
				return p;
				
			}
		}
	return null;
}
void updateAll(Player p) {
	// TODO Auto-generated method stub
	UpdatePacket pct = new UpdatePacket(p);
	for(PlayerMP q: currentplayerlist)
	{
		System.out.println(q.ipAddress + ", " + q.port);
		//sendData(pct.data, q.ipAddress, q.port);
		try {
			sendData(pct.data, InetAddress.getByName("localhost"), q.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
void updateAll(byte[] data) {
	// TODO Auto-generated method stub
	for(PlayerMP q: currentplayerlist)
	{
		
		System.out.println(q.ipAddress + ", " + q.port);
		try {
			sendData(data, InetAddress.getByName("localhost"), q.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
boolean remove(String username, String password)
{
	for(PlayerMP p : currentplayerlist)
	{
		if(p.username.equals(username) && p.password.equals(password))
		{
			currentplayerlist.remove(p);//inefficient, but I can't be bothered.
			return true;
		}
	}
	return false;
}
boolean logout(String un)
{
	for(PlayerMP p : currentplayerlist)
	{
		if(p.username.equals(un))
		{
			p.logout();
			for(Player q: accountlist)
			{
				if(q.username.equals(p.username))
				{
					q = p; 
					updateAll(p);
					return true;
					
				}
			}
		}
	}
	return false;
}

public void sendData(byte[] data, InetAddress ipAddress, int port) {
    DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
    try {
        awesome.send(packet);
    } catch (IOException e) {
        e.printStackTrace();
    }
    
}
}
