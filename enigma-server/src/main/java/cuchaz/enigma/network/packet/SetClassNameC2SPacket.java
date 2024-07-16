package cuchaz.enigma.network.packet;

import cuchaz.enigma.network.EnigmaServer;
import cuchaz.enigma.network.ServerPacketHandler;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class SetClassNameC2SPacket implements Packet<ServerPacketHandler> {

	private String className;

	public SetClassNameC2SPacket() {
	}

	public SetClassNameC2SPacket(String className) {
		this.className = className;
	}

	@Override
	public void read(DataInput input) throws IOException {
		this.className = PacketHelper.readString(input);
	}

	@Override
	public void write(DataOutput output) throws IOException {
		if (this.className == null) {
			this.className = "";
		}
		PacketHelper.writeString(output, this.className);
	}

	@Override
	public void handle(ServerPacketHandler handler) {

		EnigmaServer server = handler.getServer();
		server.setClassName(handler.getClient(), this.className);
		server.updateUsernames();
	}

}
