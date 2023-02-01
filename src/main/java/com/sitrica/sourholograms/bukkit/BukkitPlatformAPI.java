package com.sitrica.sourholograms.bukkit;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sitrica.sourholograms.Platform.PlatformNMS;
import com.sitrica.sourholograms.PlatformAPI;
import com.sitrica.sourholograms.api.objects.Hologram;
import com.sitrica.sourholograms.bukkit.nms.BukkitNMS;
import com.sitrica.sourholograms.bukkit.nms.ProtocolLibNMS_1_19_R2;
import com.sitrica.sourholograms.bukkit.objects.BukkitPosition;
import com.sitrica.sourholograms.managers.HologramManager;

public class BukkitPlatformAPI extends PlatformAPI<PlatformNMS<? extends BukkitPlayerReference>> {

	private Plugin plugin;

	/**
	 * Used internally by SourHolograms. It registers it's own PlatformAPI.
	 * 
	 * @param nms The nms that will be cloned to every other instance.
	 */
	BukkitPlatformAPI(PlatformNMS<? extends BukkitPlayerReference> nms) {
		super(nms, SourHologramsBukkit.class.getName());
	}

	final BukkitPlatformAPI newInstance(Plugin plugin, HologramManager hologramManager) {
		BukkitPlatformAPI API = new BukkitPlatformAPI(nms);
		API.pluginMainClass = plugin.getClass().getName();
		API.plugin = plugin;
		hologramManager.load(API);
		return API;
	}

	/**
	 * @return The Plugin that is registered to this BukkitPlatformAPI.
	 */
	public Plugin getOwningPlugin() {
		return plugin;
	}

	@SuppressWarnings("unchecked")
	protected <P extends BukkitPlayerReference> P[] getPlayerReferences(Player... players) {
		if (nms instanceof BukkitNMS)
			return (P[]) ((BukkitNMS)nms).getReference(players);
		if (nms instanceof ProtocolLibNMS_1_19_R2)
			return (P[]) ((ProtocolLibNMS_1_19_R2)nms).getReference(players);
		return null;
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

}
