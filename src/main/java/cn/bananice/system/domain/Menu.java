package cn.bananice.system.domain;

import cn.bananice.basic.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
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
public class Menu extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private String name;
    private String component;
    private String url;
    private String icon;
    private Integer index;
    private Long parentId;
    private String intro;
    private Boolean state;

    private Menu parent;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)//如果这个集合为空 就不需要转成json字符串
    private List<Menu> children = new ArrayList<>();

}
