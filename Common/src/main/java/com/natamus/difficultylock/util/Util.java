package com.natamus.difficultylock.util;

import com.natamus.difficultylock.config.ConfigHandler;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState.SelectedGameMode;
import net.minecraft.world.Difficulty;

import java.util.Timer;
import java.util.TimerTask;

public class Util {
	public static int buttonUpdatesLeft = 3;
	public static CycleButton<?> gameModeButton = null;
	public static CycleButton<?> difficultyButton = null;
	public static CycleButton<?> allowCheatsButton = null;
	private static SelectedGameMode lastSelectedGameMode = null;

	public static void processScreenTick(CreateWorldScreen createWorldScreen) {
		WorldCreationUiState uiState = createWorldScreen.getUiState();
		if (!uiState.getGameMode().equals(lastSelectedGameMode)) {
			buttonUpdatesLeft = 3;
			lastSelectedGameMode = uiState.getGameMode();
		}

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

	public static void setCreateWorldScreenDifficulty(Screen screen) {
		delaySettingDifficulty(screen);
	}

	private static void delaySettingDifficulty(Screen screen) {
		new Timer().schedule(
			new TimerTask() {
				@Override
				public void run() {
					if (screen instanceof CreateWorldScreen) {
						actuallySetDifficulty((CreateWorldScreen)screen);
					}
				}
			}, 1
		);
	}

	private static void actuallySetDifficulty(CreateWorldScreen createWorldScreen) {
		WorldCreationUiState uiState = createWorldScreen.getUiState();
		if (ConfigHandler.forceHardcoreMode) {
			if (!uiState.getGameMode().equals(SelectedGameMode.HARDCORE)) {
				buttonUpdatesLeft = 3;
				uiState.setGameMode(SelectedGameMode.HARDCORE);
			}

			if (gameModeButton.active) {
				gameModeButton.active = false;
			}
		}
		else {
			SelectedGameMode selectedGameMode = uiState.getGameMode();
			if (selectedGameMode.equals(SelectedGameMode.HARDCORE)) {
				return;
			}

			if (ConfigHandler.disableCreativeModeSelection && selectedGameMode.equals(SelectedGameMode.CREATIVE)) {
				buttonUpdatesLeft = 3;
				uiState.setGameMode(SelectedGameMode.SURVIVAL);
				selectedGameMode = SelectedGameMode.SURVIVAL;
			}

			Difficulty newDifficulty = getDifficultyFromConfig();

			if (newDifficulty != null && !uiState.getDifficulty().equals(newDifficulty)) {
				buttonUpdatesLeft = 3;
				uiState.setDifficulty(newDifficulty);
			}

			if (difficultyButton.active && hasADifficultyEnabledInConfig()) {
				difficultyButton.active = false;
			}

			if (ConfigHandler.forceCheatsDisabled && selectedGameMode.equals(SelectedGameMode.SURVIVAL)) {
				if (uiState.isAllowCommands()) {
					buttonUpdatesLeft = 3;
					uiState.setAllowCommands(false);
				}

				if (allowCheatsButton.active) {
					allowCheatsButton.active = false;
				}
			}
		}
	}
}
