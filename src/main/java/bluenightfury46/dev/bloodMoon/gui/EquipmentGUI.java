package bluenightfury46.dev.bloodMoon.gui;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class EquipmentGUI {

//   Inventory CONFIG_INVENTORY;

    public static final int INVENTORY_COLUMNS = 9;
    public static final int INVENTORY_ROWS = 3;
    public static final String INVENTORY_TITLE = "Blood Moon";

    public static Inventory getCONFIG_INVENTORY(Server server){
        Inventory inventory = server.createInventory(null, (INVENTORY_COLUMNS*INVENTORY_ROWS), INVENTORY_TITLE);
        inventory.addItem(genGUIItem(Material.REDSTONE, 1, "Test Item", "test"));

        return inventory;
    }

    public static ItemStack genGUIItem(Material m, final int count, String name, String...Lore){
        ItemStack itemStack = new ItemStack(m, count);

        ItemMeta meta = itemStack.getItemMeta();

       meta.setItemName(name);
       meta.setLore(List.of(Lore));

       itemStack.setItemMeta(meta);


        return itemStack;
    }


}
