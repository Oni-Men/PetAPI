package onim.en.petapi.pet.impl;

import org.bukkit.entity.EntityType;

import onim.en.petapi.pet.Pet;

public class SheepPet implements Pet {

  @Override
  public String getId() {
    return "pet_sheep";
  }

  @Override
  public EntityType getType() {
    return EntityType.SHEEP;
  }

  @Override
  public float getSpeed() {
    return 2F;
  }

  @Override
  public long getPrice() {
    return 0;
  }

}
