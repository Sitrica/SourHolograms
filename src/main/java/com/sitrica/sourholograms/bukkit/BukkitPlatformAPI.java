package com.sitrica.sourholograms.bukkit;

import org.bukkit.Location;

import com.sitrica.sourholograms.PlatformAPI;
import com.sitrica.sourholograms.api.objects.Hologram;
import com.sitrica.sourholograms.bukkit.objects.BukkitPosition;
import com.sitrica.sourholograms.objects.Position;

public class BukkitPlatformAPI implements PlatformAPI {

	private static BukkitPlatformAPI INSTANCE;

	private BukkitPlatformAPI() {}

	static final BukkitPlatformAPI get() {
		if (INSTANCE == null)
			INSTANCE = new BukkitPlatformAPI();
		return INSTANCE;
	}

	/**
	 * Creates a Hologram at the given location with the defined name.
	 * 
	 * @param name The name for reference to this Hologram.
	 * @param location The location to spawn this hologram at.
	 * @return The created Hologram object after spawning.
	 */
	public Hologram createHologram(String name, Location location) {
		return createHologram(name, BukkitPosition.fromLocation(location));
	}

	@Override
	public Hologram createHologram(String name, Position position) {
		// TODO
		return null;
	}

}
