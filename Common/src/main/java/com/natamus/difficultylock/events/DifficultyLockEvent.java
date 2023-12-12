package com.natamus.difficultylock.events;

import com.natamus.difficultylock.config.ConfigHandler;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.WorldData;

public class DifficultyLockEvent {
	public static void onWorldLoad(ServerLevel world) {
		WorldData serverconfiguration = world.getServer().getWorldData();
		
		LevelData worldinfo = world.getLevelData();
		boolean islocked = worldinfo.isDifficultyLocked();
		if (islocked && !ConfigHandler.shouldChangeDifficultyWhenAlreadyLocked) {
			return;
		}
		
		Difficulty currentdifficulty = worldinfo.getDifficulty();
		if (ConfigHandler.forcePeaceful) {
			if (!currentdifficulty.equals(Difficulty.PEACEFUL)) {
				serverconfiguration.setDifficulty(Difficulty.PEACEFUL);
			}
		}
		else if (ConfigHandler.forceEasy) {
			if (!currentdifficulty.equals(Difficulty.EASY)) {
				serverconfiguration.setDifficulty(Difficulty.EASY);
			}			
		}
		else if (ConfigHandler.forceNormal) {
			if (!currentdifficulty.equals(Difficulty.NORMAL)) {
				serverconfiguration.setDifficulty(Difficulty.NORMAL);
			}			
		}
		else if (ConfigHandler.forceHard) {
			if (!currentdifficulty.equals(Difficulty.HARD)) {
				serverconfiguration.setDifficulty(Difficulty.HARD);
			}			
		}
		
		if (ConfigHandler.shouldLockDifficulty) {
			if (!islocked) {
				serverconfiguration.setDifficultyLocked(true);
			}
		}
	}
}
