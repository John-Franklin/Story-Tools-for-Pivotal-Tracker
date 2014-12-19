import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class UpdatePacket extends Packet {
	
	byte[] data;
	private Player player; 
	
	public UpdatePacket(byte[] data) {
		// TODO Auto-generated constructor stub
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
			s.readByte();
			player = (Player)s.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public UpdatePacket(Player p)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeByte((byte)1);
			oos.reset();
			oos.writeObject(p);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = baos.toByteArray();
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
