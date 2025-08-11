package com.icoffiel.jspecify.platform.domain.manufacturer.port;

import com.icoffiel.jspecify.platform.domain.manufacturer.model.Manufacturer;
import com.icoffiel.jspecify.platform.domain.manufacturer.model.ManufacturerId;

import java.util.List;

public interface ManufacturerRepository {
    void deleteById(ManufacturerId manufacturerId);
    List<Manufacturer> findAll();
    Manufacturer findById(ManufacturerId id);
    Manufacturer save(Manufacturer manufacturer);
}
