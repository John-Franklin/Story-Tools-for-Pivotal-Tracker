
public class CarnagePacket extends LoginPacket {

	public CarnagePacket(byte[] data) {
		// TODO Auto-generated constructor stub
		super(data);
	}
	public CarnagePacket(String username, String password)
	{
		super(username,password,(byte)3);
	}
	public CarnagePacket()
	{
		this(" or ", "invalid.");
	}
}
