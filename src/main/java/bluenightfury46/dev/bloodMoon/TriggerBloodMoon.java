package bluenightfury46.dev.bloodMoon;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TriggerBloodMoon implements CommandExecutor {

    final int WORLD_ARGUMENT = 0;
    final int TIME_MIDNIGHT = 18000;

    final String HELP = ChatColor.BLUE+"Command Usage:\n/trigger" + ChatColor.GREEN + "<world>";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if(!commandSender.hasPermission("bloodmoon.trigger")){
            commandSender.sendMessage(ChatColor.RED+"You don't have permission to use that command...");
            return true;
        }

        if(!BloodMoon.DO_BLOODMOONS){
            commandSender.sendMessage(ChatColor.YELLOW+"Warning! Natural Blood moons are currently disabled in the config.yml...");
        }

        if(args.length>0){

            for(World world : BloodMoon.plugin.getServer().getWorlds()){

                if(world.getName().equalsIgnoreCase(args[WORLD_ARGUMENT])){
                    world.setFullTime(TIME_MIDNIGHT);
                    if(!BloodMoon.ACTIVE_BLOODMOON.containsKey(world)){
                        BloodMoon.ACTIVE_BLOODMOON.put(world, true);
                    } else {
                        BloodMoon.ACTIVE_BLOODMOON.replace(world, true);

                    }

                    for(Player p : world.getPlayers()){
                        p.sendMessage(BloodMoon.BLOODMOON_CHATMESSAGE);
                        p.sendTitle(BloodMoon.BLOODMOON_TITLE, BloodMoon.BLOODMOON_SUBTITLE);
                        world.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.2f, 1.0f);
                    }

                    commandSender.sendMessage(ChatColor.BLUE+"Successfully triggered Blood Moon in world " + ChatColor.GOLD+world.getName() + ChatColor.BLUE + "...");
                    return true;

                }


            }

            commandSender.sendMessage(ChatColor.RED+"Error, world \"" + ChatColor.GOLD + args[WORLD_ARGUMENT]+ ChatColor.RED + "\" not found...");
            return true;


        }


        if((commandSender instanceof Player)){
            Player player = (Player) commandSender;

            player.getWorld().setFullTime(TIME_MIDNIGHT);
            if(!BloodMoon.ACTIVE_BLOODMOON.containsKey(player.getWorld())){
            BloodMoon.ACTIVE_BLOODMOON.put(player.getWorld(), true);
            } else {
                BloodMoon.ACTIVE_BLOODMOON.replace(player.getWorld(), true);

            }

            for(Player p : player.getWorld().getPlayers()){
                p.sendMessage(BloodMoon.BLOODMOON_CHATMESSAGE);
                p.sendTitle(BloodMoon.BLOODMOON_TITLE, BloodMoon.BLOODMOON_SUBTITLE);
                player.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.2f, 1.0f);
            }

            player.sendMessage(ChatColor.BLUE+"Successfully triggered Blood Moon...");
return true;

        } else {

            commandSender.sendMessage(HELP);
            return true;

        }


    }
}
