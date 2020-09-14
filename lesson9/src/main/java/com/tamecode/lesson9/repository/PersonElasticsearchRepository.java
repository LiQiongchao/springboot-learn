package com.tamecode.lesson9.repository;

import com.tamecode.lesson9.bean.Person;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.MappingElasticsearchEntityInformation;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.TypeInformation;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

/**
 * 自定义仓储类
 *
 * @author Liqc
 * @date 2020/9/14 15:38
 */
@Repository(value = "personRepository1")
public class PersonElasticsearchRepository extends AbstractElasticsearchRepository<Person, Long> {

    public PersonElasticsearchRepository(ElasticsearchOperations elasticsearchOperations) {
        super(createAbstractElasticsearchRepository(), elasticsearchOperations);
//        super.entityClass = Person.class;
    }

    private static ElasticsearchEntityInformation<Person, Long> createAbstractElasticsearchRepository() {
        TypeInformation<Person> typeInformation = ClassTypeInformation.from(Person.class);

        ElasticsearchPersistentEntity<Person> entity = new SimpleElasticsearchPersistentEntity(typeInformation);

        ElasticsearchEntityInformation<Person, Long> information = new MappingElasticsearchEntityInformation(entity);

        return information;
    }

    @Override
    protected String stringIdRepresentation(Long aLong) {
        return aLong + "";
    }
}
