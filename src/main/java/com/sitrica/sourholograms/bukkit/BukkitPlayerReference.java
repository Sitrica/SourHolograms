package com.sitrica.sourholograms.bukkit;

import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.sitrica.sourholograms.objects.PlayerReference;

import net.minecraft.network.protocol.Packet;

public class BukkitPlayerReference extends PlayerReference {

	public BukkitPlayerReference(Player player) {
		super(((CraftPlayer) player).getHandle().b);
	}

	public void sendPacket(Packet<?> packet) {
		connection.a(packet);
	}

	@Override
	protected void sendPacket_i(Object packet) {
		sendPacket((Packet<?>) packet);
	}

}
