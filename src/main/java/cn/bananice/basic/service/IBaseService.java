package cn.bananice.basic.service;

import cn.bananice.basic.query.BaseQuery;
import cn.bananice.basic.util.PageList;

import java.util.List;

public interface IBaseService<T> {
    void add(T t);

    void remove(Long id);

    void update(T t);

    T queryById(Long id);

    List<T> queryAll();

    PageList<T> queryPage(BaseQuery query);

    void batchDelete(Long[] ids);
}
