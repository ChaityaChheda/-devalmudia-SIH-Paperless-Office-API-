package com.example.SIH;

import Entities.Menu2;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CassandraRepository<Menu2, Integer> {

}