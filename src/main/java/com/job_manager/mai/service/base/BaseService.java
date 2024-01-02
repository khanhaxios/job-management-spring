package com.job_manager.mai.service.base;

import org.modelmapper.ModelMapper;

public class BaseService {
    protected ModelMapper getMapper() {
        return new ModelMapper();
    }
}
