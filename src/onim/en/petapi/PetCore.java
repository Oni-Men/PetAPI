package onim.en.petapi;

import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import onim.en.petapi.command.PetCommand;
import onim.en.petapi.pet.PetEntityTracker;
import onim.en.petapi.pet.PetManager;
import onim.en.petapi.pet.impl.EntityTypePet;

public class PetCore extends JavaPlugin {

  public static JavaPlugin instance() {
    return JavaPlugin.getPlugin(PetCore.class);
  }

  @Override
  public void onEnable() {
    this.getServer().getPluginManager().registerEvents(new PetEntityTracker(), this);
    this.getCommand("petapi").setExecutor(new PetCommand());
    
    for (EntityType type : EntityType.values()) {
      PetManager.add(new EntityTypePet(type));
    }
  }

  @Override
  public void onDisable() {
    PetEntityTracker.despawnAll();
  }
}
