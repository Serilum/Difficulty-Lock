package com.serilum.difficultylock.mixin;

import com.serilum.difficultylock.util.Util;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CreateWorldScreen.class, priority = 1001)
public class CreateWorldScreenMixin {
	@Inject(method = "init()V", at = @At(value = "TAIL"))
	public void init(CallbackInfo ci) {
		Util.actuallySetDifficulty((CreateWorldScreen)(Object)this);
	}
}
