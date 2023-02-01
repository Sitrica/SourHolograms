package com.sitrica.sourholograms.bukkit.nms;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.sitrica.sourholograms.Platform.PlatformNMS;
import com.sitrica.sourholograms.bukkit.BukkitEntityReference;
import com.sitrica.sourholograms.bukkit.BukkitPlayerReference;
import com.sitrica.sourholograms.bukkit.SourHologramsBukkit;
import com.sitrica.sourholograms.bukkit.objects.BukkitPosition;
import com.sitrica.sourholograms.nms.NMS;
import com.sitrica.sourholograms.objects.PlayerReference;
import com.sitrica.sourholograms.objects.Position;

import io.netty.channel.Channel;

/**
 * Class that represents all the methods to implement
 * for each of the supported versions of the plugin.
 */
public class BukkitNMS implements PlatformNMS<BukkitPlayerReference> {

	protected static NMS<PlayerReference> nms;
	private static TinyProtocol protocol;
	protected static BukkitNMS INSTANCE;

	/**
	 * To be implemented by an NMS object with version support.
	 * 
	 * @param plugin The plugin that is registering the NMS.
	 * @param nms The NMS implementation for the version.
	 */
	private BukkitNMS(SourHologramsBukkit plugin, NMS<PlayerReference> nms) {
		BukkitNMS.nms = nms;
		BukkitNMS.protocol = new TinyProtocol(plugin) {

			@Override
			public Object onPacketInAsync(Player sender, Channel channel, Object packet) {
				return nms.onPacketInAsync(getReference(sender)[0], packet);
			}

			@Override
			public Object onPacketOutAsync(Player reciever, Channel channel, Object packet) {
				return nms.onPacketOutAsync(getReference(reciever)[0], packet);
			}

		};
	}

	public static final BukkitNMS get(SourHologramsBukkit plugin, NMS<PlayerReference> nms) {
		if (INSTANCE != null)
			return INSTANCE;
		INSTANCE = new BukkitNMS(plugin, nms);
		return INSTANCE;
	}

	/**
	 * Close the netty listener for packets.
	 */
	public void close() {
		if (protocol != null)
			protocol.close();
	}

	protected BukkitPlayerReference[] getReference(Player... players) {
		BukkitPlayerReference[] references = new BukkitPlayerReference[players.length];
		for (int i = 0; i < players.length; i++)
			references[i] = new BukkitPlayerReference(players[i]);
		return references;
	}

	@Override
	@SuppressWarnings("unchecked")
	public BukkitEntityReference getNextEntityReference() {
		return BukkitEntityReference.get(nms.getNextEntityId());
	}

	/**
	 * Spawn a fake armor stand at the defined location
	 * with attributes to the defined players.
	 * 
	 * @param entity The BukkitEntityReference that represents the ID and UUID of the entity.
	 * @param location The location to spawn the entity at.
	 * @param small Boolean if the armor stand should have the small attribute.
	 * @param invisible Boolean if the armour stand should have the invisible attribute.
	 * @param players The players to send this armor stand to.
	 */
	public void showFakeArmorStand(BukkitEntityReference entity, Location location, boolean small, boolean invisible, Player... players) {
		nms.showFakeArmorStand(entity, BukkitPosition.fromLocation(location), small, invisible, getReference(players));
	}

	/**
	 * Spawn a fake armor stand at the defined location
	 * with attributes to the defined players. This will use the next entityId.
	 * 
	 * @param location The location to spawn the entity at.
	 * @param small Boolean if the armor stand should have the small attribute.
	 * @param invisible Boolean if the armour stand should have the invisible attribute.
	 * @param players The players to send this armor stand to.
	 * @return The BukkitEntityReference that was used to create the armor stand.
	 */
	public BukkitEntityReference showFakeArmorStand(Location location, boolean small, boolean invisible, Player... players) {
		return showFakeArmorStand(BukkitPosition.fromLocation(location), small, invisible, getReference(players));
	}

