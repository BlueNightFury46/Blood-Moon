package bluenightfury46.dev.bloodMoon.json.random;

import bluenightfury46.dev.bloodMoon.ArmourData;
import bluenightfury46.dev.bloodMoon.json.JsonEnchantments;
import bluenightfury46.dev.bloodMoon.json.JsonItemstack;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonEquipment {

    @NotNull public ArrayList<ArmourData> ITEM_POOL = new ArrayList<>();
    @NotNull public boolean RANDOM_EQUIPMENT = false;

    public JsonEquipment(ArmourData Item){
        ITEM_POOL.add(Item);

    }

    public JsonEquipment(ArrayList<ArmourData> items){
        ITEM_POOL.addAll(items);
    }

    public JsonEquipment(){
        ITEM_POOL.add(new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR));
    }


}
