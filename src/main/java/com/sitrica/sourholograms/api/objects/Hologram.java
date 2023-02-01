package com.sitrica.sourholograms.api.objects;

public class Hologram {

	private final String name;

	public Hologram(String name) {
		this.name = name;
	}

	/**
	 * The name this Hologram was created with.
	 * 
	 * @return the name of the hologram.
	 */
	public String getName() {
		return name;
	}

}
