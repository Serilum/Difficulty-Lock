package com.natamus.difficultylock.mixin;

import com.natamus.difficultylock.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;
import java.util.function.Supplier;

@Mixin(value = CycleButton.class, priority = 1001)
public class CycleButtonMixin {
    @Inject(method = "<init>(IIIILnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/Component;ILjava/lang/Object;Ljava/util/function/Supplier;Lnet/minecraft/client/gui/components/CycleButton$ValueListSupplier;Ljava/util/function/Function;Ljava/util/function/Function;Lnet/minecraft/client/gui/components/CycleButton$OnValueChange;Lnet/minecraft/client/OptionInstance$TooltipSupplier;Lnet/minecraft/client/gui/components/CycleButton$DisplayState;Lnet/minecraft/client/gui/components/CycleButton$SpriteSupplier;)V", at = @At(value = "TAIL"))
    public void CycleButton(int i, int j, int k, int l, Component component, Component name, int index, Object object, Supplier<?> defaultValueSupplier, CycleButton.ValueListSupplier<?> valueListSupplier, Function<?, ?> fI, Function<?, ?> fJ, CycleButton.OnValueChange<?> onValueChange, OptionInstance.TooltipSupplier<?> tooltipSupplier, CycleButton.DisplayState displayState, CycleButton.SpriteSupplier<?> spriteSupplier, CallbackInfo ci) {
		if (Minecraft.getInstance().screen instanceof CreateWorldScreen) {
			CycleButton<?> cycleButton = (CycleButton<?>)(Object)this;
			String nameString = name.getString();

			if (nameString.equals(Component.translatable("selectWorld.gameMode").getString())) {
				Util.gameModeButton = cycleButton;
			}
			else if (nameString.equals(Component.translatable("options.difficulty").getString())) {
				Util.difficultyButton = cycleButton;
			}
			else if (nameString.equals(Component.translatable("selectWorld.allowCommands").getString())) {
				Util.allowCheatsButton = cycleButton;
			}
		}
	}
}
