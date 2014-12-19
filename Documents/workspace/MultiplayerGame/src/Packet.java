import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public abstract class Packet {
	
	public static int getType(byte[] data)
	{
		//byte temp[] = data;
		try {
			//System.out.println(new String(data));
			ObjectInputStream s = new ObjectInputStream(new ByteArrayInputStream(data.clone()));
			byte b = s.readByte();
			//System.out.print(b);
			//System.out.println(new String(data));
			
			return new ObjectInputStream(new ByteArrayInputStream(data)).readByte();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static Types lookupPacket(String packetId) {
        try {
            return lookupPacket(Integer.parseInt(packetId));
        } catch (NumberFormatException e) {
            return Types.INVALID;
        }
    }

    public static Types lookupPacket(int id) {
        for (Types p : Types.values()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return Types.INVALID;
    }
	public abstract byte[] getData();
	
}
