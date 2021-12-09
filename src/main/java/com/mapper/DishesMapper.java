package com.mapper;

import com.pojo.Dishes;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.mapper
 * @interfaceName DishesMapper
 * @date 2021/12/8 8:35
 **/
public interface DishesMapper {
    /**
     * 通过店铺id查询菜品
     */
    List<Dishes> selectDishesByStoreId(int storeId);
}
