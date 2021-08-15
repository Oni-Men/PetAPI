package onim.en.petapi.pet;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.EntityType;

public interface Pet {

  static final List<String> OVER_WORLD = Arrays.asList("thelow");

  public String getId();

  public EntityType getType();

  public float getSpeed();

  public long getPrice();

  public default List<String> getAllowedWorlds() {
    return OVER_WORLD;
  }
}
