package com.example.SIH;

import Entities.Pending_Requests;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Pending_RequestsRepository extends CassandraRepository<Pending_Requests, String> {

    @Override
    Optional<Pending_Requests> findById(String s);
}