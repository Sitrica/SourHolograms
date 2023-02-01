package com.sitrica.sourholograms.bukkit.objects;

import org.bukkit.Location;

import com.sitrica.sourholograms.objects.Position;

/**
 * Bukkit wrapper for the Positon object.
 */
public class BukkitPosition extends Position {

	public BukkitPosition(Location location) {
		super(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	}

	public BukkitPosition(double x, double y, double z, float yaw, float pitch) {
		super(x, y, z, yaw, pitch);
	}

	public BukkitPosition(double x, double y, double z) {
		super(x, y, z);
	}

	public static BukkitPosition fromLocation(Location location) {
		return new BukkitPosition(location);
	}

}
