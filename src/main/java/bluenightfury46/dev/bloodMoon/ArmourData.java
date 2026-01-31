package bluenightfury46.dev.bloodMoon;

import bluenightfury46.dev.bloodMoon.json.JsonItemstack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ArmourData {

   @NotNull public JsonItemstack HELMET;
    @NotNull public JsonItemstack CHESTPLATE;
    @NotNull public JsonItemstack LEGGINGS;
    @NotNull public JsonItemstack BOOTS;
    @NotNull public JsonItemstack MAINHAND;
    @NotNull public JsonItemstack OFFHAND;

    public static final JsonItemstack AIR = new JsonItemstack(new ItemStack(Material.AIR));

    public ArmourData(JsonItemstack H, JsonItemstack C, JsonItemstack L, JsonItemstack B, JsonItemstack M, JsonItemstack O){
        HELMET = H;
        CHESTPLATE = C;
        LEGGINGS = L;
        BOOTS = B;
        MAINHAND = M;
        OFFHAND = O;
    }





}
