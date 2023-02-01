package com.sitrica.sourholograms.bukkit.nms;

import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.comphenix.packetwrapper.WrapperPlayServerSpawnEntity;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.sitrica.sourholograms.Platform.PlatformNMS;
import com.sitrica.sourholograms.bukkit.BukkitEntityReference;
import com.sitrica.sourholograms.bukkit.ProtocolLibPlayerReference;
import com.sitrica.sourholograms.bukkit.SourHologramsBukkit;
import com.sitrica.sourholograms.bukkit.nms.Reflection.FieldAccessor;
import com.sitrica.sourholograms.bukkit.objects.BukkitPosition;
import com.sitrica.sourholograms.nms.NMS;
import com.sitrica.sourholograms.objects.EntityReference;
import com.sitrica.sourholograms.objects.Position;

import net.minecraft.world.entity.Entity;

public class ProtocolLibNMS_1_19_R2 implements PlatformNMS<ProtocolLibPlayerReference>, NMS<ProtocolLibPlayerReference> {

	private static final FieldAccessor<AtomicInteger> ENTITY_ID_COUNTER = Reflection.getField(Entity.class, "c", AtomicInteger.class);
	private static final ProtocolManager manager = ProtocolLibrary.getProtocolManager();
	protected static ProtocolLibNMS_1_19_R2 INSTANCE;

	private ProtocolLibNMS_1_19_R2(SourHologramsBukkit plugin) {
		INSTANCE = this;
		manager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL) {

			@Override
			public void onPacketReceiving(PacketEvent event) {
				onPacketInAsync(getReference(event.getPlayer())[0], event);
			}

			@Override
			public void onPacketSending(PacketEvent event) {
				onPacketOutAsync(getReference(event.getPlayer())[0], event);
			}

		});
	}

	public static final ProtocolLibNMS_1_19_R2 get(SourHologramsBukkit plugin) {
		if (INSTANCE != null)
			return INSTANCE;
		INSTANCE = new ProtocolLibNMS_1_19_R2(plugin);
		return INSTANCE;
	}

	protected ProtocolLibPlayerReference[] getReference(Player... players) {
		ProtocolLibPlayerReference[] references = new ProtocolLibPlayerReference[players.length];
		for (int i = 0; i < players.length; i++)
			references[i] = new ProtocolLibPlayerReference(players[i]);
		return references;
	}

	@Override
	public final int getNextEntityId() {
		return ENTITY_ID_COUNTER.getStatic().incrementAndGet();
	}

	@Override
	@SuppressWarnings("unchecked")
	public BukkitEntityReference getNextEntityReference() {
		return BukkitEntityReference.get(getNextEntityId());
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
		showFakeArmorStand(entity, BukkitPosition.fromLocation(location), small, invisible, getReference(players));
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
	public BukkitEntityReference showFakeArmorStand(Position position, boolean small, boolean invisible, ProtocolLibPlayerReference... players) {
		BukkitEntityReference entity = getNextEntityReference();
		showFakeArmorStand(entity, position, small, invisible, players);
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
		showFakeEntity(entityType.getTypeId(), entity, BukkitPosition.fromLocation(location), getReference(players));
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
	public BukkitEntityReference showFakeEntity(int entityType, Position position, ProtocolLibPlayerReference... players) {
		BukkitEntityReference entity = getNextEntityReference();
		showFakeEntity(entityType, entity, position, players);
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
		updateFakeEntityCustomName(entity, name, getReference(players));
	}

	/**
	 * Teleports a fake entity to a location for the defined players.
	 * 
	 * @param entity The BukkitEntityReference that represents the ID and UUID of the entity.
	 * @param location The location to teleport the entity to.
	 * @param players The players to send this teleport effect to.
	 */
	public void teleportFakeEntity(BukkitEntityReference entity, Location location, Player... players) {
		teleportFakeEntity(entity, BukkitPosition.fromLocation(location), getReference(players));
	}

	/**
	 * Makes an entity appear to be riding another entity to the defined players.
	 * 
	 * @param entity The BukkitEntityReference that represents the ID and UUID of the entity.
	 * @param vehicle The BukkitEntityReference that represents the ID and UUID of the vehicle.
	 * @param players The players to send this teleport effect to.
	 */
	public void rideFakeEntity(BukkitEntityReference entity, BukkitEntityReference vehicle, Player... players) {
		rideFakeEntity(entity, vehicle, getReference(players));
	}

	/**
	 * Hide fake entities to the defined players.
	 * 
	 * @param entities Array containing BukkitEntityReference of all the entities.
	 * @param players The players to send this hide effect to.
	 */
	public void hideFakeEntities(BukkitEntityReference[] entities, Player... players) {
		hideFakeEntities(entities, getReference(players));
	}

	/**
	 * Hides a single entity to the defined players.
	 * 
	 * @param entity The BukkitEntityReference that represents the ID and UUID of the entity.
	 * @param players The players to send this hide effect to.
	 */
	public void hideFakeEntity(BukkitEntityReference entity, Player... players) {
		hideFakeEntities(new BukkitEntityReference[] {entity}, getReference(players));
	}

	@Override
	@SuppressWarnings("deprecation")
	public void showFakeArmorStand(EntityReference entity, Position position, boolean small, boolean invisible, ProtocolLibPlayerReference... players) {
		WrapperPlayServerSpawnEntity packet = new WrapperPlayServerSpawnEntity();
		packet.setEntityID(entity.getId());
		packet.setUniqueId(entity.getUniqueId());

		// Position
		packet.setX(position.getX());
		packet.setY(position.getY());
		packet.setZ(position.getZ());
		packet.setYaw(position.getYaw());
		packet.setPitch(position.getPitch());

		packet.setType(EntityType.ARMOR_STAND.getTypeId());
		packet.setObjectData(0);

		for (ProtocolLibPlayerReference reference : players)
			reference.sendPacket(packet.getHandle());
	}

	@Override
	public void showFakeEntity(int entityType, EntityReference entity, Position position, ProtocolLibPlayerReference... players) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateFakeEntityCustomName(EntityReference entity, String name, ProtocolLibPlayerReference... players) {
		// TODO Auto-generated method stub
	}

	@Override
	public void teleportFakeEntity(EntityReference entity, Position position, ProtocolLibPlayerReference... players) {
		// TODO Auto-generated method stub
	}

	@Override
	public void rideFakeEntity(EntityReference entity, EntityReference vehicleId, ProtocolLibPlayerReference... players) {
		// TODO Auto-generated method stub
	}

	@Override
	public void hideFakeEntities(EntityReference[] entities, ProtocolLibPlayerReference... players) {
		// TODO Auto-generated method stub
	}

}
