package io.github.explosivemine.anvil.version;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.Inventory;

public final class Wrapper1_19_1_R1 implements VersionWrapper {
    @Override
    public Inventory openInventory(Player player, String title) {
        Block block = Blocks.ANVIL;
        ServerPlayer serverPlayer = getServerPlayer(player);
        Location loc = player.getLocation();
        serverPlayer.openMenu(block.defaultBlockState().getMenuProvider(serverPlayer.level, new BlockPos(loc.getX(), loc.getY(), loc.getZ())));
        serverPlayer.containerMenu.checkReachable = false;
//        serverPlayer.containerMenu.setTitle(Component.literal(title));
        return serverPlayer.containerMenu.getBukkitView().getTopInventory();
    }

    @Override
    public void setInstaBuild(Player player, boolean value) {
        ServerPlayer serverPlayer = getServerPlayer(player);
        serverPlayer.getAbilities().instabuild = value;
        serverPlayer.connection.send(new ClientboundPlayerAbilitiesPacket(serverPlayer.getAbilities()));
    }

    @Override
    public ServerPlayer getServerPlayer(Player player) {
        return ((CraftPlayer) player).getHandle();
    }

    public int getRepairCost(PrepareAnvilEvent prepareAnvilEvent) {
        return prepareAnvilEvent.getInventory().getRepairCost();
    }
}