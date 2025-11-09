package com.demo.daos;

import java.util.List;
import java.util.Optional;

import com.demo.models.Product;

public interface ProductDaos {
List<Product> findAll();
Optional<Product> getById(Integer id);
int insert(Product p);
int update(Product p);
int delete(Integer id);
}
