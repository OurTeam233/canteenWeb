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

    /**
     * <p> 根据菜品名称返回菜品编号 </p>
     *
     * @param name 菜品类型名称
     * @return int 菜品类型id
     * @since 2021/12/21
     */
    Integer selectDishesTypeByName(String name);

    /**
     * <p> 新增菜品类型名 </p>
     *
     * @param name 菜品类型名称
     * @return int 菜品类型id
     * @since 2021/12/21
     */
    Integer insertDishesType(String name);
}
