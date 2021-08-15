package onim.en.petapi.ai;

import net.minecraft.server.v1_8_R3.EntityCreature;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import net.minecraft.server.v1_8_R3.RandomPositionGenerator;
import net.minecraft.server.v1_8_R3.Vec3D;

public class PetAIRandomSkip extends PathfinderGoal {

  private EntityCreature entity;
  private double x;
  private double y;
  private double z;
  private double speed;
  private int i;
  private boolean g;

  public PetAIRandomSkip(EntityCreature entity, double speed) {
      this(entity, speed, 120);
  }

  public PetAIRandomSkip(EntityCreature entity, double speed, int i) {
      this.entity = entity;
      this.speed = speed;
      this.i = i;
      this.a(1);
  }

  public boolean a() {
      if (!this.g) {
          if (this.entity.bh() >= 100) {
              return false;
          }

          if (this.entity.bc().nextInt(this.i) != 0) {
              return false;
          }
      }

      Vec3D pos = RandomPositionGenerator.a(this.entity, 10, 7);
      if (pos == null) {
          return false;
      } else {
          this.x = pos.a;
          this.y = pos.b;
          this.z = pos.c;
          this.g = false;
          return true;
      }
  }

  public boolean b() {
      return !this.entity.getNavigation().m();
  }
  
  @Override
  public void e() {
    this.entity.getControllerJump().a();
  }

  public void c() {
      this.entity.getNavigation().a(this.x, this.y, this.z, this.speed);
  }

  public void setTimeBetweenMovement(int var1) {
      this.i = var1;
  }
}
