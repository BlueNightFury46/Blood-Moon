package bluenightfury46.dev.bloodMoon.gui;

import bluenightfury46.dev.bloodMoon.BloodMoon;
import bluenightfury46.dev.bloodMoon.json.JsonItemstack;
import io.papermc.paper.event.player.PlayerCustomClickEvent;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.ChatColor;
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

    ItemStack itemFilter(ItemStack i){
        if(i.getType().equals(DEFAULT_MATERIAL)){
            i.setType(Material.AIR);
        }
        return i;
    }

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

        if(!e.getWhoClicked().isOp()){
            return;
        }

        Material item = e.getCurrentItem().getType();


      //  if(e.getClick().isRightClick()){
            switch(e.getSlot()){

                case EquipmentGUI.HELMET_POS -> {
                 if(!EquipmentGUI.getHelmetItem().getType().equals(DEFAULT_MATERIAL)){

                    EquipmentGUI.setHelmet(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Helmet", "Set the blood moon helmet"));
                    return;
                }}
                case EquipmentGUI.CHESTPLATE_POS -> {
                    if(!EquipmentGUI.getChestplateItem().getType().equals(DEFAULT_MATERIAL)){

                        EquipmentGUI.setChestplate(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Chestplate", "Set the blood moon chestplate"));
                    return;
                }}
                case EquipmentGUI.LEGGINGS_POS -> {
                    if(!EquipmentGUI.getLeggingsItem().getType().equals(DEFAULT_MATERIAL)){

                        EquipmentGUI.setLeggings(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Leggings", "Set the blood moon leggings"));
                    return;
                }}
                case EquipmentGUI.BOOTS_POS -> {
                    if(!EquipmentGUI.getBootsItem().getType().equals(DEFAULT_MATERIAL)){

                        EquipmentGUI.setBoots(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Boots", "Set the blood moon boots"));
                    return;
                }}



                case EquipmentGUI.MAINHAND_POS -> {
                    if(!EquipmentGUI.getMainhandItem().getType().equals(DEFAULT_MATERIAL)){

                        EquipmentGUI.setMainhand(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Mainhand", "Set the blood moon mainhand"));
                    return;
                }}
                case EquipmentGUI.OFFHAND_POS -> {
                    if(!EquipmentGUI.getOffhandItem().getType().equals(DEFAULT_MATERIAL)){


                        EquipmentGUI.setOffhand(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Offhand", "Set the blood moon offhand"));
                    return;
                }}






                case EquipmentGUI.APPLY_POS -> {

                    if(!e.getWhoClicked().isOp()){
                        return;
                    }
                    BloodMoon.data.HELMET = new JsonItemstack(itemFilter(EquipmentGUI.getHelmetItem()));
                    BloodMoon.data.CHESTPLATE = new JsonItemstack(itemFilter(EquipmentGUI.getChestplateItem()));
                    BloodMoon.data.LEGGINGS = new JsonItemstack(itemFilter(EquipmentGUI.getLeggingsItem()));
                    BloodMoon.data.BOOTS = new JsonItemstack(itemFilter(EquipmentGUI.getBootsItem()));

                    BloodMoon.data.MAINHAND = new JsonItemstack(itemFilter(EquipmentGUI.getMainhandItem()));
                    BloodMoon.data.OFFHAND = new JsonItemstack(itemFilter(EquipmentGUI.getOffhandItem()));

                    e.getInventory().close();

                    e.getWhoClicked().sendMessage(ChatColor.GREEN + "Applied equipment layout");


                    return;
                }

                case EquipmentGUI.CANCEL_POS -> {
                    EquipmentGUI.setHelmet(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Helmet", "Set the blood moon helmet"));
                    EquipmentGUI.setChestplate(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Chestplate", "Set the blood moon chestplate"));
                    EquipmentGUI.setLeggings(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Leggings", "Set the blood moon leggings"));
                    EquipmentGUI.setBoots(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Boots", "Set the blood moon boots"));
                    EquipmentGUI.setMainhand(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Mainhand", "Set the blood moon mainhand"));
                    EquipmentGUI.setOffhand(genGUIItem(EquipmentGUI.DEFAULT_MATERIAL, 1, "Offhand", "Set the blood moon offhand"));
                    return;
                }

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



        if(EquipmentGUI.getMainhandItem().getType().equals(EquipmentGUI.DEFAULT_MATERIAL) ){
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

        if(e.getInventory().equals(EquipmentGUI.getCONFIG_INVENTORY())){
            e.setCancelled(true);

        }
    }




}
