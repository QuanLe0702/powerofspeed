package vn.aptech.powerofspeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.powerofspeed.model.inventory.Inventory;
import vn.aptech.powerofspeed.model.supplier.Supplier;
import vn.aptech.powerofspeed.repository.inventory.InventoryRepository;
import vn.aptech.powerofspeed.service.InventoryService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> findAllInventory() {
        List<Inventory> result = new ArrayList<>();
        Iterable<Inventory> listInventory = inventoryRepository.findAll();
        for (Inventory i : listInventory) {

            result.add(i);
        }
        return result;
    }

    @Override
    @Transactional
    public void create(Inventory inventory) {
        inventoryRepository.save(inventory);

    }

    @Override
    public void update(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Override
    public Inventory findPk(int id) {
        try {
            Inventory inventory = inventoryRepository.findById(id).get();

            return inventory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void delete(int id) {
            Inventory inventory = inventoryRepository.findById(id).get();
            inventoryRepository.delete(inventory);
    }


}
