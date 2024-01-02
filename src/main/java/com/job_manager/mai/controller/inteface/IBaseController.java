package com.job_manager.mai.controller.inteface;

public interface IBaseController<Request, TypeId> extends ICrudController<Request, TypeId>, ISearchController, ISortController {
}
