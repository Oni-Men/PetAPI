package onim.en.petapi.pet;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Nullable;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftChicken;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.google.common.collect.Maps;

import net.minecraft.server.v1_8_R3.EntityChicken;
import onim.en.petapi.owner.PetOwner;
import onim.en.petapi.owner.PetOwnerFactory;
import onim.en.petapi.pet.data.PetData;
import onim.en.petapi.pet.data.PetDataManager;
import onim.en.petapi.pet.exception.EntityAlreadyPetException;

public class PetEntityTracker implements Listener {

  private static Map<Entity, PetData> pets = Maps.newHashMap();
  private static Map<PetData, Entity> entities = Maps.newHashMap();

  /**
   * スポーンしている Entity と Pet を関連付ける
   * 
   * @param entity
   * @param data
   * @throws EntityAlreadyPetException
   */
  public static void link(Entity entity, PetData data) throws EntityAlreadyPetException {
    if (pets.containsKey(entity)) {
      throw new EntityAlreadyPetException(entity, data);
    }

    pets.put(entity, data);
    entities.put(data, entity);

    initialize(entity);
  }

  /**
   * 指定した Entity と関連付けられた PetData の関連を解消する
   * 
   * @param entity
   */
  public static void unlink(Entity entity) {
    PetData data = pets.remove(entity);
    if (data != null) {
      entities.remove(data);
    }
    
    finalize(entity);
  }

  /**
   * 指定した PetData と関連付けられた Entity の関連を解消する
   * 
   * @param data
   */
  public static void unlink(PetData data) {
    Entity entity = entities.remove(data);
    if (entity != null) {
      pets.remove(entity);
    }
    
    finalize(entity);
  }

  /**
   * 関連付けた Entity の動作を変更する
   * 
   * @param entity
   */
  public static void initialize(Entity entity) {
    
    //ニワトリが卵を産まないようにする
    if (entity instanceof CraftChicken) {
      ((CraftChicken) entity).getHandle().bs = Integer.MAX_VALUE;
    }
  }
  
  /**
   * 関連付けを解除した後に、Entity をバニラの動作に戻す
   * 
   * @param entity
   */
  public static void finalize(Entity entity) {
    if (entity instanceof CraftChicken) {
      EntityChicken handle = ((CraftChicken) entity).getHandle();
      handle.bs = handle.bc().nextInt(6000) + 6000;
    }
  }
  
  /**
   * Entity に関連づけられた PetData を取得する
   * 
   * @param uniqueId
   */
  @Nullable
  public static PetData get(Entity entity) {
    return pets.get(entity);
  }

  /**
   * PetData に関連付けられた Entity を取得する
   * 
   * @param petData
   * @return
   */
  @Nullable
  public static Entity get(PetData petData) {
    return entities.get(petData);
  }

  public static void despawn(PetData data) {
    Entity entity = get(data);

    if (entity == null) {
      return;
    }

    entity.remove();
    unlink(entity);
  }

  /**
   * Entity をデスポーンし、Entity と PetData との関連も解消する
   * 
   * @param entity
   */
  public static void despawn(Entity entity) {
    PetData data = get(entity);

    if (data == null) {
      return;
    }

    entity.remove();
    unlink(data);
  }

  /**
   * すべての関連付けられた Entity をデスポーンし、PetData との関連も解消する
   */
  public static void despawnAll() {
    for (Entity entity : pets.keySet()) {
      entity.remove();
    }

    unlinkAll();
  }

  /**
   * すべての PetData と Entity の関連付けを解消する
   */
  public static void unlinkAll() {
    pets.clear();
    entities.clear();
  }

  /**
   * 指定した Entity が既に関連付けられているとき、true
   * 
   * @param entity
   * @return
   */
  public static boolean isLinked(Entity entity) {
    return pets.containsKey(entity);
  }

  /**
   * 指定した PetData が既に関連付けられているとき、true
   * 
   * @param data
   * @return
   */
  public static boolean isLinked(PetData data) {
    return entities.containsKey(data);
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    PetOwner owner = PetOwnerFactory.get(event.getPlayer());
    Collection<PetData> datas = PetDataManager.get(owner);
    for (PetData data : datas) {
      PetEntityTracker.despawn(data);
    }
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onEntityDeath(EntityDeathEvent event) {
    PetData data = PetEntityTracker.get(event.getEntity());

    if (data == null) {
      return;
    }
    event.getDrops().clear();
    unlink(event.getEntity());
  }

}
