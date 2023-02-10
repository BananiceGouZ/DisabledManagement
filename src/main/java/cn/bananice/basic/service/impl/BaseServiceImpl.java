package cn.bananice.basic.service.impl;

import cn.bananice.basic.mapper.BaseMapper;
import cn.bananice.basic.query.BaseQuery;
import cn.bananice.basic.service.IBaseService;
import cn.bananice.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    BaseMapper<T> baseMapper;

    @Override
    @Transactional
    public void add(T t) {
        baseMapper.save(t);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        baseMapper.delete(id);
    }

    @Override
    @Transactional
    public void update(T t) {
        baseMapper.update(t);
    }

    @Override
    public T queryById(Long id) {
        return baseMapper.loadById(id);
    }

    @Override
    public List<T> queryAll() {
        return baseMapper.loadAll();
    }

    @Override
    public PageList<T> queryPage(BaseQuery query) {

        Integer queryCount = baseMapper.queryCount(query);
        if (queryCount == 0) {
            return new PageList<>();
        }

        List<T> queryData = baseMapper.queryData(query);
        return new PageList<>(queryCount, queryData);
    }

    @Override
    @Transactional
    public void batchDelete(Long[] ids) {
        baseMapper.batchDelete(ids);
    }
}
