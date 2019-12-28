package com.github.nefasu.minocracy;

import com.github.nefasu.minocracy.commands.DimensionalTeleport;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@EventBusSubscriber(modid = Minocracy.MODID, bus = Bus.FORGE)
public class MionocracyRegistries {
	public static void registerCommands(FMLServerStartingEvent event) {
		final CommandDispatcher<CommandSource> DISPATCHER = event.getCommandDispatcher();
		DimensionalTeleport.registerCommand(DISPATCHER);
	}
}