	@Override
	public BukkitEntityReference showFakeArmorStand(Position position, boolean small, boolean invisible, BukkitPlayerReference... players) {
		BukkitEntityReference entity = getNextEntityReference();
		nms.showFakeArmorStand(entity, position, small, invisible, players);
		return entity;
	}

	/**
	 * Spawn a fake entity at the defined location
	 * to the defined players.
	 * 
	 * @param entityType The EntityType that this entity represents.
	 * @param entity The BukkitEntityReference that represents the ID and UUID of the entity.
	 * @param location The location to spawn the entity at.
	 * @param players The players to send this entity to.
	 */
	@SuppressWarnings("deprecation")
	public void showFakeEntity(EntityType entityType, BukkitEntityReference entity, Location location, Player... players) {
		nms.showFakeEntity(entityType.getTypeId(), entity, BukkitPosition.fromLocation(location), getReference(players));
	}

	/**
	 * Spawn a fake entity at the defined location
	 * to the defined players.
	 * 
	 * @param entityType The EntityType that this entity represents.
	 * @param location The location to spawn the entity at.
	 * @param players The players to send this entity to.
	 * @return The entityId that was used to create the living entity.
	 */
	@SuppressWarnings("deprecation")
	public BukkitEntityReference showFakeEntity(EntityType entityType, Location location, Player... players) {
		return showFakeEntity(entityType.getTypeId(), BukkitPosition.fromLocation(location), getReference(players));
	}

	@Override
	public BukkitEntityReference showFakeEntity(int entityType, Position position, BukkitPlayerReference... players) {
		BukkitEntityReference entity = getNextEntityReference();
		nms.showFakeEntity(entityType, entity, position, players);
		return entity;
	}

	/**
	 * Set the custom name of a fake entity to the defined players.
	 * 
	 * @param entity The BukkitEntityReference that represents the ID and UUID of the entity.
	 * @param name The name to update the entity to.
	 * @param players The players to send the custom name update to.
	 */
	public void updateFakeEntityCustomName(BukkitEntityReference entity, String name, Player... players) {
		nms.updateFakeEntityCustomName(entity, name, getReference(players));
	}

	/**
	 * Teleports a fake entity to a location for the defined players.
	 * 
	 * @param entity The BukkitEntityReference that represents the ID and UUID of the entity.
	 * @param location The location to teleport the entity to.
	 * @param players The players to send this teleport effect to.
	 */
	public void teleportFakeEntity(BukkitEntityReference entity, Location location, Player... players) {
		nms.teleportFakeEntity(entity, BukkitPosition.fromLocation(location), getReference(players));
	}

	/**
	 * Makes an entity appear to be riding another entity to the defined players.
	 * 
	 * @param entity The BukkitEntityReference that represents the ID and UUID of the entity.
	 * @param vehicle The BukkitEntityReference that represents the ID and UUID of the vehicle.
	 * @param players The players to send this teleport effect to.
	 */
	public void rideFakeEntity(BukkitEntityReference entity, BukkitEntityReference vehicle, Player... players) {
		nms.rideFakeEntity(entity, vehicle, getReference(players));
	}

	/**
	 * Hide fake entities to the defined players.
	 * 
	 * @param entities Array containing BukkitEntityReference of all the entities.
	 * @param players The players to send this hide effect to.
	 */
	public void hideFakeEntities(BukkitEntityReference[] entities, Player... players) {
		nms.hideFakeEntities(entities, getReference(players));
	}

	/**
	 * Hides a single entity to the defined players.
	 * 
	 * @param entity The BukkitEntityReference that represents the ID and UUID of the entity.
	 * @param players The players to send this hide effect to.
	 */
	public void hideFakeEntity(BukkitEntityReference entity, Player... players) {
		nms.hideFakeEntities(new BukkitEntityReference[] {entity}, getReference(players));
	}

}
