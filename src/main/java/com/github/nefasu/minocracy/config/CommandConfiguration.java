package com.github.nefasu.minocracy.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;

public class CommandConfiguration {
	public static ForgeConfigSpec.BooleanValue enable_dimension_teleport;
	public static ForgeConfigSpec.IntValue dimension_teleport_permission;
	
	public static void init(Builder server, Builder client) {
		server.comment("Predefined Command Selection");
		enable_dimension_teleport = server
				.comment("Allow player to teleport to different dimension.")
				.define("commandSelection.enable_dimension_teleport", true);
		dimension_teleport_permission = server
				.comment("Allow player to teleport to different dimension.")
				.defineInRange("commandSelection.dimension_teleport_permission", 2, 0, 4);
	}
}