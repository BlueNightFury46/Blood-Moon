package bluenightfury46.dev.bloodMoon.json;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

public class JsonEnchantments {

    @NotNull public String ENCHANTMENT;
    @NotNull public int LEVEL;

   public static Enchantment getFromString(String s){
       return Registry.ENCHANTMENT.get(NamespacedKey.fromString(s));
    }

    public Enchantment toEnchant(){
        return Registry.ENCHANTMENT.get(NamespacedKey.fromString(ENCHANTMENT));
    }

    public static String toString(Enchantment e){
        return Registry.ENCHANTMENT.getKey(e).asString();
    }


    public JsonEnchantments(Enchantment e, int l){
        ENCHANTMENT =  JsonEnchantments.toString(e);
        LEVEL = l;
    }



}
