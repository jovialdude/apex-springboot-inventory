package com.apex.eqp.inventory.helpers;

import com.apex.eqp.inventory.entities.Product;
import com.apex.eqp.inventory.entities.RecalledProduct;

import java.util.*;
import java.util.stream.Collectors;

public class ProductFilter {

    private final Set<String> recalledProducts;

    public ProductFilter(Set<String> recalledProducts) {
        this.recalledProducts = recalledProducts;
    }

    public List<Product> removeRecalledFrom(Collection<Product> allProduct) {
        return allProduct.stream().filter(a->filterByName(a)).collect(Collectors.toList());
    }

//    public List<Product> removeUnexpiredRecalledFrom(Collection<Product> allProduct) {
//
//    }

    //this filter will select ALL recalledProduct
    //another filter will be needed to collect non expired recall product
    private boolean filterByName(Product product) {
        if (recalledProducts.contains(product.getName())) {
            return false;
        }
        return true;
    }

//    private boolean filterExpiredProducts(Product product) {
//        if (recalledProducts.contains(product.getName())) {
//            for (String rp: recalledProducts) {
//                if ()
//            }
//        }
//
//        return true;
//    }

}
