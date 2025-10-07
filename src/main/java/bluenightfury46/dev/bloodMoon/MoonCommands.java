package bluenightfury46.dev.bloodMoon;

import bluenightfury46.dev.bloodMoon.json.JsonItemstack;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import bluenightfury46.dev.bloodMoon.gui.*;

public class MoonCommands implements CommandExecutor {

    final String COMMAND_HELP = ChatColor.BLUE + "Command Usage:\n/setequipement "+ChatColor.GOLD+"<ITEM_SLOT>";

    String SUCCESS_RESPONSE(String ARMOUR_PIECE, String ITEM_NAME){
        return (ChatColor.GREEN+"Successfully changed " + ChatColor.AQUA + ARMOUR_PIECE + ChatColor.GREEN + " to " + ChatColor.GOLD + ITEM_NAME);
    }


    final static String HELMET_ARG = "HELMET";
    final static String CHESTPLATE_ARG = "CHESTPLATE";
    final static String LEGGINGS_ARG = "LEGGINGS";
    final static String BOOTS_ARG = "BOOTS";
    final static String MAINHAND_ARG = "MAINHAND";
    final static String OFFHAND_ARG = "OFFHAND";

    final int ITEM_CATEGORY = 0;



    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "ONLY PLAYERS CAN USE THIS COMMAND...");
            return true;
        }

        if(!commandSender.hasPermission("bloodmoon.setequipement")){
            commandSender.sendMessage(ChatColor.RED + "You don't have permission to use this command...");
            return true;
        }



        if(args.length>0) {

            Player player = ((Player) commandSender).getPlayer();


            if (args[ITEM_CATEGORY].toUpperCase().equals(HELMET_ARG)) {
                BloodMoon.data.HELMET = new JsonItemstack(player.getInventory().getItemInMainHand());
                commandSender.sendMessage(SUCCESS_RESPONSE(HELMET_ARG, player.getInventory().getItemInMainHand().getType().name()));
                return true;
            }

            if (args[ITEM_CATEGORY].toUpperCase().equals(CHESTPLATE_ARG)) {
                BloodMoon.data.CHESTPLATE = new JsonItemstack(player.getInventory().getItemInMainHand());
                commandSender.sendMessage(SUCCESS_RESPONSE(CHESTPLATE_ARG, player.getInventory().getItemInMainHand().getType().name()));

                return true;
            }

            if (args[ITEM_CATEGORY].toUpperCase().equals(LEGGINGS_ARG)) {
                BloodMoon.data.LEGGINGS = new JsonItemstack(player.getInventory().getItemInMainHand());
                commandSender.sendMessage(SUCCESS_RESPONSE(LEGGINGS_ARG, player.getInventory().getItemInMainHand().getType().name()));

                return true;
            }

            if (args[ITEM_CATEGORY].toUpperCase().equals(BOOTS_ARG)) {
                BloodMoon.data.BOOTS = new JsonItemstack(player.getInventory().getItemInMainHand());
                commandSender.sendMessage(SUCCESS_RESPONSE(BOOTS_ARG, player.getInventory().getItemInMainHand().getType().name()));
                return true;
            }

            if (args[ITEM_CATEGORY].toUpperCase().equals(MAINHAND_ARG)) {
                BloodMoon.data.MAINHAND = new JsonItemstack(player.getInventory().getItemInMainHand());
                commandSender.sendMessage(SUCCESS_RESPONSE(MAINHAND_ARG, player.getInventory().getItemInMainHand().getType().name()));

                return true;
            }

            if (args[ITEM_CATEGORY].toUpperCase().equals(OFFHAND_ARG)) {
                BloodMoon.data.OFFHAND = new JsonItemstack(player.getInventory().getItemInMainHand());
                commandSender.sendMessage(SUCCESS_RESPONSE(OFFHAND_ARG, player.getInventory().getItemInMainHand().getType().name()));
                return true;
            }



        }


        //NEW STUFF
        if(args.length>=0){
            EquipmentGUI

        }



        commandSender.sendMessage(COMMAND_HELP);
        return true;


    }
}
