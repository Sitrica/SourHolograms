package com.sitrica.sourholograms.objects;

import net.minecraft.network.protocol.Packet;
import net.minecraft.server.network.PlayerConnection;

public abstract class PlayerReference {

	protected final PlayerConnection connection;

	public PlayerReference(PlayerConnection connection) {
		this.connection = connection;
	}

	public void sendPacket(Packet<?> packet) {
		connection.a(packet);
	}

	protected abstract void sendPacket_i(Object packet);

}
