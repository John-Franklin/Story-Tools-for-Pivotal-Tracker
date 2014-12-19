import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class UpdatePacketSmaller extends Packet {
	
	byte[] data;
	private Player player; 
	
	public UpdatePacketSmaller(byte[] data) {
		// TODO Auto-generated constructor stub
		System.out.println("from data: " + data.length);
		this.data = data;
		ByteArrayInputStream b = new ByteArrayInputStream(data);
		
		ObjectInputStream s = null;
		try {
			s = new ObjectInputStream(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(s.readByte());
			String workaround = (String)(s.readObject());
			String[] d = workaround.split(",");
			//Color c = new Color(s.readInt(),s.readInt(),s.readInt());
			
			//player = new Player(Integer.parseInt(d[2]),Integer.parseInt(d[3]), d[0], null, new Color(Integer.parseInt(d[4]),Integer.parseInt(d[5]),Integer.parseInt(d[6])));
			player = new Player(Integer.parseInt(d[2]),Integer.parseInt(d[3]), d[0], null);
			/*player.xv = Integer.parseInt(d[7]);
			player.yv = Integer.parseInt(d[8]);
			player.xa = Integer.parseInt(d[9]);
			player.ya = Integer.parseInt(d[10]);
			player.isLoggedIn = Boolean.parseBoolean(d[11]);*/
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public UpdatePacketSmaller(Player p)
	{
		player = p;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeByte((byte)1);
			oos.writeObject(p.username + "," + p.x + "," + p.y
					//+ "," + p.c.getRed() + "," + p.c.getGreen() + "," + p.c.getBlue() 
					//+ "," + p.xv + "," + p.yv + "," + p.xa + "," + p.ya +"," + p.isLoggedIn
					);
			oos.reset();
			oos.writeInt(p.y);
			oos.reset();
			/*oos.writeInt(p.c.getRed());
			oos.reset();
			oos.writeInt(p.c.getBlue());
			oos.reset();
			oos.writeInt(p.c.getGreen());*/
			/*oos.reset();
			oos.writeInt(p.xv);
			oos.reset();
			oos.writeInt(p.yv);
			oos.reset();
			oos.writeInt(p.xa);
			oos.reset();
			oos.writeInt(p.ya);
			oos.reset();
			oos.writeBoolean(p.isLoggedIn);*/
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = baos.toByteArray();
		System.out.println("from generator: " + data.length);
		System.out.println("from generator: " + new String(data));
	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return data;
	}

	public Player getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

}
