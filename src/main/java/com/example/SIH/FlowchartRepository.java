package com.example.SIH;

import Entities.Flowchart;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlowchartRepository extends CassandraRepository<Flowchart, String> {

    @Query(allowFiltering = true)
    List<Flowchart> findByTitle(final String title);

    @Override
    @Query(allowFiltering = true)
    Optional<Flowchart> findById(final String id);

}