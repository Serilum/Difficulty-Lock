package com.serilum.difficultylock.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.collective.functions.ConfigFunctions;
import com.serilum.difficultylock.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean forceHardcoreMode = false;
	@Entry public static boolean disableCreativeModeSelection = false;
	@Entry(options = {"Disabled", "Peaceful", "Easy", "Normal", "Hard"}) public static String forcedDifficulty = "Hard";
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
		configMetaData.put("forcedDifficulty", Arrays.asList(
			"The difficulty that is set in any world. Disabled leaves the difficulty as it is."
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

		HashMap<String, String> oldConfigValues = ConfigFunctions.getConfigValues(Reference.MOD_ID);
		String oldForcedDifficulty = getOldForcedDifficulty(oldConfigValues);

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);

		if (oldForcedDifficulty != null) {
			forcedDifficulty = oldForcedDifficulty;
			DuskConfig.write(Reference.MOD_ID);
		}
	}

	private static String getOldForcedDifficulty(HashMap<String, String> configValues) {
		if (configValues.containsKey("forcedDifficulty")) {
			return null;
		}

		boolean hasOldSetting = false;
		for (String difficulty : Arrays.asList("Peaceful", "Easy", "Normal", "Hard")) {
			String oldValue = configValues.get("force" + difficulty);
			if ("true".equals(oldValue)) {
				return difficulty;
			}
			if (oldValue != null) {
				hasOldSetting = true;
			}
		}

		if (!hasOldSetting) {
			return null;
		}
		return "Disabled";
	}
}