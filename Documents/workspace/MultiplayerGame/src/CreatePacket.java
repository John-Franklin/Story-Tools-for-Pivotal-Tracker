
public class CreatePacket extends LoginPacket {
	
	public CreatePacket(String username, String pw) {
		// TODO Auto-generated constructor stub
		super(username, pw, (byte)0);
		//data[0] = 0;
	}
	public CreatePacket(byte[] data)
	{
		super(data);
	}
	public CreatePacket() {
		// TODO Auto-generated constructor stub
		super(" or ", "invalid.", (byte)0);
		
	}
	
	
}
