package bluenightfury46.dev.bloodMoon;

import bluenightfury46.dev.bloodMoon.events.BlockBedStuff;
import bluenightfury46.dev.bloodMoon.events.MoonEntitySpawnEv;
import bluenightfury46.dev.bloodMoon.gui.EquipmentGUI;
import bluenightfury46.dev.bloodMoon.gui.GUIEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class BloodMoon extends JavaPlugin {

  @NotNull public static BloodMoon plugin;

  //static File JSON_FILE = new File(this.getDataFolder() + "\\" + Moon.JSON_FILENAME);
  @NotNull public static ArmourData data;
  @NotNull public static double BLOODMOON_CHANCE = 100.0;
  @NotNull public static boolean BLOODMOON_ALLOWSLEEP = false;
  @NotNull public static boolean DO_BLOODMOONS = true;
    @NotNull public static String BLOODMOON_SUBTITLE = "§4Mobs you find will have stronger armour§4";
    @NotNull public static String BLOODMOON_TITLE = "§cThe Blood Moon Rises Once Again!§c";
    @NotNull public static String BLOODMOON_CHATMESSAGE = "§4As the blood moon rises the countless monsters band together, you encounter mobs with stronger gear...§4";
    @NotNull public static String BED_ENTER_FAIL_MESSAGE = (ChatColor.DARK_RED + "" + ChatColor.BOLD + "You can not enter the bed during a blood moon... Good luck");
  @NotNull public static HashMap<World, Boolean> ACTIVE_BLOODMOON = new HashMap<>();

  @NotNull  public static List<String> DISABLED_WORLDS = new ArrayList<>();


    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        

       loadConfig();
       EquipmentGUI.genCONFIG_INVENTORY();





        getCommand("setequipement").setExecutor(new MoonCommands());
        getCommand("setequipement").setTabCompleter(new MoonTabComplete());

        getCommand("reload-bloodmoon").setExecutor(new ReloadCommand());

        getCommand("trigger").setExecutor(new TriggerBloodMoon());
        getCommand("trigger").setTabCompleter(new MoonTabComplete());

        Bukkit.getPluginManager().registerEvents(new MoonEntitySpawnEv(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBedStuff(), this);

        //1.4
        Bukkit.getPluginManager().registerEvents(new GUIEvents(), this);







    }


    void loadConfig(){
        File file = new File(getDataFolder(), "data.json");
        //   data = Moon.jsonInit(file);
        data = Moon.jsonInit(file);



        this.saveDefaultConfig();

        BLOODMOON_CHANCE = this.getConfig().getDouble("bloodmoon-chance");
        if(BLOODMOON_CHANCE <= 0){
            BLOODMOON_CHANCE = 1;
            this.getLogger().warning("bloodmoon-chance value can not be less than, or equal to zero! Defaulting to 1. If this is a mistake, please modify the config.yml and change the value back to an appropriate value");
        }
        if(BLOODMOON_CHANCE > 100){
            BLOODMOON_CHANCE = 100;
            this.getLogger().warning("bloodmoon-chance value can not greater than 100! Defaulting to 100. If this is a mistake, please modify the config.yml and change the value back to an appropriate value");
        }




        //NEW AS OF VERSION 1.2... TRY CATCH EXISTS IF THE CONFIG IS OLD
        try {
            BLOODMOON_ALLOWSLEEP = getConfig().getBoolean("allow-sleep");
        }catch(NullPointerException e){
            BloodMoon.plugin.getLogger().warning("WARNING! allow-sleep in the config.yml is missing, please add it... Defaulting to false");
            BLOODMOON_ALLOWSLEEP = false;
        }
        //1.2 END


        //NEW CONFIG OPTION AS OF VERSION 1.2.1, EXISTS IN THE EVENT OF AN OLD CONFIG
        try{
            BED_ENTER_FAIL_MESSAGE = getConfig().getString("messages.bloodmoon-bedenter");
        } catch(NullPointerException e){
            //Check is probably redundant, but I'm not risking it in case getConfig returns null, or something
            BloodMoon.plugin.getLogger().warning("WARNING! messages.bloodmoon-bedenter in the config.yml is missing, please add it... Applying default value");
            BED_ENTER_FAIL_MESSAGE = (ChatColor.DARK_RED + "" + ChatColor.BOLD + "You can not enter the bed during a blood moon... Good luck");
        }
        //1.2.1 END





        BLOODMOON_TITLE = getConfig().getString("messages.blood-moon-title-message");



        BLOODMOON_SUBTITLE = getConfig().getString("messages.blood-moon-subtitle-message");
        BLOODMOON_CHATMESSAGE = getConfig().getString("messages.blood-moon-chat-message");


        //Weird check for something that probably doesn't work? It doesn't break anything though so I'm not changing it
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


        //NEW AS OF 1.5

        try {
            if (!getConfig().isSet("disabled-worlds")) {
                getLogger().severe("Error! disabled-worlds is not set... Unexpected errors may occur");
            } else {

                DISABLED_WORLDS = getConfig().getStringList("disabled-worlds");

            }
        } catch(NullPointerException ex){
            getLogger().warning("Warning! disabled-worlds failed to load...");

        }





    }



    @Override
    public void onDisable() {

        // Plugin shutdown logic
        this.saveDefaultConfig();
        File JSON_FILE = new File(getDataFolder(),"data.json");
        Moon.jsonSave(data, JSON_FILE);

        //1.4 - remove armour
        for(World world : this.getServer().getWorlds()){
            for (LivingEntity livingEntity : world.getLivingEntities()) {
                if (livingEntity.getType().equals(EntityType.ZOMBIE)) {
                    MoonEntitySpawnEv.removeItems(livingEntity);
                }

            }
        }

        //END OF 1.4
    }
}
