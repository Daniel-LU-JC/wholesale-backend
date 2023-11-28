package com.example.orm_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(solrCoreName = "solr_demo")
public class BookWithNumber {

    @Id
    @Field("id")
    @Indexed(readonly = true)
    public int id;

    @Field("isbn")
    @Indexed(readonly = true)
    public String isbn;

    @Field("book_name")
    @Indexed(readonly = true)
    public String book_name;

    @Field("type")
    public String type;

    @Field("author")
    @Indexed(readonly = true)
    public String author;

    @Field("price")
    @Indexed(searchable = false)
    public int price;

    @Field("description")
    public String description;

    @Field("inventory")
    @Indexed(stored = false)
    public int inventory;

    @Field("image")
    @Indexed(stored = false)
    public String image;

    @Field("number")
    @Indexed(stored = false)
    public int number;
}
