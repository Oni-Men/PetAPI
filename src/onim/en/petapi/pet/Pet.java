package onim.en.petapi.pet;

import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import onim.en.petapi.PetAPI;

public interface Pet {

  static List<String> CONFIG_WORLDS = null;

  public String getId();

  public EntityType getType();

  public float getSpeed();

  public void initPathfinderGoals(EntityInsentient insentient, Player player);
  
  public default List<String> getAllowedWorlds() {
    return PetAPI.getWorldAllowedByConfig();
  }
}
