package vn.aptech.powerofspeed.service;

import vn.aptech.powerofspeed.model.inventory.Inventory;
import vn.aptech.powerofspeed.model.products.Product;

import java.util.List;

public interface InventoryService {

    List<Inventory> findAllInventory();
    void create(Inventory inventory);
    void update(Inventory inventory);
    Inventory findPk(int id);
    void delete(int id);
}
