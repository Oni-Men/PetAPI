package onim.en.petapi.ai;

import net.minecraft.server.v1_8_R3.EntityCreature;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import net.minecraft.server.v1_8_R3.Vec3D;

public class PetAIBunnyHop extends PathfinderGoal {

  private EntityCreature entity;
  private EntityLiving target;

  private double x;
  private double y;
  private double z;

  private boolean g;
  private double speed;

  public PetAIBunnyHop(EntityCreature entity, double speed) {
    this.entity = entity;
    this.speed = speed;
  }

  public boolean a() {
    if (!this.g) {
      this.target = this.entity.world.findNearbyPlayer(entity, 10);
      
      if (target == null) {
        return false;
      }
    } 

    Vec3D pos = RandomPosition.aroundOf(this.target, 2, 5);
    if (pos == null) {
      return false;
    } else {
      this.x = pos.a;
      this.y = pos.b;
      this.z = pos.c;
      this.g = false;
      return  true;
    }
  }

  @Override
  public boolean b() {
    return !this.entity.getNavigation().m();
  }

  public void c() {
    this.entity.getNavigation().a(x, y, z, this.speed);
  }

  @Override
  public void e() {
    this.entity.getControllerJump().a();
  }

  @Override
  public void d() {
    this.entity.getNavigation().n();
    this.entity.getControllerJump().b();
    return;
  }

}
