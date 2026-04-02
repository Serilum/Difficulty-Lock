package com.natamus.difficultylock.mixin;

import com.natamus.difficultylock.util.Util;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.function.Consumer;

@Mixin(value = WorldCreationUiState.class, priority = 1001)
public class WorldCreationUiStateMixin {
	/*
	 * Fixes an issue where the buttons flash on and off between element updates.
	 * World name updates are allowed.
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Inject(method = "onChanged()V", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/Iterator;hasNext()Z"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
	public void onChanged(CallbackInfo ci, boolean bl, boolean bl2, Iterator var3) {
		boolean allowOnce = true;

		while (var3.hasNext()) {
            Consumer<WorldCreationUiState> consumer = (Consumer)var3.next();
			if (consumer.toString().contains("GameTab")) {
				if (Util.buttonUpdatesLeft < 0) {
					if (!allowOnce) {
						continue;
					}

					allowOnce = false;
				}
				Util.buttonUpdatesLeft-=1;
			}

            consumer.accept((WorldCreationUiState)(Object)this);
        }

		ci.cancel();
	}
}
