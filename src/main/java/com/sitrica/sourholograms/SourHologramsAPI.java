package com.sitrica.sourholograms;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.plugin.Plugin;

import com.sitrica.sourholograms.Platform.Type;
import com.sitrica.sourholograms.bukkit.BukkitPlatformAPI;

/**
 * The main API access for the SourHolograms plugin.
 */
public class SourHologramsAPI {

	private static final Set<Plugin> BUKKIT_PLUGINS = new HashSet<>();
	private static BukkitPlatformAPI BUKKIT_API;
	private static Platform platform;

	/**
	 * Return the instance of the BukkitPlatformAPI
	 * 
	 * @return BukkitPlatformAPI
	 */
	public static final BukkitPlatformAPI get(Plugin plugin) {
		if (platform.getType() != Type.BUKKIT)
			throw new IllegalStateException("The current platform is not Bukkit/Spigot!");
		if (plugin instanceof Platform)
			throw new IllegalStateException("SourHolograms cannot self register itself as an API provider!");
		if (!BUKKIT_PLUGINS.contains(plugin))
			BUKKIT_PLUGINS.add(plugin);
		if (BUKKIT_API == null)
			BUKKIT_API = platform.getPlatformAPI();
		return BUKKIT_API;
	}

	// TODO
//	public static final SpongePlatformAPI get(Plugin plugin) {
//		if (platform.getType() != Type.SPONGE)
//			throw new IllegalStateException("The current platform is not Sponge!");
//		if (plugin instanceof Platform)
//			throw new IllegalStateException("SourHolograms cannot self register itself as an API provider!");
//		if (!SPONGE_PLUGINS.contains(plugin))
//			SPONGE_PLUGINS.add(plugin);
//		if (SPONGE_API == null)
//			SPONGE_API = platform.getPlatformAPI();
//		return SPONGE_API;
//	}

	/**
	 * Internal use for setting the platform.
	 * 
	 * @param platform The platform that is being initalized.
	 * @throws IllegalAccessException if the platform has already been initalized.
	 */
	static final void setPlatform(Platform platform) throws IllegalAccessException {
		if (SourHologramsAPI.platform == null)
			throw new IllegalAccessException("The platforrm has already been initalized!");
		SourHologramsAPI.platform = platform;
	}

}
