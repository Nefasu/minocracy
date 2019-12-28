package com.github.nefasu.minocracy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.nefasu.minocracy.config.Config;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("minocracy")
public class Minocracy {
	public static Minocracy instance;
	public static final String MODID = "minocracy";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	public Minocracy() {
		instance = this;

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

		Config.LoadConfig(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("minocracy-server.toml").toString());
		Config.LoadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("minocracy-client.toml").toString());
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("Setup method registered.");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		LOGGER.info("Client registries method registered.");
	}
}