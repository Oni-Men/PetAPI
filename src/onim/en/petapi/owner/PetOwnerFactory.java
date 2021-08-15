package onim.en.petapi.owner;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

import onim.en.petapi.owner.impl.SimplePetOwner;

public class PetOwnerFactory {

  private static Map<UUID, PetOwner> owners = Maps.newHashMap();

  @Nonnull
  public static PetOwner get(UUID uniqueId) {
    PetOwner owner = owners.get(uniqueId);

    if (owner == null) {
      owner = new SimplePetOwner(uniqueId);
    }

    return owner;
  }

  @Nonnull
  public static PetOwner get(String name) {
    return get(Bukkit.getPlayer(name));
  }
  
  @Nonnull
  public static PetOwner get(Player player) {
    return get(player.getUniqueId());
  }

}
