import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EquipmentGUI {

//   Inventory CONFIG_INVENTORY;

    public static final int INVENTORY_SIZE = 9;
    public static final String INVENTORY_TITLE = "Blood Moon";

    public static Inventory getCONFIG_INVENTORY(Server server, Player holder){
        Inventory inventory = server.createInventory(null, INVENTORY_SIZE, INVENTORY_TITLE);
        inventory.addItem(genGUIItem(Material.REDSTONE, 1, "Test Item", "test"));

        return inventory;
    }

    public static ItemStack genGUIItem(Material m, final int count, String name, String...Lore){
        ItemStack itemStack = new ItemStack(m, count);

        itemStack.getItemMeta().setItemName(name);
        itemStack.getItemMeta().setLore(List.of(Lore));


        return itemStack;
    }


}
