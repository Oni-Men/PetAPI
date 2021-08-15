package onim.en.petapi.pet;

import java.util.Map;

import com.google.common.collect.Maps;

public class PetManager {

  private static Map<String, Pet> pets = Maps.newHashMap();

  public static void add(Pet pet) {
    pets.put(pet.getId(), pet);
  }
  
  public static Pet get(String id) {
    return pets.get(id);
  }



}
