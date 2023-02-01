package com.sitrica.sourholograms.nms;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketDataSerializer;

import java.util.UUID;

public class MinecraftByteBuffer extends PacketDataSerializer {

	public MinecraftByteBuffer() {
		super(Unpooled.buffer());
	}

	public void writeVarInt(int i) {
		super.d(i);
	}

	public void writeVarIntArray(int amount, int... array) {
		writeVarInt(amount);
		for (int i = 0; i < amount; i++)
			 writeVarInt(array[i]);
	}

	public void writeUUID(UUID uuid) {
		super.a(uuid);
	}

}
