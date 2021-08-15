package onim.en.petapi.owner;

import java.util.Collection;
import java.util.UUID;

import onim.en.petapi.pet.data.PetData;

public interface PetOwner {

  /**
   * Owner の保持しているペットから、IDが petId であるものを返す
   * 
   * @param petId
   * @return
   */
  public PetData getById(UUID petId);

  /**
   * Ownerの保持しているすべてのペットを返す
   * 
   * @return
   */
  public Collection<PetData> getPets();

  // /**
  // * Ownerの保持しているペットからスポーンさせる
  // *
  // * @param pet
  // */
  // public void spawn(PetData pet);
  //
  // /**
  // * Ownerの保持しているペットからデスポーンさせる
  // *
  // * @param pet
  // */
  // public void despawn(PetData pet);
  //
  // /**
  // * Ownerの保持しているペットを全てデスポーンさせる
  // *
  // */
  // public void despawnAll();

  /**
   * OwnerのUUIDを返す
   * 
   * @return
   */
  public UUID getId();
}
