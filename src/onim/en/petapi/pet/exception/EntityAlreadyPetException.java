package onim.en.petapi.pet.exception;

import org.bukkit.entity.Entity;

import lombok.Getter;
import onim.en.petapi.pet.data.PetData;

public class EntityAlreadyPetException extends Exception {

  @Getter
  private Entity entity;

  @Getter
  private PetData petData;

  public EntityAlreadyPetException(Entity entity, PetData petData) {
    super(entity.getCustomName() + "はすでにペットです。");
    this.entity = entity;
    this.petData = petData;
  }

}
