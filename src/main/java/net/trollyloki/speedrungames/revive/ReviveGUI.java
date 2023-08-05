package net.trollyloki.speedrungames.revive;

import net.kyori.adventure.text.Component;
import net.trollyloki.speedrungames.SpeedrunGamesPlugin;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReviveGUI implements Listener {

    private final Set<InventoryView> inventories = new HashSet<>();

    public void open(@NotNull Player player) {
        SpeedrunGamesPlugin plugin = SpeedrunGamesPlugin.getInstance();
        Inventory inventory = player.getServer().createInventory(null, 27,
                plugin.getConfigMessage("revive-gui.title"));

        Integer revives = SpeedrunGamesPlugin.getReviveListener().getRevives(player);
        Component lore = SpeedrunGamesPlugin.MINI_MESSAGE.deserialize(
                plugin.getConfig().getString("revive-gui.remaining").formatted(revives));

        ItemStack yes = new ItemStack(Material.valueOf(
                plugin.getConfig().getString("revive-gui.yes-item.material")));
        yes.editMeta(meta -> {
            meta.displayName(plugin.getConfigMessage("revive-gui.yes-item.name"));
            meta.lore(List.of(lore));
        });

        ItemStack no = new ItemStack(Material.valueOf(
                plugin.getConfig().getString("revive-gui.no-item.material")));
        no.editMeta(meta -> meta.displayName(plugin.getConfigMessage("revive-gui.no-item.name")));

        inventory.setItem(11, yes);
        inventory.setItem(15, no);
        inventories.add(player.openInventory(inventory));
    }

    public void close(@NotNull HumanEntity player) {
        inventories.remove(player.getOpenInventory());
        player.closeInventory();
    }

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent event) {
        if (!inventories.contains(event.getView()))
            return;
        event.setCancelled(true);

        ReviveListener reviveListener = SpeedrunGamesPlugin.getReviveListener();
        switch (event.getSlot()) {
            case 11 -> {
                close(event.getWhoClicked());
                reviveListener.revive((Player) event.getWhoClicked());
            }
            case 15 -> {
                close(event.getWhoClicked());
                reviveListener.dropDrops((Player) event.getWhoClicked());
            }
        }

    }

    @EventHandler
    public void onInventoryDrag(@NotNull InventoryDragEvent event) {
        if (!inventories.contains(event.getView()))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(@NotNull InventoryCloseEvent event) {
        if (!inventories.contains(event.getView()))
            return;

        close(event.getPlayer());
        SpeedrunGamesPlugin.getReviveListener().dropDrops((Player) event.getPlayer());
    }

}
