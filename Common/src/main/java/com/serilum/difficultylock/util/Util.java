package com.serilum.difficultylock.util;

import com.serilum.difficultylock.config.ConfigHandler;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState.SelectedGameMode;
import net.minecraft.world.Difficulty;

import java.util.Locale;

public class Util {
	public static CycleButton<?> gameModeButton = null;
	public static CycleButton<?> difficultyButton = null;
	public static CycleButton<?> allowCheatsButton = null;

	public static void processScreenTick(CreateWorldScreen createWorldScreen) {
		actuallySetDifficulty(createWorldScreen);
	}

	public static Difficulty getDifficultyFromConfig() {
		if (ConfigHandler.forceHardcoreMode) {
			return Difficulty.HARD;
		}
		return Difficulty.byName(ConfigHandler.forcedDifficulty.toLowerCase(Locale.ROOT));
	}

	public static boolean hasADifficultyEnabledInConfig() {
		return getDifficultyFromConfig() != null;
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
				if (uiState.isAllowCheats()) {
					uiState.setAllowCheats(false);
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
