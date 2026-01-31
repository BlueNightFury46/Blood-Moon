package bluenightfury46.dev.bloodMoon.events;

import bluenightfury46.dev.bloodMoon.ArmourData;
import bluenightfury46.dev.bloodMoon.BloodMoon;
import bluenightfury46.dev.bloodMoon.Moon;
import bluenightfury46.dev.bloodMoon.json.random.MoonRandom;
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
import org.w3c.dom.html.HTMLAreaElement;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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


    public static void removeItems(LivingEntity e){
            e.getEquipment().setHelmet(ItemStack.of(Material.AIR));



            e.getEquipment().setChestplate(ItemStack.of(Material.AIR));
            //TODO Add Enchantment check



            e.getEquipment().setLeggings(ItemStack.of(Material.AIR));



            e.getEquipment().setBoots(ItemStack.of(Material.AIR));



            e.getEquipment().setItemInMainHand(ItemStack.of(Material.AIR));



            e.getEquipment().setItemInOffHand(ItemStack.of(Material.AIR));
    }


    void BLOODMOON_ACTIVATE(LivingEntity e){

        ItemStack HELMET = BloodMoon.data.HELMET.toItemStack();
        ItemStack CHESTPLATE = BloodMoon.data.CHESTPLATE.toItemStack();
        ItemStack LEGGINGS = BloodMoon.data.LEGGINGS.toItemStack();
        ItemStack BOOTS = BloodMoon.data.BOOTS.toItemStack();
        ItemStack MAINHAND = BloodMoon.data.MAINHAND.toItemStack();
        ItemStack OFFHAND = BloodMoon.data.OFFHAND.toItemStack();

        if(BloodMoon.equipment_pool.RANDOM_EQUIPMENT) {

            ArmourData randomEquipment = MoonRandom.generateRandomEquipment(BloodMoon.equipment_pool, false);
            HELMET = randomEquipment.HELMET.toItemStack();
            CHESTPLATE = randomEquipment.CHESTPLATE.toItemStack();
            LEGGINGS = randomEquipment.LEGGINGS.toItemStack();
            BOOTS = randomEquipment.BOOTS.toItemStack();
            MAINHAND = randomEquipment.MAINHAND.toItemStack();
            OFFHAND = randomEquipment.OFFHAND.toItemStack();
        }





            if (isValidHelmet(e)) {
                Objects.requireNonNull(e.getEquipment()).setHelmet(HELMET);
                e.getEquipment().setHelmetDropChance(0.0f);
            }

            if (isValidChestplate(e)) {
                Objects.requireNonNull(e.getEquipment()).setChestplate(CHESTPLATE);
                e.getEquipment().setChestplateDropChance(0.0f);

            }

            if (isValidLeggings(e)) {
                Objects.requireNonNull(e.getEquipment()).setLeggings(LEGGINGS);
                e.getEquipment().setLeggingsDropChance(0.0f);


            }

            if (isValidBoots(e)) {
                Objects.requireNonNull(e.getEquipment()).setBoots(BOOTS);
                e.getEquipment().setBootsDropChance(0.0f);

            }

            if (isValidMainhand(e)) {
                Objects.requireNonNull(e.getEquipment()).setItemInMainHand(MAINHAND);
                e.getEquipment().setItemInMainHandDropChance(0.0f);

            }

            if (isValidOffhand(e)) {
                Objects.requireNonNull(e.getEquipment()).setItemInOffHand(LEGGINGS);
                e.getEquipment().setItemInOffHandDropChance(0.0f);


            }

            try {
                if (!BloodMoon.TRACKED_ENTITIES.contains(e.getEntityId())) {
                    BloodMoon.TRACKED_ENTITIES.add(e.getEntityId());
                    //  BloodMoon.plugin.getLogger().info(""+e.getEntityId());
                }
            } catch (NullPointerException ex) {


        }


    }

    @EventHandler
    public void spawn_event(org.bukkit.event.entity.EntitySpawnEvent event){

        if (BloodMoon.DO_BLOODMOONS){

            if(!event.getEntityType().equals(EntityType.ZOMBIE)){
                return;
            }

            World world = event.getEntity().getWorld();







                if (world.getTime() < 23000 && world.getTime() > 13000) {


                    if(!BloodMoon.ACTIVE_BLOODMOON.containsKey(world)){

                        if(isWorldDisabledInConfig(world.getName())){
                            return;
                        }

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

                    try {
                        if (BloodMoon.ACTIVE_BLOODMOON.containsKey(world)) {
                            BloodMoon.ACTIVE_BLOODMOON.remove(world);

                            for (LivingEntity livingEntity : world.getLivingEntities()) {
                                if (livingEntity.getType().equals(EntityType.ZOMBIE) && BloodMoon.TRACKED_ENTITIES.contains(livingEntity.getEntityId())) {
                                    removeItems(livingEntity);
                                }

                            }

                        }

                    } catch(NullPointerException e){

                    }





                }








            }


    }


    boolean isWorldDisabledInConfig(String worldname){
        try {
            if (BloodMoon.DISABLED_WORLDS == null) {
                return false;
            }


            for(String world : BloodMoon.DISABLED_WORLDS){
                if(world.equalsIgnoreCase(worldname)){
                    return true;
                }

            }

        } catch (NullPointerException ex){
            BloodMoon.DISABLED_WORLDS = new ArrayList<>();
        }
        return false;
    }



}
