package com.sitrica.sourholograms;

import com.sitrica.sourholograms.managers.HologramManager;
import com.sitrica.sourholograms.objects.EntityReference;
import com.sitrica.sourholograms.objects.PlayerReference;
import com.sitrica.sourholograms.objects.Position;
import com.sitrica.sourholograms.util.Version;

/**
 * Represents an instance of the main class
 * that can be ran on multiple platforms.
 */
public interface Platform {

	/**
	 * Represents a class that contains wrappers for the platform to access NMS methods.
	 * Must also implement a PlayerReference for the platform.
	 */
	public interface PlatformNMS<P extends PlayerReference> {

		/**
		 * Grab the next entity reference for this NMS version.
		 * This is required to be on the platform so that the method can check against existing UUIDs.
		 * 
		 * @return the EntityReference with a random UUID and the entity ID for this NMS version.
		 */
		<T extends EntityReference> T getNextEntityReference();

		/**
		 * Spawn a fake armor stand at the defined position
		 * with attributes to the defined players. This will use the next entityId.
		 * @param <P>
		 * 
		 * @param position The position to spawn the entity at.
		 * @param small Boolean if the armor stand should have the small attribute.
		 * @param invisible Boolean if the armour stand should have the invisible attribute.
		 * @param players The players to send this armor stand to.
		 * @return The EntityReference that was used to create the armor stand.
		 */
		@SuppressWarnings("unchecked")
		EntityReference showFakeArmorStand(Position position, boolean small, boolean invisible, P... players);

		/**
		 * Spawn a fake entity at the defined position
		 * to the defined players.
		 * @param <P>
		 * 
		 * @param entityType The type id of the entity to spawn.
		 * @param position The position to spawn the entity at.
		 * @param players The players to send this entity to.
		 * @return The EntityReference that was used to create the living entity.
		 */
		@SuppressWarnings("unchecked")
		EntityReference showFakeEntity(int entityType, Position position, P... players);

	}

	public enum Type {
		BUKKIT;
		//SPONGE
		//NUKKIT/CLOUDBURST
	}

	/**
	 * The version of Minecraft.
	 * 
	 * @return version of Minecraft.
	 */
	Version getMinecraftVersion();

	/**
	 * The version of this platform.
	 * 
	 * @return version of this platform.
	 */
	Version getPlatformVersion();

//	/**
//	 * Returns the NMS with casting potential.
//	 * @param <P>
//	 * 
//	 * @param <T extends PlatformNMS> The class that implements PlatformNMS
//	 * @return The NMS class.
//	 */
//	@Nullable
//	<P extends PlayerReference, T extends PlatformNMS<P>> T getNMS();

	/**
	 * The enum type that this platform instance represents.
	 * 
	 * @return The Type enum that represents this instane of a platform.
	 */
	Type getType();

	/**
	 * Returns the API related to the platform.
	 * 
	 * @param <API extends PlatformAPI>
	 * @return PlatformAPI
	 */
	<API extends PlatformAPI<?>> API getPlatformAPI();

	/**
	 * Used internally.
	 */
	// Allows for package access only security.
	default void setPlatform(Platform platform) throws IllegalAccessException {
		SourHologramsAPI.setPlatform(this);
	}

	/**
	 * Prints a message to the console on this platform.
	 * 
	 * @param message The String message to print to the console.
	 */
	void printMessage(String message);

	HologramManager getHologramManager();

}
