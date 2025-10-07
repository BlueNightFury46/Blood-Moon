package bluenightfury46.dev.bloodMoon.gui;

import io.papermc.paper.event.player.PlayerCustomClickEvent;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static bluenightfury46.dev.bloodMoon.gui.EquipmentGUI.*;

public class GUIEvents implements Listener {

    @EventHandler
    public void click_ev(InventoryClickEvent e){

        if(!e.getInventory().equals(EquipmentGUI.getCONFIG_INVENTORY())){
            return;
        }


        try{
            if(e.getCurrentItem()==null){
                return;
            }
        } catch(NullPointerException ex){
            return;
        }




            e.setCancelled(true);

        Material item = e.getCurrentItem().getType();


      //  if(e.getClick().isRightClick()){
            switch(e.getSlot()){

                case EquipmentGUI.HELMET_POS -> {
                 if(!EquipmentGUI.getHelmetItem().getType().equals(DEFAULT_MATERIAL)){

                    EquipmentGUI.setHelmet(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Helmet", ""));
                    return;
                }}
                case EquipmentGUI.CHESTPLATE_POS -> {
                    if(!EquipmentGUI.getChestplateItem().getType().equals(DEFAULT_MATERIAL)){

                        EquipmentGUI.setChestplate(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Chestplate", ""));
                    return;
                }}
                case EquipmentGUI.LEGGINGS_POS -> {
                    if(!EquipmentGUI.getLeggingsItem().getType().equals(DEFAULT_MATERIAL)){

                        EquipmentGUI.setLeggings(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Leggings", ""));
                    return;
                }}
                case EquipmentGUI.BOOTS_POS -> {
                    if(!EquipmentGUI.getBootsItem().getType().equals(DEFAULT_MATERIAL)){

                        EquipmentGUI.setBoots(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Boots", ""));
                    return;
                }}



                case EquipmentGUI.MAINHAND_POS -> {
                    if(!EquipmentGUI.getMainhandItem().getType().equals(DEFAULT_MATERIAL)){

                        EquipmentGUI.setMainhand(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Mainhand", ""));
                    return;
                }}
                case EquipmentGUI.OFFHAND_POS -> {
                    if(!EquipmentGUI.getOffhandItem().getType().equals(DEFAULT_MATERIAL)){


                        EquipmentGUI.setOffhand(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Offhand", ""));
                    return;
                }}

            }



     //   }




        if(EquipmentGUI.isHelmet(item) && EquipmentGUI.getHelmetItem().getType().equals(EquipmentGUI.DEFAULT_MATERIAL)){
            EquipmentGUI.setHelmet(e.getCurrentItem());
            return;
        }

        if(EquipmentGUI.isChestplate(item) && EquipmentGUI.getChestplateItem().getType().equals(EquipmentGUI.DEFAULT_MATERIAL)){
            EquipmentGUI.setChestplate(e.getCurrentItem());
            return;
        }

        if(EquipmentGUI.isLeggings(item) && EquipmentGUI.getLeggingsItem().getType().equals(EquipmentGUI.DEFAULT_MATERIAL)){
            EquipmentGUI.setLeggings(e.getCurrentItem());
            return;
        }

        if(EquipmentGUI.isBoots(item) && EquipmentGUI.getBootsItem().getType().equals(EquipmentGUI.DEFAULT_MATERIAL)){
            EquipmentGUI.setBoots(e.getCurrentItem());
            return;
        }



        if(EquipmentGUI.getMainhandItem().getType().equals(EquipmentGUI.DEFAULT_MATERIAL)){
            EquipmentGUI.setMainhand(e.getCurrentItem());
            return;
        }

        if(EquipmentGUI.getOffhandItem().getType().equals(EquipmentGUI.DEFAULT_MATERIAL)){
            EquipmentGUI.setOffhand(e.getCurrentItem());
            return;
        }















        }







    @EventHandler
    public void drag_ev(InventoryDragEvent e){
        e.getWhoClicked().sendMessage("Detected2");

        if(e.getInventory().equals(EquipmentGUI.getCONFIG_INVENTORY())){
            e.setCancelled(true);

        }
    }




}
