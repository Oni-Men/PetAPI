package onim.en.petapi.ai;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Navigation;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import net.minecraft.server.v1_8_R3.World;

// TOOD 食べ物を持っているプレイヤー飛びつく
public class PetAIFoodie extends PathfinderGoal {

  private EntityInsentient entity;
  private EntityHuman player;
  private World world;
  private float range;
  private float speed;
  private int count;

  private boolean m;

  public PetAIFoodie(EntityInsentient entity, float range, float speed) {
    this.entity = entity;
    this.world = entity.world;
    this.range = range;
  }

  @Override
  public boolean a() {
    if (this.count > 0) {
      this.count--;
      return false;
    }
    
    this.player = this.world.findNearbyPlayer(this.entity, (double) this.range);
    if (this.player == null) {
      return false;
    }
    
    if (!this.player.isAlive()) {
      return false;
    }
    
    if (this.entity.h(this.player) > this.range * this.range) {
      return false;
    }
    
    return this.isPlayerHoldingFood(player);
  }

  @Override
  public boolean b() {
    return this.a();
  }

  @Override
  public void c() {
    this.m = ((Navigation) this.entity.getNavigation()).e();
    ((Navigation) this.entity.getNavigation()).a(false);
  }

  @Override
  public void e() {
    this.entity.getControllerLook().a(player, 30F, this.entity.bQ());
    if (this.entity.h(this.player) < 6.25D) {
      this.entity.getNavigation().n();
    } else {
      this.entity.getNavigation().a(player, this.speed);
    }

  }

  @Override
  public void d() {
    this.count = 100;
    this.player = null;
    ((Navigation) this.entity.getNavigation()).a(this.m);
    this.entity.getNavigation().n();
  }

  private boolean isPlayerHoldingFood(EntityHuman player) {
    ItemStack stack = player.inventory.getItemInHand();
    Material type = CraftItemStack.asBukkitCopy(stack).getType();
    return !type.isBlock() && type.isEdible();
  }
}
