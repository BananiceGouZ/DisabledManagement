package cn.bananice.user.service.impl;

import cn.bananice.user.domain.Wxuser;
import cn.bananice.user.mapper.WxuserMapper;
import cn.bananice.user.service.IWxuserService;
import cn.bananice.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bananice
 * @since 2023-01-11
 */
@Service
public class WxuserServiceImpl extends BaseServiceImpl<Wxuser> implements IWxuserService {

    @Autowired
    private WxuserMapper wxuserMapper;

    @Override
    public Wxuser laodByOpenid(String openid) {
        return wxuserMapper.laodByOpenid(openid);
    }
}
