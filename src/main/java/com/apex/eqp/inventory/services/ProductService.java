package com.apex.eqp.inventory.services;

import com.apex.eqp.inventory.entities.Product;
import com.apex.eqp.inventory.entities.RecalledProduct;
import com.apex.eqp.inventory.helpers.ProductFilter;
import com.apex.eqp.inventory.repositories.InventoryRepository;
import com.apex.eqp.inventory.repositories.RecalledProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final InventoryRepository inventoryRepository;
    private final RecalledProductRepository recalledProductRepository;

    @Transactional
    public Product save(Product product) {
        return inventoryRepository.save(product);
    }

    public Collection<Product> getAllProduct() {
        Set<RecalledProduct> list = recalledProductRepository.findAll().stream().collect(Collectors.toSet());
        Set<String> prodNames = new HashSet<>();

        for (RecalledProduct rp: list) {
            prodNames.add(rp.getName());
        }

        ProductFilter filter = new ProductFilter(prodNames);

        return filter.removeRecalledFrom(inventoryRepository.findAll());
    }

    public Optional<Product> findById(Integer id) {
        return inventoryRepository.findById(id);
    }
}
