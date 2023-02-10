package cn.bananice.basic.mapper;

import cn.bananice.basic.query.BaseQuery;

import java.util.List;

public interface BaseMapper<T> {
    void save(T t);

    void delete(Long id);

    void update(T t);

    T loadById(Long id);

    List<T> loadAll();

    /*下面是分页用到的两个方法*/
    Integer queryCount(BaseQuery tQuery);

    List<T> queryData(BaseQuery tQuery);

    void batchDelete(Long[] ids);
}
