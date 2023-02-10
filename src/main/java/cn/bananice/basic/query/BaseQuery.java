package cn.bananice.basic.query;

import lombok.Data;

@Data
public class BaseQuery {
    private Integer currentPage = 1;
    private Integer pageSize = 4;

    private String keyword;   //查找用的关键字

    public Integer getBegin() {
        return (currentPage - 1) * pageSize;
    }
}
