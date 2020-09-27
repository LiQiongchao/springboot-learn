package com.tamecode.lesson9.controller;

import com.tamecode.lesson9.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static java.util.Collections.singletonMap;
import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

/**
 * @author Liqc
 * @date 2020/9/14 14:26
 */
@Slf4j
@RestController
public class ElasticSearchController {

    @Autowired
    @Qualifier(value = "myRestHighLevelClient")
    RestHighLevelClient highLevelClient;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    @Qualifier(value = "personRepository1")
    private PagingAndSortingRepository personRepository;

    @Autowired
    @Qualifier(value = "personRepository2")
    private PagingAndSortingRepository PersonRepository2;


    /**
     * 使用 highLevelClient 创建索引
     *
     * @param index
     * @param type
     * @return
     * @throws IOException
     */
    @PostMapping("/indexes/{index}/{type}")
    public DocWriteResponse.Result indexes(@PathVariable String index, @PathVariable String type) throws IOException {
        RestClient lowLevelClient = highLevelClient.getLowLevelClient();

        IndexRequest request = new IndexRequest(index, type)
                .source(singletonMap("feature", "high-level-rest-client"))
                .setRefreshPolicy(IMMEDIATE);
        RequestOptions options = RequestOptions.DEFAULT;
        IndexResponse response = highLevelClient.index(request, options);
        log.debug("response: {}", response);
        return response.getResult();
    }

    @PostMapping("/person")
    public String save(@RequestBody Person person) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId().toString())
                .withObject(person)
                .build();
        IndexCoordinates coordinates = IndexCoordinates.of("person");
        String documentId = elasticsearchOperations.index(indexQuery, coordinates);
        return documentId;
    }

    @GetMapping("/person/{id}")
    public Person findById(@PathVariable("id") Long id) {
        Person person = elasticsearchOperations
                .queryForObject(GetQuery.getById(id.toString()), Person.class);
        return person;
    }

    @PostMapping("/person1")
    public Person savePerson(@RequestBody Person person) {
        personRepository.save(person);
        return person;
    }

    @GetMapping("/person1/{id}")
    public Person findById2(@PathVariable("id") Long id) {
        return (Person) PersonRepository2.findById(id).get();
    }

}
