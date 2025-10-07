package bluenightfury46.dev.bloodMoon.json;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class JsonItemstack {

    @NotNull public String material_name;
   @NotNull public ArrayList<JsonEnchantments> enchantments = new ArrayList<>();




    public JsonItemstack(ItemStack item){

        material_name = item.getType().name();
        enchantments.add(new JsonEnchantments(Enchantment.BINDING_CURSE, 1));

        enchantments.remove(new JsonEnchantments(Enchantment.BINDING_CURSE, 1));

        if(item.getEnchantments().values().size()>0) {
            Set<Enchantment> enchantment = item.getEnchantments().keySet();
            for (Enchantment e : enchantment) {

                enchantments.add(new JsonEnchantments(e, item.getEnchantments().get(e)));


            }
        }

    }


    public ItemStack toItemStack(){
        ItemStack return_item = new ItemStack(Material.getMaterial(material_name));

        //Screw Java
        try {
            if (enchantments.isEmpty()) {
                return return_item;
            }
            //WHICH PROGRAMMING LANGUAGE LEAVES NO WAY TO INITIALISE A F****** ARRAY INLINE...
        }catch (NullPointerException e){
            return return_item;
        }

        for(JsonEnchantments enchant : enchantments){

                return_item.addUnsafeEnchantment(enchant.toEnchant(), enchant.LEVEL);

        }

        return return_item;
    }

    public Map<Enchantment, Integer> asEnchantments(){
        Map<Enchantment, Integer> enchantMap = new HashMap<>();
        for(JsonEnchantments enchant : enchantments){
            enchantMap.put(enchant.toEnchant(), enchant.LEVEL);
        }

        return enchantMap;
    }
}
