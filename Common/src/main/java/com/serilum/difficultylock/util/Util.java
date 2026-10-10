package com.serilum.difficultylock.util;

import com.serilum.difficultylock.config.ConfigHandler;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState.SelectedGameMode;
import net.minecraft.world.Difficulty;

public class Util {
	public static CycleButton<?> gameModeButton = null;
	public static CycleButton<?> difficultyButton = null;
	public static CycleButton<?> allowCheatsButton = null;

	public static void processScreenTick(CreateWorldScreen createWorldScreen) {
		actuallySetDifficulty(createWorldScreen);
	}

	public static Difficulty getDifficultyFromConfig() {
		boolean forceHardcoreMode = ConfigHandler.forceHardcoreMode;
		if (ConfigHandler.forcePeaceful && !forceHardcoreMode) {
			return Difficulty.PEACEFUL;
		}
		else if (ConfigHandler.forceEasy && !forceHardcoreMode) {
			return Difficulty.EASY;
		}
		else if (ConfigHandler.forceNormal && !forceHardcoreMode) {
			return Difficulty.NORMAL;
		}
		else if (ConfigHandler.forceHard ||  forceHardcoreMode) {
			return Difficulty.HARD;
		}
		return null;
	}

	public static boolean hasADifficultyEnabledInConfig() {
		return ConfigHandler.forcePeaceful || ConfigHandler.forceEasy || ConfigHandler.forceNormal || ConfigHandler.forceHard || ConfigHandler.forceHardcoreMode;
	}

	public static SelectedGameMode getAllowedGameMode(SelectedGameMode currentGameMode, SelectedGameMode newGameMode) {
		if (!ConfigHandler.disableCreativeModeSelection || !newGameMode.equals(SelectedGameMode.CREATIVE)) {
			return newGameMode;
		}

		if (currentGameMode.equals(SelectedGameMode.SURVIVAL)) {
			return SelectedGameMode.HARDCORE;
		}
		return SelectedGameMode.SURVIVAL;
	}

	public static void actuallySetDifficulty(CreateWorldScreen createWorldScreen) {
		WorldCreationUiState uiState = createWorldScreen.getUiState();
		if (ConfigHandler.forceHardcoreMode) {
			if (!uiState.getGameMode().equals(SelectedGameMode.HARDCORE)) {
				uiState.setGameMode(SelectedGameMode.HARDCORE);
			}
		}
		else {
			SelectedGameMode selectedGameMode = uiState.getGameMode();
			if (selectedGameMode.equals(SelectedGameMode.HARDCORE)) {
				return;
			}

			if (ConfigHandler.disableCreativeModeSelection && selectedGameMode.equals(SelectedGameMode.CREATIVE)) {
				uiState.setGameMode(SelectedGameMode.SURVIVAL);
				selectedGameMode = SelectedGameMode.SURVIVAL;
			}

			Difficulty newDifficulty = getDifficultyFromConfig();

			if (newDifficulty != null && !uiState.getDifficulty().equals(newDifficulty)) {
				uiState.setDifficulty(newDifficulty);
			}

			if (ConfigHandler.forceCheatsDisabled && selectedGameMode.equals(SelectedGameMode.SURVIVAL)) {
				if (uiState.isAllowCommands()) {
					uiState.setAllowCommands(false);
				}
			}
		}
	}

	public static void updateButtonStates(WorldCreationUiState uiState) {
		if (ConfigHandler.forceHardcoreMode) {
			if (gameModeButton != null) {
				gameModeButton.active = false;
			}
			return;
		}

		if (difficultyButton != null && hasADifficultyEnabledInConfig()) {
			difficultyButton.active = false;
		}

		if (allowCheatsButton != null && ConfigHandler.forceCheatsDisabled && uiState.getGameMode().equals(SelectedGameMode.SURVIVAL)) {
			allowCheatsButton.active = false;
		}
	}
}
