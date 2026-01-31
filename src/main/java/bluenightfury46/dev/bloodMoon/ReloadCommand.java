package bluenightfury46.dev.bloodMoon;

import bluenightfury46.dev.bloodMoon.json.random.MoonRandom;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class ReloadCommand implements CommandExecutor {



    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if(!commandSender.hasPermission("bloodmoon.reload")){
            commandSender.sendMessage(ChatColor.RED+"You don't have permission to use that command...");
            return true;
        }



        BloodMoon.plugin.saveDefaultConfig();
        BloodMoon.plugin.reloadConfig();





            File file = new File(BloodMoon.plugin.getDataFolder(), "data.json");

            if(!file.exists()){
                Moon.jsonSave(BloodMoon.data, file);

            } else {
                //   data = Moon.jsonInit(file);
                BloodMoon.data = Moon.jsonInit(file);

                Moon.jsonSave(BloodMoon.data, file);
            }


        //VERSION 1.6
        File random = new File(BloodMoon.plugin.getDataFolder(), "equipment_pool.json");
        if(random.exists()) {
            //   data = Moon.jsonInit(file);
            BloodMoon.plugin.equipment_pool = MoonRandom.jsonInit(random);


            //VERSION 1.6
            MoonRandom.jsonSave(BloodMoon.plugin.equipment_pool, random);
        } else {
            MoonRandom.jsonSave(BloodMoon.plugin.equipment_pool, random);

        }


            BloodMoon.plugin.saveDefaultConfig();

            BloodMoon.BLOODMOON_CHANCE = BloodMoon.plugin.getConfig().getDouble("bloodmoon-chance");
            if(BloodMoon.BLOODMOON_CHANCE <= 0){
                BloodMoon.BLOODMOON_CHANCE = 1;
                commandSender.sendMessage(ChatColor.YELLOW+"bloodmoon-chance value can not be less than, or equal to zero! Defaulting to 1. If this is a mistake, please modify the config.yml and change the value back to an appropriate value");
            }
            if(BloodMoon.BLOODMOON_CHANCE > 100){
                BloodMoon.BLOODMOON_CHANCE = 100;
                commandSender.sendMessage(ChatColor.YELLOW+"bloodmoon-chance value can not greater than 100! Defaulting to 100. If this is a mistake, please modify the config.yml and change the value back to an appropriate value");
            }


        //NEW AS OF 1.2
        try {
            BloodMoon.plugin.BLOODMOON_ALLOWSLEEP = BloodMoon.plugin.getConfig().getBoolean("allow-sleep");
        }catch(NullPointerException e){
            BloodMoon.plugin.getLogger().warning("WARNING! allow-sleep in the config.yml is missing, please add it... Defaulting to false");
            BloodMoon.plugin.BLOODMOON_ALLOWSLEEP = false;
        }

        //NEW CONFIG OPTION AS OF VERSION 1.2.1, EXISTS IN THE EVENT OF AN OLD CONFIG
        try{
            BloodMoon.BED_ENTER_FAIL_MESSAGE = BloodMoon.plugin.getConfig().getString("messages.bloodmoon-bedenter");
        } catch(NullPointerException e){
            //Check is probably redundant, but I'm not risking it in case getConfig returns null, or something
            BloodMoon.plugin.getLogger().warning("WARNING! messages.bloodmoon-bedenter in the config.yml is missing, please add it... Applying default value");
            BloodMoon.BED_ENTER_FAIL_MESSAGE = (ChatColor.DARK_RED + "" + ChatColor.BOLD + "You can not enter the bed during a blood moon... Good luck");
        }
        //1.2.1 END


           /* try{
                double CONFIG_DOUBLE_DROPCHANCE = BloodMoon.plugin.getConfig().getDouble("mob-dropchance");

                if(CONFIG_DOUBLE_DROPCHANCE < 0){
                    CONFIG_DOUBLE_DROPCHANCE = 0;
                    commandSender.sendMessage(ChatColor.YELLOW+"bloodmoon-chance value can not be less than, or equal to zero! Defaulting to 1. If this is a mistake, please modify the config.yml and change the value back to an appropriate value");
                }
                if(CONFIG_DOUBLE_DROPCHANCE > 100){
                    CONFIG_DOUBLE_DROPCHANCE = 100;
                    commandSender.sendMessage(ChatColor.YELLOW+"bloodmoon-chance value can not greater than 100! Defaulting to 100. If this is a mistake, please modify the config.yml and change the value back to an appropriate value");
                }

                BloodMoon.ARMOUR_DROPCHANCE = (float)(CONFIG_DOUBLE_DROPCHANCE/100);
            } catch(NullPointerException e){
                commandSender.sendMessage(ChatColor.YELLOW+"Error! mob-dropchance in config.yml was missing... Please add it and restart the server. Defaulting to 0% chance");
                BloodMoon.ARMOUR_DROPCHANCE = 0.0f;
            }*/



            BloodMoon.BLOODMOON_TITLE = BloodMoon.plugin.getConfig().getString("messages.blood-moon-title-message");



            BloodMoon.BLOODMOON_SUBTITLE = BloodMoon.plugin.getConfig().getString("messages.blood-moon-subtitle-message");
            BloodMoon.BLOODMOON_CHATMESSAGE = BloodMoon.plugin.getConfig().getString("messages.blood-moon-chat-message");

            BloodMoon.DO_BLOODMOONS = BloodMoon.plugin.getConfig().getBoolean("do-bloodmoons");
            try {
                if (BloodMoon.DO_BLOODMOONS && !BloodMoon.DO_BLOODMOONS) {
                    BloodMoon.DO_BLOODMOONS = true;
                    commandSender.sendMessage(ChatColor.YELLOW+"Warning! do-bloodmoons failed to load... Defaulting to true");
                }
            }catch (NullPointerException e){
                BloodMoon.DO_BLOODMOONS = true;
                commandSender.sendMessage(ChatColor.YELLOW+"Warning! do-bloodmoons failed to load... Defaulting to true");
            }

        //NEW AS OF 1.5

        try {
            if (!BloodMoon.plugin.getConfig().isSet("disabled-worlds")) {
                BloodMoon.plugin.getLogger().severe("Error! disabled-worlds is not set... Unexpected errors may occur");
            } else {

                BloodMoon.DISABLED_WORLDS = BloodMoon.plugin.getConfig().getStringList("disabled-worlds");

            }
        } catch(NullPointerException ex){
            BloodMoon.plugin.getLogger().warning("Warning! disabled-worlds failed to load...");

        }

            //TODO If anyone ever forks this plugin in the future, make sure to add your name here in the credits
            commandSender.sendMessage(ChatColor.GREEN+"Reloaded " + ChatColor.RED + BloodMoon.plugin.getPluginMeta().getName() + ChatColor.GREEN+" version " + ChatColor.GOLD + BloodMoon.plugin.getPluginMeta().getVersion() + ChatColor.GREEN + " by " + ChatColor.BLUE + "BlueNightFury46");

          return true;





    }
}
