package onim.en.petapi.ai;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathfinderGoal;

public class PetAIFollowOwner extends PathfinderGoal {

  private EntityInsentient entity;
  private Entity owner;

  private double speed;
  private int count = 0;

  public PetAIFollowOwner(EntityInsentient entity, Entity owner, double speed) {
    this.entity = entity;
    this.owner = owner;
    this.speed = speed;
  }

  public PetAIFollowOwner(EntityInsentient entity, Player player, double speed) {
    this(entity, ((CraftPlayer) player).getHandle(), speed);
  }

  public boolean a() {
    return this.b();
  }

  @Override
  public boolean b() {
    if (!this.entity.isAlive() || !this.owner.isAlive()) {
      return false;
    }

    double distance = this.entity.h(this.owner);
    return distance > 9D;
  }


  public void c() {
    this.count = 0;
  }

  @Override
  public void d() {
    return;
  }

  @Override
  public void e() {
    if (--this.count <= 0) {
      this.count = 10;
      this.entity.getNavigation().a(owner, this.speed);
    }
  }

}
