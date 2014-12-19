import java.awt.Color;
import java.net.InetAddress;
import java.util.ArrayList;


public class PlayerMP extends Player{
	ArrayList<Player> modelCopy;
	InetAddress ipAddress;//IP address of server.
	int port;
	PlayerMP(int x, int y, InetAddress ipAddress, int port, String usrname, String pw, Color color) {
		super(x, y, usrname, pw, color);
		// TODO Auto-generated constructor stub
		this.ipAddress = ipAddress;
		this.port = port;
		this.modelCopy = new ArrayList<Player>();
		modelCopy.add(this);
	}
	PlayerMP(Player p, InetAddress ipAddress, int port)
	{
		super(p.x, p.y, p.username, p.password, p.c);

		this.ipAddress = ipAddress;
		this.port = port;
		this.modelCopy = new ArrayList<Player>();
		modelCopy.add(this);
	}
	PlayerMP(Player p, InetAddress ipAddress, int port, String arg0)
	{
		this(p, ipAddress, port);
	}
/**
 * do any actual updating in the GamePane class?
 * @param newModelCopy
 * @return
 */
boolean updateModel(Player updatedPlayer)
{
	if(!updatedPlayer.username.equals(username))
	{
		for(int i = 0; i < modelCopy.size(); i++)
		{
			if(modelCopy.get(i).username.equals(updatedPlayer.username))
			{
				modelCopy.set(i, updatedPlayer);
				return true;
			}
		}
		modelCopy.add(updatedPlayer);
	}
	return false;
}
//returns false if usernametodie is the player
boolean killFromModel(String usernametodie)
{
	for(Player p: modelCopy)
	{
		if(p.username.equals(usernametodie) && !p.username.equals(username))
		{
			modelCopy.remove(p);
		}
		
	}
	return false;
}
void extrapolate()
{
	for(Player p: modelCopy)
	{
		System.out.println(p.username);
		System.out.println("x: "+p.x);
		System.out.println("y: "+p.y);
		System.out.println("xv: "+p.xv);
		System.out.println("yv: "+p.yv);
		System.out.println("xa: "+p.xa);
		System.out.println("ya: "+p.ya);
		p.xv += p.xa;
		p.yv += p.ya;
		if(Math.sqrt(p.xv * p.xv + p.yv * p.yv) > Player.defaultTileGroup.getMaxSpeed(p))
		{
			p.scaleVelocity(Player.defaultTileGroup.getMaxSpeed(p));
		}
		p.x += xv;
		p.y += yv;
		
	}
	
}
}
