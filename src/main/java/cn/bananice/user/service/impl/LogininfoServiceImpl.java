package cn.bananice.user.service.impl;

import cn.bananice.user.domain.Logininfo;
import cn.bananice.user.mapper.LogininfoMapper;
import cn.bananice.user.service.ILogininfoService;
import cn.bananice.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bananice
 * @since 2023-01-08
 */
@Service
public class LogininfoServiceImpl extends BaseServiceImpl<Logininfo> implements ILogininfoService {

    @Autowired
    private LogininfoMapper logininfoMapper;

    @Override
    public void active(Long logininfoId, boolean state) {
        logininfoMapper.updateStateById(logininfoId,state);
    }
}
