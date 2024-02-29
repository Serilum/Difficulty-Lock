package com.natamus.difficultylock.events;

import com.natamus.difficultylock.config.ConfigHandler;
import com.natamus.difficultylock.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.WorldData;

public class DifficultyLockEvent {
	public static void onClientTick(Minecraft mc) {
		if (mc.screen instanceof CreateWorldScreen) {
			Util.processScreenTick((CreateWorldScreen)mc.screen);
		}
	}

	public static void onWorldLoad(ServerLevel serverLevel) {
		WorldData worldData = serverLevel.getServer().getWorldData();

		LevelData levelData = serverLevel.getLevelData();
		boolean isLocked = levelData.isDifficultyLocked();
		if (isLocked && !ConfigHandler.shouldChangeDifficultyWhenAlreadyLocked) {
			return;
		}

		Difficulty currentDifficulty = levelData.getDifficulty();
		Difficulty newDifficulty = Util.getDifficultyFromConfig();

		if (newDifficulty != null && !currentDifficulty.equals(newDifficulty)) {
			worldData.setDifficulty(newDifficulty);
		}

		if (ConfigHandler.shouldLockDifficulty) {
			if (!isLocked) {
				worldData.setDifficultyLocked(true);
			}
		}
	}
}
