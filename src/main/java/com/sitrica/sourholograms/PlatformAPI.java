package com.sitrica.sourholograms;

import java.util.Collection;
import java.util.Collections;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.sitrica.sourholograms.Platform.PlatformNMS;
import com.sitrica.sourholograms.api.objects.Hologram;
import com.sitrica.sourholograms.objects.Position;

/**
 * Acts as the general API for all platforms.
 * Extensions are to implement methods for the platform and remember plugin owners.
 */
public abstract class PlatformAPI<P extends PlatformNMS<?>> {

	private final static Multimap<String, Hologram> holograms = HashMultimap.create();
	protected String pluginMainClass;
	protected final P nms;

	public PlatformAPI(P nms, String pluginMainClass) {
		this.pluginMainClass = pluginMainClass;
		this.nms = nms;
	}

	/**
	 * Used for serialization to the database for ownership reference.
	 * 
	 * @return The name of the class that represents the plugin that registered a PlatformAPI.
	 */
	String getPluginMainClassName() {
		return pluginMainClass;
	}

	/**
	 * Creates a Hologram at the given position with the defined name.
	 * 
	 * @param name The name for reference to this Hologram.
	 * @param position The position to spawn this hologram at.
	 * @return The created Hologram object after spawning.
	 */
	protected Hologram createHologram(String name, Position position) {
		Hologram hologram = new Hologram(name);
		holograms.put(pluginMainClass, hologram);
		return hologram;
	}

	/**
	 * @return all the holograms registered by all plugins.
	 */
	public Collection<Hologram> getAllHolograms() {
		return Collections.unmodifiableCollection(holograms.values());
	}

	/**
	 * @return Returns a collection of all Holograms registered by this Plugin. {@link #getOwningPlugin()}
	 */
	public Collection<Hologram> getPluginHolograms() {
		return holograms.get(pluginMainClass);
	}

}
