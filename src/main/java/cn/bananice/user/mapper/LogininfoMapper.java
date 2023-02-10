package cn.bananice.user.mapper;

import cn.bananice.user.domain.Logininfo;
import cn.bananice.basic.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bananice
 * @since 2023-01-08
 */
public interface LogininfoMapper extends BaseMapper<Logininfo> {

    Logininfo loadByAccountAndType(@Param("account") String account, @Param("type") String type);

    Logininfo loadByUserId(Long userId);

    void updateStateById(@Param("id") Long logininfoId, @Param("state") boolean state);
}
