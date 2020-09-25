package com.esjd.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈es配置类〉
 *
 * @author Max
 * @create 2020/9/21
 */
@Configuration
public class ElasticSearchClientConfig {

	@Bean
	public RestHighLevelClient restHighLevelClient(){
	       RestHighLevelClient client = new RestHighLevelClient(
	                RestClient.builder(
	                        new HttpHost("127.0.0.1",9200)));
	        return client;
	    }
}