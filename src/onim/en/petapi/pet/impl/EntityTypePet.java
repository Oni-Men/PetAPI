package onim.en.petapi.pet.impl;

import org.bukkit.entity.EntityType;

import lombok.Getter;
import onim.en.petapi.pet.Pet;

public class EntityTypePet implements Pet{

  @Getter
  private String id;
  
  @Getter
  private EntityType type;
  
  public EntityTypePet(EntityType type) {
    this.type = type;
    this.id = type.name();
  }
  
  @Override
  public float getSpeed() {
    return 1F;
  }

  @Override
  public long getPrice() {
    return 0;
  }

  
  
}
