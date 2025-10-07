package bluenightfury46.dev.bloodMoon.gui;

import bluenightfury46.dev.bloodMoon.BloodMoon;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EquipmentGUI {

//   Inventory CONFIG_INVENTORY;

    public static final int INVENTORY_COLUMNS = 9;
    public static final int INVENTORY_ROWS = 3;
    public static final String INVENTORY_TITLE = "Blood Moon";


    public static final int HELMET_POS = (INVENTORY_COLUMNS*1)+1;
    public static final int CHESTPLATE_POS = (INVENTORY_COLUMNS*1)+2;
    public static final int LEGGINGS_POS = (INVENTORY_COLUMNS*1)+3;
    public static final int BOOTS_POS = (INVENTORY_COLUMNS*1)+4;

    public static final int MAINHAND_POS = (INVENTORY_COLUMNS*1)+5;
    public static final int OFFHAND_POS = (INVENTORY_COLUMNS*1)+6;


    public static final Material DEFAULT_MATERIAL = Material.WHITE_STAINED_GLASS;




    @NotNull public static Inventory inventory;



    public static boolean isHelmet(Material m){

        //TODO add copper for 1.21.9
        switch(m){
            case LEATHER_HELMET, CHAINMAIL_HELMET, GOLDEN_HELMET, IRON_HELMET, DIAMOND_HELMET, NETHERITE_HELMET, TURTLE_HELMET, PLAYER_HEAD, WITHER_SKELETON_SKULL, SKELETON_SKULL, CREEPER_HEAD, DRAGON_HEAD, ZOMBIE_HEAD -> {
                return true;
            }

        }
        return false;
    }

    public static boolean isChestplate(Material m){

        //TODO add copper for 1.21.9
        switch(m){
            case LEATHER_CHESTPLATE, CHAINMAIL_CHESTPLATE, GOLDEN_CHESTPLATE, IRON_CHESTPLATE, DIAMOND_CHESTPLATE, NETHERITE_CHESTPLATE, ELYTRA -> {
                return true;
            }

        }
        return false;
    }

    public static boolean isLeggings(Material m){

        //TODO add copper for 1.21.9
        switch(m){
            case LEATHER_LEGGINGS, CHAINMAIL_LEGGINGS, GOLDEN_LEGGINGS, IRON_LEGGINGS, DIAMOND_LEGGINGS, NETHERITE_LEGGINGS -> {
                return true;
            }

        }
        return false;
    }

    public static boolean isBoots(Material m){

        //TODO add copper for 1.21.9
        switch(m){
            case LEATHER_BOOTS, CHAINMAIL_BOOTS, GOLDEN_BOOTS, IRON_BOOTS, DIAMOND_BOOTS, NETHERITE_BOOTS -> {
                return true;
            }

        }
        return false;
    }


    public static void setHelmet(ItemStack i){
        inventory.setItem(HELMET_POS,i);

    }

    public static void setChestplate(ItemStack i){
        inventory.setItem(CHESTPLATE_POS,i);

    }

    public static void setLeggings(ItemStack i){
        inventory.setItem(LEGGINGS_POS,i);

    }

    public static void setBoots(ItemStack i){
        inventory.setItem(BOOTS_POS,i);

    }

    public static void setMainhand(ItemStack i){
        inventory.setItem(MAINHAND_POS,i);

    }

    public static void setOffhand(ItemStack i){
        inventory.setItem(OFFHAND_POS,i);

    }


    public static ItemStack getOffhandItem(){
        return inventory.getItem(OFFHAND_POS);
    }

    public static ItemStack getMainhandItem(){
        return inventory.getItem(MAINHAND_POS);
    }

    public static ItemStack getHelmetItem(){
        return inventory.getItem(HELMET_POS);
    }

    public static ItemStack getChestplateItem(){
        return inventory.getItem(CHESTPLATE_POS);
    }

    public static ItemStack getLeggingsItem(){
        return inventory.getItem(LEGGINGS_POS);
    }

    public static ItemStack getBootsItem(){
        return inventory.getItem(BOOTS_POS);
    }




   @NotNull public static void genCONFIG_INVENTORY(){

       inventory = BloodMoon.plugin.getServer().createInventory(null, (INVENTORY_COLUMNS*INVENTORY_ROWS), INVENTORY_TITLE);
       reloadItems();


    }

     public static void reloadItems(){
         inventory.setItem(HELMET_POS,genGUIItem(BloodMoon.data.HELMET.toItemStack().getType(), 1, "Helmet", "Set the blood moon helmet"));
         inventory.setItem(CHESTPLATE_POS,genGUIItem(BloodMoon.data.CHESTPLATE.toItemStack().getType(), 1, "Chestplate", "test"));
         inventory.setItem(LEGGINGS_POS,genGUIItem(BloodMoon.data.CHESTPLATE.toItemStack().getType(), 1, "Leggings", "test"));
         inventory.setItem(BOOTS_POS,genGUIItem(BloodMoon.data.CHESTPLATE.toItemStack().getType(), 1, "Boots", "test"));
         inventory.setItem(MAINHAND_POS,genGUIItem(BloodMoon.data.CHESTPLATE.toItemStack().getType(), 1, "Mainhand", "test"));
         inventory.setItem(OFFHAND_POS,genGUIItem(BloodMoon.data.CHESTPLATE.toItemStack().getType(), 1, "Offhand", "test"));

     }

    @NotNull public static Inventory getCONFIG_INVENTORY(){
        return inventory;
    }





    public static ItemStack genGUIItem(@NotNull Material m, final int count, String name, String...Lore){
        ItemStack itemStack = new ItemStack(m, count);

        if (!itemStack.getType().isAir() || itemStack.getType()==null) {
            itemStack = new ItemStack(DEFAULT_MATERIAL, count);
        }

        try {
            ItemMeta meta = itemStack.getItemMeta();

            meta.setItemName(name);
            meta.setLore(List.of(Lore));

            itemStack.setItemMeta(meta);

        } catch(NullPointerException e){
            itemStack.setType(DEFAULT_MATERIAL);

        }



        return itemStack;
    }


}
