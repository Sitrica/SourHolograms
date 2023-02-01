package com.sitrica.sourholograms;

import com.sitrica.sourholograms.api.objects.Hologram;
import com.sitrica.sourholograms.objects.Position;

public interface PlatformAPI {

	/**
	 * Creates a Hologram at the given position with the defined name.
	 * 
	 * @param name The name for reference to this Hologram.
	 * @param position The position to spawn this hologram at.
	 * @return The created Hologram object after spawning.
	 */
	Hologram createHologram(String name, Position position);

}
