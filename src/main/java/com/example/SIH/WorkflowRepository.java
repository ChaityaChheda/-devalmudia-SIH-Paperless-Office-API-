package com.example.SIH;

import Entities.Forms;
import Entities.Workflow;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkflowRepository extends CassandraRepository<Workflow, String> {

    List<Forms> findByTitle(String title);

    @Override
    Optional<Workflow> findById(String s);
}