package com.sitrica.sourholograms;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

import org.bukkit.plugin.Plugin;

import com.sitrica.sourholograms.Platform.Type;
import com.sitrica.sourholograms.bukkit.BukkitPlatformAPI;
import com.sitrica.sourholograms.bukkit.SourHologramsBukkit;

/**
 * The main API access for the SourHolograms plugin.
 */
public class SourHologramsAPI {

	private static final Set<PlatformAPI<?>> REGISTRATION = Collections.newSetFromMap(new WeakHashMap<PlatformAPI<?>, Boolean>()); 
	private static Platform platform;

	/**
	 * Return the instance of the BukkitPlatformAPI for a plugin.
	 * 
	 * @param plugin The plugin that is using this API.
	 * @return BukkitPlatformAPI
	 */
	public static final BukkitPlatformAPI get(Plugin plugin) {
		if (platform.getType() != Type.BUKKIT)
			throw new IllegalStateException("The current platform is not Bukkit/Spigot!");
		if (plugin instanceof Platform)
			throw new IllegalStateException("SourHolograms cannot self register itself as an API provider!");
		return REGISTRATION.stream()
				.filter(BukkitPlatformAPI.class::isInstance)
				.map(BukkitPlatformAPI.class::cast)
				.filter(API -> API.getOwningPlugin().equals(plugin))
				.findFirst()
				.orElseGet(() -> {
					BukkitPlatformAPI API = ((SourHologramsBukkit)platform).getPlatformAPI(plugin);
					REGISTRATION.add(API);
					return API;
				});
	}

	// TODO
//	public static final SpongePlatformAPI get(Plugin plugin) {
//		if (platform.getType() != Type.SPONGE)
//			throw new IllegalStateException("The current platform is not Sponge!");
//		if (plugin instanceof Platform)
//			throw new IllegalStateException("SourHolograms cannot self register itself as an API provider!");
//		return REGISTRATION.stream()
//				.filter(SpongePlatformAPI.class::isInstance)
//				.map(SpongePlatformAPI.class::cast)
//				.findFirst()
//				.orElseGet(() -> {
//					SpongePlatformAPI API = ((SourHologramsSponge)platform).getPlatformAPI(plugin);
//					REGISTRATION.add(API);
//					return API;
//				});
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
