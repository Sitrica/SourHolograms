package com.sitrica.sourholograms.nms.versions;

import java.util.concurrent.atomic.AtomicInteger;

import com.sitrica.sourholograms.bukkit.nms.Reflection;
import com.sitrica.sourholograms.bukkit.nms.Reflection.FieldAccessor;
import com.sitrica.sourholograms.nms.NMS;
import com.sitrica.sourholograms.objects.EntityReference;
import com.sitrica.sourholograms.objects.PlayerReference;
import com.sitrica.sourholograms.objects.Position;

import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.phys.Vec3D;

public class NMS_1_19_R2 implements NMS<PlayerReference> {

	private static final FieldAccessor<AtomicInteger> ENTITY_ID_COUNTER = Reflection.getField(Entity.class, "c", AtomicInteger.class);

	@Override
	public final int getNextEntityId() {
		return ENTITY_ID_COUNTER.getStatic().incrementAndGet();
	}

	@Override
	public void showFakeArmorStand(EntityReference entity, Position position, boolean small, boolean invisible, PlayerReference... players) {
		PacketPlayOutSpawnEntity packet = new PacketPlayOutSpawnEntity(
				entity.getId(),
				entity.getUniqueId(),
				position.getX(),
				position.getY(),
				position.getZ(),
				position.getYaw(),
				position.getPitch(),
				EntityTypes.d,
				0,
				Vec3D.b,
				position.getYaw()
		);
		for (PlayerReference reference : players)
			reference.sendPacket(packet);
	}

	@Override
	public void showFakeEntity(int entityType, EntityReference entity, Position position, PlayerReference... players) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateFakeEntityCustomName(EntityReference entity, String name, PlayerReference... players) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleportFakeEntity(EntityReference entity, Position position, PlayerReference... players) {
		// TODO Auto-generated method stub
	}

	@Override
	public void rideFakeEntity(EntityReference entity, EntityReference vehicleId, PlayerReference... players) {
		// TODO Auto-generated method stub
	}

	@Override
	public void hideFakeEntities(EntityReference[] entities, PlayerReference... players) {
		// TODO Auto-generated method stub
	}

}
