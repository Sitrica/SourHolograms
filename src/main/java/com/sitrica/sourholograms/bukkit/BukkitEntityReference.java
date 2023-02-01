package com.sitrica.sourholograms.bukkit;

import java.util.UUID;

import org.bukkit.Bukkit;

import com.sitrica.sourholograms.objects.EntityReference;

public class BukkitEntityReference extends EntityReference {

	private BukkitEntityReference(int ID, UUID uuid) {
		super(ID, uuid);
	}

	public static BukkitEntityReference get(int ID) {
		UUID uuid = null;
		while (uuid == null) {
			UUID temp = UUID.randomUUID();
			if (Bukkit.getEntity(temp) != null)
				continue;
			if (entities.stream().map(EntityReference::getUniqueId).anyMatch(u -> u.equals(temp)))
				continue;
			uuid = temp;
		}
		return new BukkitEntityReference(ID, uuid);
	}

}
