package com.job_manager.mai.util;

import com.job_manager.mai.contrains.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiResponseHelper {

    @Data
    @AllArgsConstructor
    static class Response {
        private Object data;
        private List<String> message;
        private boolean isValid;
    }

    public static ResponseEntity<?> success() {
        List<String> msgs = new ArrayList<>();
        msgs.add(Messages.DEFAULT_SUCCESS_MESSAGE);
        return new ResponseEntity<>(new Response(new HashMap<>(), msgs, true), HttpStatus.OK);
    }

    public static ResponseEntity<?> success(Object data) {
        List<String> msgs = new ArrayList<>();
        msgs.add(Messages.DEFAULT_SUCCESS_MESSAGE);
        return new ResponseEntity<>(new Response(data, msgs, true), HttpStatus.OK);
    }

    public static ResponseEntity<?> resp(Object data, HttpStatus httpStatus, String msg) {
        List<String> msgs = new ArrayList<>();
        msgs.add(msg);
        return new ResponseEntity<>(new Response(data, msgs, true), httpStatus);
    }

    public static ResponseEntity<?> notFound(String msg) {
        List<String> msgs = new ArrayList<>();
        msgs.add(msg);
        return new ResponseEntity<>(new Response(new HashMap<>(), msgs, true), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<?> fallback(Exception e) {
        List<String> msgs = new ArrayList<>();
        msgs.add(e.getMessage());
        return new ResponseEntity<>(new Response(new HashMap<>(), msgs, true), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> invalid(List<String> messages) {
        return new ResponseEntity<>(new Response(new HashMap<>(), messages, false), HttpStatus.BAD_REQUEST);
    }
}
