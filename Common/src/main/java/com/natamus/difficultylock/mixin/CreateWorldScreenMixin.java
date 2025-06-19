package com.natamus.difficultylock.mixin;

import com.natamus.difficultylock.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationContext;
import net.minecraft.world.level.LevelSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;

@Mixin(value = CreateWorldScreen.class, priority = 1001)
public class CreateWorldScreenMixin {
	@Inject(method = "createFromExisting(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/screens/Screen;Lnet/minecraft/world/level/LevelSettings;Lnet/minecraft/client/gui/screens/worldselection/WorldCreationContext;Ljava/nio/file/Path;)Lnet/minecraft/client/gui/screens/worldselection/CreateWorldScreen;", at = @At(value = "RETURN"))
	private static void createFromExisting(Minecraft mc, Screen screen, LevelSettings levelSettings, WorldCreationContext worldCreationContext, Path path, CallbackInfoReturnable<CreateWorldScreen> cir) {
		Util.setCreateWorldScreenDifficulty(mc.screen);
	}

	@Inject(method = "openFresh(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/screens/Screen;)V", at = @At(value = "TAIL"))
	private static void openFresh(Minecraft mc, Screen screen, CallbackInfo ci) {
		Util.setCreateWorldScreenDifficulty(mc.screen);
	}
}
