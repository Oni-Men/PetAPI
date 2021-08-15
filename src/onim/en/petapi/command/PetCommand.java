package onim.en.petapi.command;

import java.util.Collection;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import onim.en.petapi.owner.PetOwner;
import onim.en.petapi.owner.PetOwnerFactory;
import onim.en.petapi.pet.Pet;
import onim.en.petapi.pet.PetEntitySpawner;
import onim.en.petapi.pet.PetManager;
import onim.en.petapi.pet.data.PetData;
import onim.en.petapi.pet.data.PetDataManager;
import onim.en.petapi.pet.data.SimplePetData;

public class PetCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;
    if (args.length < 2) {
      return false;
    }

    String petType = args[0];
    String petName = args[1];

    Pet pet = PetManager.get(petType);

    if (pet == null) {
      player.sendMessage(ChatColor.RED + petType + "は存在しない種類のペットです");
      return true;
    }

    PetOwner owner = PetOwnerFactory.get(player);
    Collection<PetData> datas = PetDataManager.get(owner);
    PetData data = null;

    for (PetData d : datas) {
      if (d.getName().equals(petName)) {
        data = d;
        break;
      }
    }

    if (data == null) {
      data = new SimplePetData(player.getUniqueId(), petName, pet);
      PetDataManager.add(data);
    }

    PetEntitySpawner.spawn(data);
    return true;
  }

}
