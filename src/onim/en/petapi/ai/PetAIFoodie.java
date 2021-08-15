package onim.en.petapi.ai;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.ItemStack;
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
  
  public PetAIFoodie(EntityInsentient entity, float range, float speed) {
    this.entity = entity;
    this.world = entity.world;
    this.range = range;
  }
  
  @Override
  public boolean a() {
    this.player = this.world.findNearbyPlayer(this.entity, (double) this.range);
    return this.player == null ? false : this.isPlayerHoldingFood(player);
  }
  
  @Override
  public boolean b() {
    if (!this.player.isAlive()) {
      return false;
    } else if (this.entity.h(this.player) > 6.25D) {
      return false;
    }
    
    return this.count > 0 && this.isPlayerHoldingFood(player);
  }
  
  @Override
  public void c() {
    this.count = 60 + this.entity.bc().nextInt(40);
  }
  
  @Override
  public void d() {
    this.player = null;
    this.entity.getNavigation().n();
  }
  
  @Override
  public void e() {
    double x = this.player.locX;
    double y = this.player.locY + this.player.getHeadHeight();
    double z = this.player.locZ;
    
    this.entity.getControllerLook().a(x, y, z, 10F, this.entity.bQ());
    if (this.entity.h(this.player) < 6.25D) {
      this.entity.getNavigation().n();
    } else {
      this.entity.getNavigation().a(player, this.speed);
    }
    
    this.count--;
  }
  
  private boolean isPlayerHoldingFood(EntityHuman player) {
    ItemStack stack = player.inventory.getItemInHand();
    Material type = CraftItemStack.asBukkitCopy(stack).getType();
    return !type.isBlock() && type.isEdible();
  }
}
