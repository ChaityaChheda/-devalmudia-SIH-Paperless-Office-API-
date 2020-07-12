package com.example.SIH;

import Entities.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CassandraRepository<User, String> {

    @AllowFiltering
    List<User> findByUsername(String username);

    @Override
    Optional<User> findById(String s);
}