package com.harjoitus.tyo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{
    List<Product> findByTitle(String title);

}
