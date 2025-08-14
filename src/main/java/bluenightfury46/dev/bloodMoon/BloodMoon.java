package bluenightfury46.dev.bloodMoon;

import bluenightfury46.dev.bloodMoon.events.MoonEntitySpawnEv;
import bluenightfury46.dev.bloodMoon.json.JsonItemstack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class BloodMoon extends JavaPlugin {

  @NotNull public static BloodMoon plugin;

  //static File JSON_FILE = new File(this.getDataFolder() + "\\" + Moon.JSON_FILENAME);
  @NotNull public static ArmourData data;
  @NotNull public static double BLOODMOON_CHANCE = 100.0;
  @NotNull public static boolean DO_BLOODMOONS = true;
    @NotNull public static String BLOODMOON_SUBTITLE = "§4Mobs you find will have stronger armour§4";
    @NotNull public static String BLOODMOON_TITLE = "§cThe Blood Moon Rises Once Again!§c";
    @NotNull public static String BLOODMOON_CHATMESSAGE = "§4As the blood moon rises the countless monsters band together, you encounter mobs with stronger gear...§4";
  @NotNull public static HashMap<World, Boolean> ACTIVE_BLOODMOON = new HashMap<>();


    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;
        File file = new File(getDataFolder(), "data.json");
     //   data = Moon.jsonInit(file);
         data = Moon.jsonInit(file);



        this.saveDefaultConfig();



        BLOODMOON_CHANCE = this.getConfig().getInt("bloodmoon-chance");
        if(BLOODMOON_CHANCE <= 0){
            BLOODMOON_CHANCE = 1;
            this.getLogger().warning("bloodmoon-chance value can not be less than, or equal to zero! Defaulting to 1. If this is a mistake, please modify the config.yml and change the value back to an appropriate value");
        }
        if(BLOODMOON_CHANCE > 100){
            BLOODMOON_CHANCE = 100;
            this.getLogger().warning("bloodmoon-chance value can not greater than 100! Defaulting to 100. If this is a mistake, please modify the config.yml and change the value back to an appropriate value");
        }



        BLOODMOON_TITLE = getConfig().getString("messages.blood-moon-title-message");



        BLOODMOON_SUBTITLE = getConfig().getString("messages.blood-moon-subtitle-message");
        BLOODMOON_CHATMESSAGE = getConfig().getString("messages.blood-moon-chat-message");

        DO_BLOODMOONS = getConfig().getBoolean("do-bloodmoons");
        try {
            if (DO_BLOODMOONS && !DO_BLOODMOONS) {
                DO_BLOODMOONS = true;
                getLogger().warning("Warning! do-bloodmoons failed to load... Defaulting to true");
            }
        }catch (NullPointerException e){
            DO_BLOODMOONS = true;
            getLogger().warning("Warning! do-bloodmoons failed to load... Defaulting to true");
        }





        getCommand("setequipement").setExecutor(new MoonCommands());
        getCommand("setequipement").setTabCompleter(new MoonTabComplete());

        getCommand("trigger").setExecutor(new TriggerBloodMoon());
        getCommand("trigger").setTabCompleter(new MoonTabComplete());
        Bukkit.getPluginManager().registerEvents(new MoonEntitySpawnEv(), this);







    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        File JSON_FILE = new File(getDataFolder(),"data.json");
        Moon.jsonSave(data, JSON_FILE);
    }
}
