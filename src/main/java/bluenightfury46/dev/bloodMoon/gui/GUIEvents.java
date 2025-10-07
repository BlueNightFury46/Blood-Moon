package bluenightfury46.dev.bloodMoon.gui;

import io.papermc.paper.event.player.PlayerCustomClickEvent;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class GUIEvents implements Listener {

    @EventHandler
    public void click_ev(InventoryClickEvent e){
        if(e.getClickedInventory().equals(EquipmentGUI.getCONFIG_INVENTORY(e.getWhoClicked().getServer()))){
            e.setCancelled(true);

        }

    }


    @EventHandler
    public void drag_ev(InventoryDragEvent e){
        if(e.getInventory().equals(EquipmentGUI.getCONFIG_INVENTORY(e.getWhoClicked().getServer()))){
            e.setCancelled(true);

        }
    }

}
