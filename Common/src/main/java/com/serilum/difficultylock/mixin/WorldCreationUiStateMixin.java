package com.serilum.difficultylock.mixin;

import com.serilum.difficultylock.util.Util;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState.SelectedGameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WorldCreationUiState.class, priority = 1001)
public class WorldCreationUiStateMixin {
	@ModifyVariable(method = "setGameMode(Lnet/minecraft/client/gui/screens/worldselection/WorldCreationUiState$SelectedGameMode;)V", at = @At(value = "HEAD"), argsOnly = true)
	public SelectedGameMode setGameMode_gameMode(SelectedGameMode gameMode) {
		return Util.getAllowedGameMode(((WorldCreationUiState)(Object)this).getGameMode(), gameMode);
	}

	// Runs after vanilla's button listeners, so a locked button never flashes active.
	@Inject(method = "onChanged()V", at = @At(value = "TAIL"))
	public void onChanged(CallbackInfo ci) {
		Util.updateButtonStates((WorldCreationUiState)(Object)this);
	}
}
