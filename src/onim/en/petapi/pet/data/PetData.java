package onim.en.petapi.pet.data;

import java.util.UUID;

import onim.en.petapi.owner.PetOwner;
import onim.en.petapi.pet.Pet;

public interface PetData {

  public UUID getOwnerId();
  
  public PetOwner getOwner();

  public String getName();

  public void setName(String name);

  public Pet getType();

}
