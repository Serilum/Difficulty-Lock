package com.natamus.difficultylock;

import com.natamus.collective.check.RegisterMod;
import com.natamus.difficultylock.neoforge.config.IntegrateNeoForgeConfig;
import com.natamus.difficultylock.neoforge.events.NeoForgeDifficultyLockClientEvent;
import com.natamus.difficultylock.neoforge.events.NeoForgeDifficultyLockEvent;
import com.natamus.difficultylock.util.Reference;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Reference.MOD_ID)
public class ModNeoForge {
	
	public ModNeoForge(IEventBus modEventBus) {
		modEventBus.addListener(this::loadComplete);

		setGlobalConstants();
		ModCommon.init();

		IntegrateNeoForgeConfig.registerScreen(ModLoadingContext.get());

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
		NeoForge.EVENT_BUS.register(NeoForgeDifficultyLockEvent.class);

		if (FMLEnvironment.dist.equals(Dist.CLIENT)) {
			NeoForge.EVENT_BUS.register(NeoForgeDifficultyLockClientEvent.class);
		}
	}

	private static void setGlobalConstants() {

	}
}