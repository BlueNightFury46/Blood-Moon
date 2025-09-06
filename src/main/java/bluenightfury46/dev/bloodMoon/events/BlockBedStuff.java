package bluenightfury46.dev.bloodMoon.events;

import bluenightfury46.dev.bloodMoon.BloodMoon;
import bluenightfury46.dev.bloodMoon.Moon;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BlockBedStuff implements Listener {

    @EventHandler
    public void event(PlayerBedEnterEvent e){



        if(!BloodMoon.BLOODMOON_ALLOWSLEEP){

            try {
                if (BloodMoon.ACTIVE_BLOODMOON.containsKey(e.getPlayer().getWorld())){
                    if (BloodMoon.ACTIVE_BLOODMOON.get(e.getPlayer().getWorld())) {

                        e.getPlayer().sendMessage(BloodMoon.BED_ENTER_FAIL_MESSAGE);
                        e.setCancelled(true);





                    }
            }
            } catch(NullPointerException ex){

            }

        }
    }
}
