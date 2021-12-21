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
     * @return int 插入后菜品类型id
     * @since 2021/12/21
     */
    Integer insertDishesType(String name);

    /**
     * <p> 新增菜品 </p>
     *
     * @param dishes 菜品信息
     * @return java.lang.Integer 插入后菜品id
     * @since 2021/12/21
     */
    Integer insertDishes(Dishes dishes);

    /**
     * <p> 更新菜品售卖数量 </p>
     *
     * @param dishesId 菜品id
     * @return java.lang.Integer 受影响行数
     * @since 2021/12/21
     */
    Integer updateDishesSales(String dishesId);
}
