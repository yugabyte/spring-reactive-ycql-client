package com.yugabyte.ycql.sample.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import com.yugabyte.ycql.sample.domain.Product;

public interface ProductReactiveRepository extends ReactiveCassandraRepository<Product, String>{

}
