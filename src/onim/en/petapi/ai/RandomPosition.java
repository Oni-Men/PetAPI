package onim.en.petapi.ai;

import java.util.Random;

import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.Vec3D;

public class RandomPosition {

  public static Vec3D aroundOf(EntityLiving entity, int hRange, int vRange) {
    Random var4 = entity.bc();
    int var6 = 0;
    int var7 = 0;
    int var8 = 0;
    // if (entity.ck()) {
    // double var10 = entity.ch().c((double) MathHelper.floor(entity.locX), (double) MathHelper
    // .floor(entity.locY), (double) MathHelper.floor(entity.locZ)) + 4.0D;
    // double var12 = (double) (entity.ci() + (float) var1);
    // var14 = var10 < var12 * var12;
    // } else {
    // var14 = false;
    // }

    for (int var15 = 0; var15 < 10; ++var15) {
      int var16 = var4.nextInt(2 * hRange + 1) - hRange;
      int var17 = var4.nextInt(2 * vRange + 1) - vRange;
      int var18 = var4.nextInt(2 * hRange + 1) - hRange;

      var16 += MathHelper.floor(entity.locX);
      var17 += MathHelper.floor(entity.locY);
      var18 += MathHelper.floor(entity.locZ);
      var6 = var16;
      var7 = var17;
      var8 = var18;
    }

    return new Vec3D((double) var6, (double) var7, (double) var8);
  }

}
