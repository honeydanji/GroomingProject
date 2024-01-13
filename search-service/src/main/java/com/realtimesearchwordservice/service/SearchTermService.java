package com.realtimesearchwordservice.service;

import com.realtimesearchwordservice.document.SearchTermDocument;
import com.realtimesearchwordservice.repository.SearchTermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchTermService {

    private final SearchTermRepository searchTermRepository;

    public void saveDocumentService(SearchTermDocument searchTermDocument) {
        searchTermRepository.save(searchTermDocument);
    }

}
