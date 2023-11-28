package com.example.orm_backend.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
@Getter
@Setter
public class Neo4jBook {

    @Id @GeneratedValue
    private Long id;

    private String label;

    private Neo4jBook() {
        // Empty constructor required as of Neo4j API 2.0.5
    }

    public Neo4jBook(String label) {
        this.label = label;
    }

    @Relationship(type = "NEIGHBOR")
    public Set<Neo4jBook> neighbors;

    public void neighborWith(Neo4jBook neo4jBook) {
        if (neighbors == null)
            neighbors = new HashSet<>();
        neighbors.add(neo4jBook);
    }
}
