package com.job_manager.mai.service.inteface;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ISearchService {
    ResponseEntity<?> searchByName(Pageable pageable, String name) throws Exception;
}
