package com.realtimesearchwordservice.controller;

import com.realtimesearchwordservice.document.SearchTermDocument;
import com.realtimesearchwordservice.service.SearchTermService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchTermController {

    private final SearchTermService searchTermService;

    @PostMapping("/document")
    public ResponseEntity<String> saveDocumentController(@RequestBody SearchTermDocument searchTermDocument) {
        try {
            searchTermService.saveDocumentService(searchTermDocument);
            return ResponseEntity.ok("검색어가 성공적으로 저장 되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("검색어 저장중 에러발생.");
        }
    }
}
