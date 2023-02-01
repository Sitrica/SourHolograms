package com.sitrica.sourholograms.objects;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;

public abstract class EntityReference {

	protected static final Set<EntityReference> entities = Collections.newSetFromMap(new WeakHashMap<EntityReference, Boolean>()); 
	private final UUID uuid;
	private final int ID;

	protected EntityReference(int ID, UUID uuid) {
		this.uuid = uuid;
		this.ID = ID;
	}

	public UUID getUniqueId() {
		return uuid;
	}

	public int getId() {
		return ID;
	}

}
