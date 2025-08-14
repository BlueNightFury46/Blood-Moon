package bluenightfury46.dev.bloodMoon;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MoonTabComplete implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (command.getName().equalsIgnoreCase("setequipement")) {
            if (args.length >= 0) {
                if (args.length == 1) {
                    List<String> list = new ArrayList<>();
                    if (commandSender.isOp() || commandSender.hasPermission("bloodmoon.setequipement")) {
                        list.add(MoonCommands.HELMET_ARG);
                        list.add(MoonCommands.CHESTPLATE_ARG);
                        list.add(MoonCommands.LEGGINGS_ARG);
                        list.add(MoonCommands.BOOTS_ARG);
                        list.add(MoonCommands.MAINHAND_ARG);
                        list.add(MoonCommands.OFFHAND_ARG);


                        return list;
                    }
                }
            }
        }

        if (command.getName().equalsIgnoreCase("trigger")) {
            if(args.length>0){
                List<String> list = new ArrayList<>();
                if (commandSender.isOp() || commandSender.hasPermission("bloodmoon.trigger")) {
                    for(World world : BloodMoon.plugin.getServer().getWorlds()){
                        list.add(world.getName());
                    }


                    return list;
                }
            }
        }
        return null;
    }
}