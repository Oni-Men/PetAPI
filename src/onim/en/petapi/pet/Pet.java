package onim.en.petapi.pet;

import java.util.List;

import org.bukkit.entity.EntityType;

import onim.en.petapi.PetAPI;

public interface Pet {

  static List<String> CONFIG_WORLDS = null;

  public String getId();

  public EntityType getType();

  public float getSpeed();

  public long getPrice();

  public default List<String> getAllowedWorlds() {
    return PetAPI.getWorldAllowedByConfig();
  }
}
