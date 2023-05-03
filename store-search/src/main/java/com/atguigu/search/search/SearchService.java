package com.atguigu.search.search;

import com.atguigu.param.ProductSearchParam;
import com.atguigu.utils.R;

/**
 * @projectName: b2c-store
 * @package: com.atguigu.search.search
 * @className: SearchService
 * @author: BangDi
 * @description: TODO
 * @date: 2023/5/1 15:21
 * @version: 1.0
 */
public interface SearchService {
    /**
     * 根据关键字和分页进行数据库查询
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);
}
