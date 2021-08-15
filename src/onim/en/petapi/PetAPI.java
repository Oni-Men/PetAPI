package onim.en.petapi;

import java.util.List;

import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import onim.en.petapi.command.PetCommand;
import onim.en.petapi.pet.PetEntityTracker;
import onim.en.petapi.pet.PetManager;
import onim.en.petapi.pet.impl.EntityTypePet;

public class PetAPI extends JavaPlugin {

  private static List<String> CONFIG_WORLDS = null;
  public static List<String> getWorldAllowedByConfig() {
    if (CONFIG_WORLDS == null) {
      CONFIG_WORLDS = PetAPI.config().getStringList("allowed-worlds");
    }
    return CONFIG_WORLDS;
  }
  
  
  public static JavaPlugin instance() {
    return JavaPlugin.getPlugin(PetAPI.class);
  }
  
  public static Configuration config() {
    return instance() == null ? null : instance().getConfig();
  }

  @Override
  public void onEnable() {
    this.getConfig().addDefault("allowed-worlds", new String[] { "world" });
    this.saveDefaultConfig();
    
    this.getServer().getPluginManager().registerEvents(new PetEntityTracker(), this);
    this.getCommand("petapi").setExecutor(new PetCommand());
    
    for (EntityType type : EntityType.values()) {
      PetManager.add(new EntityTypePet(type));
    }
  }

  @Override
  public void onDisable() {
    this.saveConfig();
    PetEntityTracker.despawnAll();
  }
}
