package onim.en.petapi.pet.impl;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import lombok.Getter;
import net.minecraft.server.v1_8_R3.EntityCreature;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import onim.en.petapi.ai.PetAIFollowOwner;
import onim.en.petapi.ai.PetAIFoodie;
import onim.en.petapi.ai.PetAIRandomSkip;
import onim.en.petapi.pet.Pet;

public class EntityTypePet implements Pet {

  @Getter
  private String id;

  @Getter
  private EntityType type;

  public EntityTypePet(EntityType type) {
    this.type = type;
    this.id = type.name();
  }

  @Override
  public float getSpeed() {
    return 1F;
  }

  @Override
  public void initPathfinderGoals(EntityInsentient insentient, Player player) {
    insentient.goalSelector = new PathfinderGoalSelector(insentient.world.methodProfiler);
    insentient.targetSelector = new PathfinderGoalSelector(insentient.world.methodProfiler);

    insentient.goalSelector.a(0, new PathfinderGoalFloat(insentient));
    insentient.goalSelector.a(1, new PetAIFollowOwner(insentient, player, 2.0));
    insentient.goalSelector.a(2, new PetAIFoodie(insentient, 9F, 1.1F));

    if (insentient instanceof EntityCreature) {
      insentient.goalSelector.a(3, new PetAIRandomSkip((EntityCreature) insentient, 2.5, 20));
    }
  }

}
