package com.example.SIH;

import Entities.Forms;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormsRepository extends CassandraRepository<Forms, String> {

    @Query(allowFiltering = true)
    List<Forms> findByTitle(final String title);

    @Override
    @Query(allowFiltering = true)
    Optional<Forms> findById(final String id);

}