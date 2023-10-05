package vn.aptech.powerofspeed.service;

import vn.aptech.powerofspeed.model.category.Category;
import vn.aptech.powerofspeed.model.supplier.Supplier;

import java.util.List;

public interface SupplierService {

    List<Supplier> findAllSupplier();
    void create(Supplier supplier);
    void update(Supplier supplier);
    Supplier findPk(Long id);
    void delete(Long id);
}
