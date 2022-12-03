package com.shopreviews.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zyh
 */
@Data
public class ScrollResult {
    private List<?> list;
    private Long minTime;
    private Integer offset;
}
