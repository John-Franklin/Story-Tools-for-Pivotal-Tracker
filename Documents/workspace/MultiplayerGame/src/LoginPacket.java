import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class LoginPacket extends Packet{
	byte[] data;
	private String username;
	private String password;
	public LoginPacket(byte[] data) {
		// TODO Auto-generated constructor stub
		
		ByteArrayInputStream baos = new ByteArrayInputStream(data);
		ObjectInputStream ois;
		this.data = data;
		//System.out.println("From Input: " + data.length);
		try {
			ois = new ObjectInputStream(baos);
			//System.out.println();
			ois.readByte();
			username = (String)(ois.readObject());
			//System.out.println(username);
			password = (String)(ois.readObject());
			//System.out.println(password);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public LoginPacket(String username, String password) {
		// TODO Auto-generated constructor stub
		this(username, password, (byte) 2);
	}
	protected LoginPacket(String username, String password, byte defaultv)
	{
		this.username = username;
		this.password = password;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeByte((defaultv));
			oos.reset();
			oos.writeObject(username);
			oos.reset();
			oos.writeObject(password);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("From generator: " + baos.toByteArray().length);
		data = baos.toByteArray();
		
	}
	public LoginPacket() {
		// TODO Auto-generated constructor stub
		this(" or ", "invalid.");
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return data;
	}

}
