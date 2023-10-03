package vn.aptech.powerofspeed.repository.inventory;

import org.springframework.data.repository.CrudRepository;
import vn.aptech.powerofspeed.model.inventory.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory,Integer> {
}
