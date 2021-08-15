package onim.en.petapi.pet.impl;

import org.bukkit.entity.EntityType;

import onim.en.petapi.pet.Pet;

public class CowPet implements Pet {

  @Override
  public String getId() {
    return "pet_cow";
  }

  @Override
  public EntityType getType() {
    return EntityType.COW;
  }

  @Override
  public float getSpeed() {
    return 1.5F;
  }

  @Override
  public long getPrice() {
    return 0;
  }



}
