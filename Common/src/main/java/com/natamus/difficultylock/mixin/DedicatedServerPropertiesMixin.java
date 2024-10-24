package com.natamus.difficultylock.mixin;

import com.natamus.difficultylock.config.ConfigHandler;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Properties;

@Mixin(value = DedicatedServerProperties.class, priority = 1001)
public class DedicatedServerPropertiesMixin {
	@Shadow public @Mutable @Final boolean hardcore;

	@Inject(method = "<init>(Ljava/util/Properties;)V", at = @At(value = "TAIL"))
	public void DedicatedServerProperties(Properties properties, CallbackInfo ci) {
		if (ConfigHandler.forceHardcoreMode) {
			this.hardcore = true;
		}
	}
}