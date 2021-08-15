package onim.en.petapi.owner.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

import onim.en.petapi.owner.PetOwner;
import onim.en.petapi.pet.data.PetData;

public class SimplePetOwner implements PetOwner {

  private Map<UUID, PetData> pets;

  private Player player;

  public SimplePetOwner(UUID uniqueId) {
    this.pets = Maps.newHashMap();
    this.player = Bukkit.getPlayer(uniqueId);
  }

  @Override
  public PetData getById(UUID petId) {
    return this.pets.get(petId);
  }

  @Override
  public Collection<PetData> getPets() {
    return new ArrayList<>(this.pets.values());
  }

  public void spawn(PetData pet) {
    Location loc = player.getLocation();
    World world = player.getWorld();
    if (!pet.getType().getAllowedWorlds().contains(world.getName())) {
      return;
    }

    Entity spawned = world.spawnEntity(loc, pet.getType().getType());
    spawned.setCustomName(pet.getName());

  }

  public void despawn(PetData pet) {
    // TODO Auto-generated method stub
  }

  public void despawnAll() {
    this.pets.values().forEach(pet -> this.despawn(pet));
  }

  @Override
  public UUID getId() {
    return this.player.getUniqueId();
  }



}
