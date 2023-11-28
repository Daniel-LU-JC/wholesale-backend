package com.example.orm_backend.controllers;

import com.example.orm_backend.entity.BookWithNumber;
import com.example.orm_backend.service.BookService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class SolrController {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private BookService bookService;

    @RequestMapping("/addBooks")
    public void addBooks() throws IOException, SolrServerException {
        List<BookWithNumber> list = bookService.getBooksAll();
        for (BookWithNumber bookWithNumber: list) {
            solrClient.addBean(bookWithNumber);
        }
        solrClient.commit();
    }

    @RequestMapping("/delBooks")
    public void delBooks() throws IOException, SolrServerException {
        for (int i = 1; i < 10; ++i)
            solrClient.deleteById(String.valueOf(i));
        solrClient.commit();
    }

    @RequestMapping("/lookupInSolr/{lookup}")
    public void getByIdFromSolr(@PathVariable("lookup") String lookup)
            throws IOException, SolrServerException {
        SolrQuery solrQuery  = new SolrQuery();
        solrQuery.set("q", "description:*" + lookup + "*");
        QueryResponse response = solrClient.query(solrQuery);
        SolrDocumentList documentList = response.getResults();
        for (SolrDocument solrDocument : documentList) {
            Map<String, Object> map = solrDocument.getFieldValueMap();
            System.out.println(map);
        }
    }
}
