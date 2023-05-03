package com.atguigu.param;

import lombok.Data;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.param
 * @className: PageParam
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/1 15:09
 * @version: 1.0
 */
@Data
public class PageParam {
    private int currentPage = 1;
    private int pageSize = 15;
}
