package com.natamus.difficultylock.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.difficultylock.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean forceHardcoreMode = false;
	@Entry public static boolean disableCreativeModeSelection = false;
	@Entry public static boolean forcePeaceful = false;
	@Entry public static boolean forceEasy = false;
	@Entry public static boolean forceNormal = false;
	@Entry public static boolean forceHard = true;
	@Entry public static boolean forceCheatsDisabled = false;
	@Entry public static boolean shouldLockDifficulty = true;
	@Entry public static boolean shouldChangeDifficultyWhenAlreadyLocked = false;

	public static void initConfig() {
		configMetaData.put("forceHardcoreMode", Arrays.asList(
			"Sets the world game mode to hardcode mode and ignores the other difficulty settings when enabled."
		));
		configMetaData.put("disableCreativeModeSelection", Arrays.asList(
			"Whether Creative Mode should not be possible to select in the world creation screen."
		));
		configMetaData.put("forcePeaceful", Arrays.asList(
			"Priority 1: Sets the difficulty in any world to peaceful when enabled."
		));
		configMetaData.put("forceEasy", Arrays.asList(
			"Priority 2: Sets the difficulty in any world to easy when enabled."
		));
		configMetaData.put("forceNormal", Arrays.asList(
			"Priority 3: Sets the difficulty in any world to normal when enabled."
		));
		configMetaData.put("forceHard", Arrays.asList(
			"Priority 4: Sets the difficulty in any world to hard when enabled."
		));
		configMetaData.put("forceCheatsDisabled", Arrays.asList(
			"Whether the cheats option should be forced to be disabled on the initial world creation screen."
		));
		configMetaData.put("shouldLockDifficulty", Arrays.asList(
			"When enabled, locks the difficulty in any world so it cannot be changed."
		));
		configMetaData.put("shouldChangeDifficultyWhenAlreadyLocked", Arrays.asList(
			"When enabled, also sets the difficulty in worlds where it has already been locked."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}