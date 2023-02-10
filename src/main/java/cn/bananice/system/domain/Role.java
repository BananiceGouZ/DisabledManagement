package cn.bananice.system.domain;

import cn.bananice.basic.domain.BaseDomain;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author bananice
 * @since 2023-01-13
 */
@Data
public class Role extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private String name;
    private String sn;

    private List<Long> menus;
    private List<Long> permissions;
}
