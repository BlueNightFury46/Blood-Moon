package bluenightfury46.dev.bloodMoon.commands;

import bluenightfury46.dev.bloodMoon.ArmourData;
import bluenightfury46.dev.bloodMoon.BloodMoon;
import bluenightfury46.dev.bloodMoon.gui.EquipmentGUI;
import bluenightfury46.dev.bloodMoon.json.JsonItemstack;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.eclipse.sisu.launch.Main;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EquipmentPoolCommand implements CommandExecutor {

    final String COMMAND_HELP = ChatColor.BLUE + "Command Usage:\n/equipmentpool <ACTION> "+ChatColor.GOLD+"<ITEM_SLOT>";

    String SUCCESS_RESPONSE(String ARMOUR_PIECE, String ITEM_NAME){
        return (ChatColor.GREEN+"Successfully added " + ChatColor.LIGHT_PURPLE + ITEM_NAME + ChatColor.GREEN + " to the " + ChatColor.GOLD + ARMOUR_PIECE + ChatColor.AQUA + " item pool");
    }


    final static String ACTION_ADD_ARG = "add";
    final static String ACTION_LIST_ARG = "list";
    final static String HELMET_ARG = "HELMET";
    final static String CHESTPLATE_ARG = "CHESTPLATE";
    final static String LEGGINGS_ARG = "LEGGINGS";
    final static String BOOTS_ARG = "BOOTS";
    final static String MAINHAND_ARG = "MAINHAND";
    final static String OFFHAND_ARG = "OFFHAND";

    final int ITEM_CATEGORY = 1;
    final int ACTION = 0;



    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "ONLY PLAYERS CAN USE THIS COMMAND...");
            return true;
        }

        if(!commandSender.hasPermission("bloodmoon.equipmentpool")){
            commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command...");
            return true;
        }
        Player player = ((Player) commandSender).getPlayer();


        if(args.length>1) {




            if (args[ITEM_CATEGORY].toUpperCase().equals(HELMET_ARG)) {

                JsonItemstack Helmet = new JsonItemstack(player.getInventory().getItemInMainHand());

                if(args[ACTION].toLowerCase().equals(ACTION_ADD_ARG)) {

                    boolean added = false;
                    ArrayList<ArmourData> AddedList = new ArrayList<>();

                    for(ArmourData selected_data : BloodMoon.equipment_pool.ITEM_POOL){
                       if(selected_data.HELMET.equals(ArmourData.AIR) && !added){
                           selected_data.HELMET = Helmet;
                           added = true;
                       }
                       AddedList.add(selected_data);
                    }

                    if(!added){
                        AddedList.add(new ArmourData(Helmet, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR));
                    }

                    BloodMoon.equipment_pool.ITEM_POOL = AddedList;

                    commandSender.sendMessage(SUCCESS_RESPONSE(HELMET_ARG, player.getInventory().getItemInMainHand().getType().name()));
                    return true;
                } else if(args[ACTION].toLowerCase().equals(ACTION_LIST_ARG)){
                    String res = ChatColor.DARK_GRAY + "The following " + ChatColor.GOLD + "HELMETS" +  ChatColor.DARK_GRAY +" are in the random equipment pool: \n\n";
                    for(ArmourData d : BloodMoon.equipment_pool.ITEM_POOL) {
                        if(d.HELMET!=ArmourData.AIR) {

                            res += ChatColor.GRAY + "  - " +  ChatColor.BLUE +   d.HELMET.material_name + "\n";
                    }}
                    commandSender.sendMessage(res);
                }

                return true;
            }

            if (args[ITEM_CATEGORY].toUpperCase().equals(CHESTPLATE_ARG)) {
                JsonItemstack Chestplate = new JsonItemstack(player.getInventory().getItemInMainHand());

                if(args[ACTION].toLowerCase().equals(ACTION_ADD_ARG)) {

                    boolean added = false;
                    ArrayList<ArmourData> AddedList = new ArrayList<>();

                    for(ArmourData selected_data : BloodMoon.equipment_pool.ITEM_POOL){
                        if(selected_data.CHESTPLATE.equals(ArmourData.AIR) && !added){
                            selected_data.CHESTPLATE = Chestplate;
                            added = true;
                        }
                        AddedList.add(selected_data);
                    }

                    if(!added){
                        AddedList.add(new ArmourData(ArmourData.AIR, Chestplate, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR));
                    }

                    BloodMoon.equipment_pool.ITEM_POOL = AddedList;

                    commandSender.sendMessage(SUCCESS_RESPONSE(CHESTPLATE_ARG, player.getInventory().getItemInMainHand().getType().name()));
                    return true;
                } else if(args[ACTION].toLowerCase().equals(ACTION_LIST_ARG)){
                    String res = ChatColor.DARK_GRAY + "The following " + ChatColor.GOLD + "CHESTPLATES" +  ChatColor.DARK_GRAY +" are in the random equipment pool: \n\n";
                    for(ArmourData d : BloodMoon.equipment_pool.ITEM_POOL) {
                        if (d.CHESTPLATE != ArmourData.AIR) {

                            res += ChatColor.GRAY + "  - " + ChatColor.BLUE + d.CHESTPLATE.material_name + "\n";
                        }
                    }
                    commandSender.sendMessage(res);
                }

                return true;
            }

            if (args[ITEM_CATEGORY].toUpperCase().equals(LEGGINGS_ARG)) {
                JsonItemstack Leggings = new JsonItemstack(player.getInventory().getItemInMainHand());

                if(args[ACTION].toLowerCase().equals(ACTION_ADD_ARG)) {

                    boolean added = false;
                    ArrayList<ArmourData> AddedList = new ArrayList<>();

                    for(ArmourData selected_data : BloodMoon.equipment_pool.ITEM_POOL){
                        if(selected_data.LEGGINGS.equals(ArmourData.AIR) && !added){
                            selected_data.LEGGINGS = Leggings;
                            added = true;
                        }
                        AddedList.add(selected_data);
                    }

                    if(!added){
                        AddedList.add(new ArmourData(ArmourData.AIR, ArmourData.AIR, Leggings, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR));
                    }

                    BloodMoon.equipment_pool.ITEM_POOL = AddedList;

                    commandSender.sendMessage(SUCCESS_RESPONSE(LEGGINGS_ARG, player.getInventory().getItemInMainHand().getType().name()));
                    return true;
                } else if(args[ACTION].toLowerCase().equals(ACTION_LIST_ARG)){
                    String res = ChatColor.DARK_GRAY + "The following " + ChatColor.GOLD + LEGGINGS_ARG + ChatColor.DARK_GRAY +" are in the random equipment pool: \n\n";
                    for(ArmourData d : BloodMoon.equipment_pool.ITEM_POOL) {
                        if(d.LEGGINGS!=ArmourData.AIR) {
                            res += ChatColor.GRAY + "  - " + ChatColor.BLUE + d.LEGGINGS.material_name + "\n";
                        }
                        }
                    commandSender.sendMessage(res);
                }

                return true;
            }

            if (args[ITEM_CATEGORY].toUpperCase().equals(BOOTS_ARG)) {
                JsonItemstack Boots = new JsonItemstack(player.getInventory().getItemInMainHand());

                if(args[ACTION].toLowerCase().equals(ACTION_ADD_ARG)) {

                    boolean added = false;
                    ArrayList<ArmourData> AddedList = new ArrayList<>();

                    for(ArmourData selected_data : BloodMoon.equipment_pool.ITEM_POOL){
                        if(selected_data.BOOTS.equals(ArmourData.AIR) && !added){
                            selected_data.BOOTS = Boots;
                            added = true;
                        }
                        AddedList.add(selected_data);
                    }

                    if(!added){
                        AddedList.add(new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, Boots, ArmourData.AIR, ArmourData.AIR));
                    }

                    BloodMoon.equipment_pool.ITEM_POOL = AddedList;

                    commandSender.sendMessage(SUCCESS_RESPONSE(BOOTS_ARG, player.getInventory().getItemInMainHand().getType().name()));
                    return true;
                } else if(args[ACTION].toLowerCase().equals(ACTION_LIST_ARG)){
                    String res = ChatColor.DARK_GRAY + "The following " + ChatColor.GOLD + BOOTS_ARG +  ChatColor.DARK_GRAY +" are in the random equipment pool: \n\n";
                    for(ArmourData d : BloodMoon.equipment_pool.ITEM_POOL) {
                        if(d.BOOTS!=ArmourData.AIR) {

                            res += ChatColor.GRAY + "  - " +  ChatColor.BLUE +   d.BOOTS.material_name + "\n";
                        }}
                    commandSender.sendMessage(res);
                }

                return true;
            }

            if (args[ITEM_CATEGORY].toUpperCase().equals(MAINHAND_ARG)) {
                JsonItemstack Mainhand = new JsonItemstack(player.getInventory().getItemInMainHand());

                if(args[ACTION].toLowerCase().equals(ACTION_ADD_ARG)) {

                    boolean added = false;
                    ArrayList<ArmourData> AddedList = new ArrayList<>();

                    for(ArmourData selected_data : BloodMoon.equipment_pool.ITEM_POOL){
                        if(selected_data.MAINHAND.equals(ArmourData.AIR) && !added){
                            selected_data.MAINHAND = Mainhand;
                            added = true;
                        }
                        AddedList.add(selected_data);
                    }

                    if(!added){
                        AddedList.add(new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, Mainhand, ArmourData.AIR));
                    }

                    BloodMoon.equipment_pool.ITEM_POOL = AddedList;

                    commandSender.sendMessage(SUCCESS_RESPONSE(MAINHAND_ARG, player.getInventory().getItemInMainHand().getType().name()));
                    return true;
                } else if(args[ACTION].toLowerCase().equals(ACTION_LIST_ARG)){
                    String res = ChatColor.DARK_GRAY + "The following " + ChatColor.GOLD + MAINHAND_ARG +  ChatColor.DARK_GRAY +" items are in the random equipment pool: \n\n";
                    for(ArmourData d : BloodMoon.equipment_pool.ITEM_POOL) {
                        if(d.MAINHAND!=ArmourData.AIR) {

                            res += ChatColor.GRAY + "  - " +  ChatColor.BLUE +   d.MAINHAND.material_name + "\n";
                        }}
                    commandSender.sendMessage(res);
                }


                return true;
            }

            if (args[ITEM_CATEGORY].toUpperCase().equals(OFFHAND_ARG)) {
                JsonItemstack Offhand = new JsonItemstack(player.getInventory().getItemInMainHand());

                if(args[ACTION].toLowerCase().equals(ACTION_ADD_ARG)) {

                    boolean added = false;
                    ArrayList<ArmourData> AddedList = new ArrayList<>();

                    for(ArmourData selected_data : BloodMoon.equipment_pool.ITEM_POOL){
                        if(selected_data.OFFHAND.equals(ArmourData.AIR) && !added){
                            selected_data.OFFHAND = Offhand;
                            added = true;
                        }
                        AddedList.add(selected_data);
                    }

                    if(!added){
                        AddedList.add(new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, Offhand));
                    }

                    BloodMoon.equipment_pool.ITEM_POOL = AddedList;

                    commandSender.sendMessage(SUCCESS_RESPONSE(OFFHAND_ARG, player.getInventory().getItemInMainHand().getType().name()));
                    return true;
                } else if(args[ACTION].toLowerCase().equals(ACTION_LIST_ARG)){
                    String res = ChatColor.DARK_GRAY + "The following " + ChatColor.GOLD + OFFHAND_ARG +  ChatColor.DARK_GRAY +" items are in the random equipment pool: \n\n";
                    for(ArmourData d : BloodMoon.equipment_pool.ITEM_POOL) {
                        if(d.OFFHAND!=ArmourData.AIR) {

                            res += ChatColor.GRAY + "  - " +  ChatColor.BLUE +   d.OFFHAND.material_name + "\n";
                        }}
                    commandSender.sendMessage(res);
                }

                return true;
            }

            commandSender.sendMessage(COMMAND_HELP);
            return true;



        }
        commandSender.sendMessage(COMMAND_HELP);


        //NEW STUFF 1.4
       // EquipmentGUI.reloadItems();
       // Inventory inv = EquipmentGUI.getCONFIG_INVENTORY();
       // player.openInventory(inv);


        return true;






    }
}
