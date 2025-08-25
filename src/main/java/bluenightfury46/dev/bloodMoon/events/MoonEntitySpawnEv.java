package bluenightfury46.dev.bloodMoon.events;

import bluenightfury46.dev.bloodMoon.BloodMoon;
import bluenightfury46.dev.bloodMoon.Moon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.Objects;

public class MoonEntitySpawnEv implements Listener {

    @NotNull
    final long FULL_MOON = 0;

    public long getPhase(World w){

        return w.getFullTime() / 24000;


    }


    public boolean isValidHelmet(LivingEntity e){

        if(!e.getEquipment().getHelmet().getType().equals(Material.AIR)){
            return false;
        }
        return true;

    }

    public boolean isValidChestplate(LivingEntity e){

        if(!e.getEquipment().getChestplate().getType().equals(Material.AIR)){
            return false;
        }
        return true;

    }

    public boolean isValidLeggings(LivingEntity e){

        if(!e.getEquipment().getLeggings().getType().equals(Material.AIR)){
            return false;
        }
        return true;

    }

    public boolean isValidBoots(LivingEntity e){

        if(!e.getEquipment().getBoots().getType().equals(Material.AIR)){
            return false;
        }
        return true;

    }

    public boolean isValidOffhand(LivingEntity e){

        if(!e.getEquipment().getItemInOffHand().getType().equals(Material.AIR)){
            return false;
        }
        return true;

    }

    public boolean isValidMainhand(LivingEntity e){

        if(!e.getEquipment().getItemInMainHand().getType().equals(Material.AIR)){
            return false;
        }
        return true;

    }


    void removeItems(LivingEntity e){
        if(e.getEquipment().getHelmet().getType().equals(BloodMoon.data.HELMET.toItemStack().getType())){
            e.getEquipment().setHelmet(ItemStack.of(Material.AIR));

        }

        if(e.getEquipment().getChestplate().getType().equals(BloodMoon.data.CHESTPLATE.toItemStack().getType())){
            e.getEquipment().setChestplate(ItemStack.of(Material.AIR));
            //TODO Add Enchantment check

        }

        if(e.getEquipment().getLeggings().getType().equals(BloodMoon.data.LEGGINGS.toItemStack().getType())){
            e.getEquipment().setLeggings(ItemStack.of(Material.AIR));

        }

        if(e.getEquipment().getBoots().getType().equals(BloodMoon.data.BOOTS.toItemStack().getType())){
            e.getEquipment().setBoots(ItemStack.of(Material.AIR));

        }

        if(e.getEquipment().getItemInMainHand().getType().equals(BloodMoon.data.MAINHAND.toItemStack().getType())){
            e.getEquipment().setItemInMainHand(ItemStack.of(Material.AIR));

        }

        if(e.getEquipment().getItemInOffHand().getType().equals(BloodMoon.data.OFFHAND.toItemStack().getType())){
            e.getEquipment().setItemInOffHand(ItemStack.of(Material.AIR));

        }
    }


    void BLOODMOON_ACTIVATE(LivingEntity e){


        if(isValidHelmet(e)){
           Objects.requireNonNull(e.getEquipment()).setHelmet(BloodMoon.data.HELMET.toItemStack());
           e.getEquipment().setHelmetDropChance(0.0f);
       }

       if(isValidChestplate(e)){
           Objects.requireNonNull(e.getEquipment()).setChestplate(BloodMoon.data.CHESTPLATE.toItemStack());
           e.getEquipment().setChestplateDropChance(0.0f);

       }

        if(isValidLeggings(e)){
            Objects.requireNonNull(e.getEquipment()).setLeggings(BloodMoon.data.LEGGINGS.toItemStack());
            e.getEquipment().setLeggingsDropChance(0.0f);


        }

        if(isValidBoots(e)){
            Objects.requireNonNull(e.getEquipment()).setBoots(BloodMoon.data.BOOTS.toItemStack());
            e.getEquipment().setBootsDropChance(0.0f);

        }

        if(isValidMainhand(e)){
            Objects.requireNonNull(e.getEquipment()).setItemInMainHand(BloodMoon.data.MAINHAND.toItemStack());
            e.getEquipment().setItemInMainHandDropChance(0.0f);

        }

        if(isValidOffhand(e)){
            Objects.requireNonNull(e.getEquipment()).setItemInOffHand(BloodMoon.data.OFFHAND.toItemStack());
            e.getEquipment().setItemInOffHandDropChance(0.0f);


        }



    }

    @EventHandler
    public void spawn_event(org.bukkit.event.entity.EntitySpawnEvent event){

        if (BloodMoon.DO_BLOODMOONS){

            if(!event.getEntityType().equals(EntityType.ZOMBIE)){
                return;
            }

            World world = event.getEntity().getWorld();



                if (world.getFullTime() < 23000 && world.getFullTime() > 13000) {


                    if(!BloodMoon.ACTIVE_BLOODMOON.containsKey(world)){

                        int max = ((int)Moon.percentChance_to_fractChance(BloodMoon.BLOODMOON_CHANCE));

                     int num = Moon.getRandomNumber(0, max);



                     if((0)==num && getPhase(world)==FULL_MOON){

                         BloodMoon.ACTIVE_BLOODMOON.put(world, true);

                         for(Player player : event.getEntity().getWorld().getPlayers()){
                             player.sendMessage(BloodMoon.BLOODMOON_CHATMESSAGE);
                             player.sendTitle(BloodMoon.BLOODMOON_TITLE, BloodMoon.BLOODMOON_SUBTITLE);
                             event.getEntity().getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.2f, 1.0f);
                         }



                     } else {
                         BloodMoon.ACTIVE_BLOODMOON.put(world, false);

                     }



                    }




                    if(BloodMoon.ACTIVE_BLOODMOON.get(world).equals(true)){

                        BLOODMOON_ACTIVATE((LivingEntity)event.getEntity());




                    }















                }

                else {

                      if(BloodMoon.ACTIVE_BLOODMOON.containsKey(world)){
                          BloodMoon.ACTIVE_BLOODMOON.remove(world);

                          for(LivingEntity livingEntity : world.getLivingEntities()){
                              removeItems(livingEntity);

                          }

                       }





                }








            }


    }



}
