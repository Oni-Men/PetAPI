package onim.en.petapi.pet;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import onim.en.petapi.pet.data.PetData;
import onim.en.petapi.pet.exception.EntityAlreadyPetException;

public class PetEntitySpawner {

  public static void spawn(PetData data) {
    if (PetEntityTracker.isLinked(data)) {
      return;
    }

    Player player = Bukkit.getPlayer(data.getOwnerId());

    if (player == null) {
      return;
    }

    World world = player.getWorld();
    Location loc = player.getLocation();

    if (!data.getType().getAllowedWorlds().contains(world.getName())) {
      return;
    }

    Entity entity = world.spawnEntity(loc, data.getType().getType());

    try {
      PetEntityTracker.link(entity, data);
    } catch (EntityAlreadyPetException e) {
      entity.remove();
      return;
    }

    entity.setCustomNameVisible(true);
    entity.setCustomName(ChatColor.BOLD + data.getName());

    net.minecraft.server.v1_8_R3.Entity handle = ((CraftEntity) entity).getHandle();
    setInvulnerable(handle, true);

    if (handle instanceof EntityInsentient) {
      EntityInsentient insentient = (EntityInsentient) handle;
      data.getType().initPathfinderGoals(insentient, player);
    }

  }

  public static void setInvulnerable(net.minecraft.server.v1_8_R3.Entity entity, boolean isInvulnerable) {
    try {
      Field field = net.minecraft.server.v1_8_R3.Entity.class.getDeclaredField("invulnerable");
      field.setAccessible(true);
      field.setBoolean(entity, isInvulnerable);
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

}
