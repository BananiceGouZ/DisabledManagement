package cn.bananice.basic.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDomain<T> implements Serializable {
    /*主键*/
    private Long id;
}
