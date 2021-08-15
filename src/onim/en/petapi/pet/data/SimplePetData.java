package onim.en.petapi.pet.data;

import java.util.UUID;

import lombok.Data;
import onim.en.petapi.owner.PetOwner;
import onim.en.petapi.owner.PetOwnerFactory;
import onim.en.petapi.pet.Pet;

@Data
public class SimplePetData implements PetData {

  private UUID id;

  private UUID ownerId;

  private String name;

  private Pet type;

  public SimplePetData(UUID ownerId, String name, Pet type) {
    this.ownerId = ownerId;
    this.name = name;
    this.type = type;
  }

  @Override
  public PetOwner getOwner() {
    return PetOwnerFactory.get(this.ownerId);
  }

}
