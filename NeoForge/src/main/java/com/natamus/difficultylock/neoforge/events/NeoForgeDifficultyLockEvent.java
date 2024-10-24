package com.natamus.difficultylock.neoforge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.difficultylock.events.DifficultyLockEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.LevelEvent;

@EventBusSubscriber
public class NeoForgeDifficultyLockEvent {
	@SubscribeEvent
	public static void onWorldLoad(LevelEvent.Load e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
		if (level == null) {
			return;
		}

		DifficultyLockEvent.onWorldLoad((ServerLevel)level);
	}
}
