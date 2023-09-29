package vn.aptech.powerofspeed.services.service;

import java.util.List;

import vn.aptech.powerofspeed.model.supplier.Supplier;

public interface SupplierService {
    List<Supplier> findAllSupplier();
    void create(Supplier supplier);
    void update(Supplier supplier);
    Supplier findPk(Long id);
    void delete(Long id);
}
