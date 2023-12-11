package com.realtimesearchwordservice.repository;

import com.realtimesearchwordservice.document.SearchTermDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchTermRepository extends ElasticsearchRepository<SearchTermDocument, Long> {

}
