package com.natamus.difficultylock.forge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.difficultylock.events.DifficultyLockEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeDifficultyLockEvent {
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getWorld());
		if (level == null) {
			return;
		}

		DifficultyLockEvent.onWorldLoad((ServerLevel)level);
	}
}
