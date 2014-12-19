public enum Types{
		INVALID(-1), CREATE(00), UPDATE(01), LOGIN(02), CARNAGE(03);
		private int packetId;
		private Types(int id)
		{
			packetId = id;
		}
		int getId()
		{
			return packetId;
		}
	}