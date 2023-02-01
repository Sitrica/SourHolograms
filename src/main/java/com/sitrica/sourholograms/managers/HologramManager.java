package com.sitrica.sourholograms.managers;

import com.sitrica.sourholograms.Platform;
import com.sitrica.sourholograms.PlatformAPI;

public class HologramManager {

	public HologramManager(Platform platform) {
		
	}

	/**
	 * Loads all holograms registered under any plugin matching the class name provided.
	 * 
	 * @param pluginMainClass The main class of the plugin to search the database for and apply to a PlatformAPI.
	 */
	public void load(PlatformAPI<?> API) {
		// TODO
		//API.getPluginHolograms().addAll(null);
	}

}
