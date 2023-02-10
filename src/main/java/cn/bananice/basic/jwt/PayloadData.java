package cn.bananice.basic.jwt;


import cn.bananice.system.domain.Menu;
import lombok.Data;

import java.util.List;

@Data
public class PayloadData {
    private Object logininfo;
    private List<String> permissions;
    private List<Menu> menus;
}