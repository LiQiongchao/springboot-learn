package com.tamecode.lesson9;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.UnknownHostException;

/**
 * 在父类中默认提供了 elasticsearchTemplate 的支持
 *
 * @author Liqc
 * @date 2020/9/10 13:01
 */
@Configuration
public class ElasticsearchClientConfiguration extends AbstractElasticsearchConfiguration {

    /**
     * customize high level client
     * @return
     */
    @Bean("myRestHighLevelClient")
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

}
