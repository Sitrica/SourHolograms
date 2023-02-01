package com.sitrica.sourholograms.nms;

import com.sitrica.sourholograms.objects.EntityReference;
import com.sitrica.sourholograms.objects.PlayerReference;
import com.sitrica.sourholograms.objects.Position;

/**
 * Class that represents NMS methods down to the bone, no server implementation.
 * Must implement a PlayerReference aswell to define how your packet implementation is serialized to NMS packets.
 * Can use just PlayerReference as that'll handle PlayerConnection.
 */
public interface NMS<P extends PlayerReference> {

	/**
	 * Grab the next entity ID for this NMS version.
	 * 
	 * @return the int of the next entity ID the game uses.
	 */
	int getNextEntityId();

	/**
	 * Spawn a fake armor stand at the defined position
	 * with attributes to the defined players.
	 * 
	 * @param entity The EntityReference that represents the ID and UUID of the entity.
	 * @param position The position to spawn the entity at.
	 * @param small Boolean if the armor stand should have the small attribute.
	 * @param invisible Boolean if the armour stand should have the invisible attribute.
	 * @param players The players to send this armor stand to.
	 */
	@SuppressWarnings("unchecked")
	void showFakeArmorStand(EntityReference entity, Position position, boolean small, boolean invisible, P... players);

	/**
	 * Spawn a fake entity at the defined position
	 * to the defined players.
	 * 
	 * @param entityType The type id of the entity to spawn.
	 * @param The EntityReference that represents the ID and UUID of the entity.
	 * @param position The position to spawn the entity at.
	 * @param players The players to send this entity to.
	 */
	@SuppressWarnings("unchecked")
	void showFakeEntity(int entityType, EntityReference entity, Position position, P... players);

	/**
	 * Set the custom name of a fake entity to the defined players.
	 * 
	 * @param The EntityReference that represents the ID and UUID of the entity.
	 * @param name The name to update the entity to.
	 * @param players The players to send the custom name update to.
	 */
	@SuppressWarnings("unchecked")
	void updateFakeEntityCustomName(EntityReference entity, String name, P... players);

	/**
	 * Teleports a fake entity to a position for the defined players.
	 * 
	 * @param The EntityReference that represents the ID and UUID of the entity.
	 * @param position The position to teleport the entity to.
	 * @param players The players to send this teleport effect to.
	 */
	@SuppressWarnings("unchecked")
	void teleportFakeEntity(EntityReference entity, Position position, P... players);

	/**
	 * Makes an entity appear to be riding another entity to the defined players.
	 * 
	 * @param entity The EntityReference that represents the ID and UUID of the entity riding.
	 * @param vehicleId The EntityReference that represents the ID and UUID of the vehicle.
	 * @param players The players to send this teleport effect to.
	 */
	@SuppressWarnings("unchecked")
	void rideFakeEntity(EntityReference entity, EntityReference vehicleId, P... players);

	/**
	 * Hide fake entities to the defined players.
	 * 
	 * @param entities Array containing EntityReference of all the entities.
	 * @param players The players to send this hide effect to.
	 */
	@SuppressWarnings("unchecked")
	void hideFakeEntities(EntityReference[] entities, P... players);

	/**
	 * Hides a single entity to the defined players.
	 * 
	 * @param entity The EntityReference that represents the ID and UUID of the entity.
	 * @param players The players to send this hide effect to.
	 */
	@SuppressWarnings("unchecked")
	default void hideFakeEntity(EntityReference entity, P... players) {
		hideFakeEntities(new EntityReference[] {entity}, players);
	}

	default Object onPacketInAsync(PlayerReference sender, Object packet) {
		return packet;
	}

	default Object onPacketOutAsync(PlayerReference reciever, Object packet) {
		return packet;
	}

}
