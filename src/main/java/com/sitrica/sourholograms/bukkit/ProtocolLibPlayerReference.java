package com.sitrica.sourholograms.bukkit;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;

public class ProtocolLibPlayerReference extends BukkitPlayerReference {

	protected final Player player;

	public ProtocolLibPlayerReference(Player player) {
		super(player);
		this.player = player;
	}

	public void sendPacket(PacketContainer packet) {
		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
		} catch (InvocationTargetException e) {
			SourHologramsBukkit.getInstance().getLogger().severe("Failed to send packet '" + packet.toString() + "' to the player " + player.getName());
		}
	}

	@Override
	public final void sendPacket_i(Object packet) {
		sendPacket((PacketContainer) packet);
	}

}
