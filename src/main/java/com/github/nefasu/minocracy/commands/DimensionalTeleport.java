package com.github.nefasu.minocracy.commands;

import com.github.nefasu.minocracy.config.CommandConfiguration;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.command.arguments.DimensionArgument;
import net.minecraft.command.arguments.Vec3Argument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;

public class DimensionalTeleport {
	public static void registerCommand(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> command = Commands.literal("qol").then(registerTeleportCommand());
		dispatcher.register(command);
	}

	private static LiteralArgumentBuilder<CommandSource> registerTeleportCommand() {
		return Commands.literal("dimension-teleport")
				.requires(source -> source.hasPermissionLevel(CommandConfiguration.dimension_teleport_permission.get()))
				.then(Commands.argument("dimension", DimensionArgument.getDimension())
						.executes(context -> { return teleportPlayer(context.getSource().asPlayer(), 
								DimensionArgument.func_212592_a(context, "dimension"), 
								context.getSource().getPos()); })
						.then(Commands.argument("location", Vec3Argument.vec3())
								.executes(context -> { return teleportPlayer(context.getSource().asPlayer(), 
								DimensionArgument.func_212592_a(context, "dimension"), 
								new Vec3d(BlockPosArgument.getBlockPos(context, "location"))); })));
	}
	
	private static int teleportPlayer(ServerPlayerEntity player, DimensionType dimensionType, Vec3d position) {
		final ServerWorld world = player.getServer().getWorld(dimensionType);
		
		world.getChunkProvider().func_217228_a(TicketType.POST_TELEPORT, new ChunkPos(new BlockPos(position)), 1, 
				player.getEntityId());
		
		player.detach();
		if (player.isSleeping()) {
			player.wakeUpPlayer(true, true, false);
		}
		
		if (world == player.world) {
			player.connection.setPlayerLocation(position.getX(), position.getY(), position.getZ(), player.rotationYaw, 
					player.rotationPitch);
		}
		else {
			player.teleport(world, position.getX(), position.getY(), position.getZ(), player.rotationYaw, 
					player.rotationPitch);
		}
		
		player.setRotationYawHead(player.rotationYaw);
		
		return 0;
	}
}