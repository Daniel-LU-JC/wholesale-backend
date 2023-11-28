package com.example.orm_backend.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface Neo4jBookRepository extends Neo4jRepository<Neo4jBook, Long> {
    Neo4jBook findByLabel(String label);
}
