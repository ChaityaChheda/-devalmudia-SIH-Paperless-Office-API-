package com.example.SIH;

import Entities.test;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface testRepository extends CassandraRepository<test, String> {

    @Override
    Optional<test> findById(String s);
}