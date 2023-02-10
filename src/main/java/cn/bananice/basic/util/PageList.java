package cn.bananice.basic.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageList<T> {
    private Integer pageCount = 0;
    private List<T> list = new ArrayList<>();
}
