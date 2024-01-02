package com.job_manager.mai.controller.inteface;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ICrudController<Request, TypeID> {
    ResponseEntity<?> add(Request requestBody);

    ResponseEntity<?> update(Request requestBody, TypeID Id);

    ResponseEntity<?> delete(TypeID Id);

    ResponseEntity<?> deleteAll(Request request);

    ResponseEntity<?> getById(TypeID id);

    ResponseEntity<?> getAll(Pageable pageable);

    ResponseEntity<?> search(String query);

}
