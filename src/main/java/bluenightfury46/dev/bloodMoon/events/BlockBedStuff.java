package bluenightfury46.dev.bloodMoon.events;

import bluenightfury46.dev.bloodMoon.BloodMoon;
import org.bukkit.ChatColor;
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

                        e.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You can not enter the bed during a blood moon... Good luck");
                        e.setCancelled(true);


                    }
            }
            } catch(NullPointerException ex){

            }

        }
    }
}
