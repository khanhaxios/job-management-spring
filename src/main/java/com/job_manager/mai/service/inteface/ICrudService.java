package com.job_manager.mai.service.inteface;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface ICrudService<Request, TypeId> {
    ResponseEntity<?> store(Request request) throws Exception;

    ResponseEntity<?> edit(Request request, TypeId id) throws Exception;

    ResponseEntity<?> destroy(TypeId id) throws Exception;

    ResponseEntity<?> destroyAll(Request request) throws Exception;

    ResponseEntity<?> getAll(Pageable pageable);

    ResponseEntity<?> getById(TypeId id);
}
