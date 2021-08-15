package onim.en.petapi.pet.data;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import onim.en.petapi.owner.PetOwner;

public class PetDataManager {

  private static Map<PetData, UUID> owners = Maps.newHashMap();
  private static Multimap<UUID, PetData> datas = HashMultimap.create();

  public static void add(PetData data) {
    owners.put(data, data.getOwnerId());
    datas.put(data.getOwnerId(), data);
  }

  public static boolean contains(PetData data) {
    return owners.containsKey(data);
  }

  public static void remove(PetData data) {
    UUID ownerId = owners.remove(data);
    if (ownerId != null) {
      datas.remove(ownerId, data);
    }
  }
  
  public static Collection<PetData> get(PetOwner owner) {
     return datas.get(owner.getId());
  }
  
  public static UUID get(PetData data) {
    return owners.get(data);
  }
  
}
