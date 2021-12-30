package com.mapper;

import com.pojo.Dishes;
import com.pojo.DishesTypes;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
    Integer insertDishesType(DishesTypes dishesTypes);

    /**
     * <p> 通过id删除菜品类型 </p>
     *
     * @param dishTypeId 菜品类型id
     * @return java.lang.Integer 被影响行数
     * @since 2021/12/22
     */
    Integer delDishesTypeById(String dishTypeId);

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

    /**
     * <p> 删除菜品信息 </p>
     *
     * @param dishesId 菜品id
     * @return java.lang.Integer 受影响行数
     * @since 2021/12/21
     */
    Integer delDishesById(String dishesId);

    /**
     * <p> 查询所有菜品类型 </p>
     *
     * @return java.util.List<com.pojo.DishesTypes>
     * @since 2021/12/23
     */
    List<DishesTypes> selectDishesTypes(String storeId);

    /**
     * <p> 根据店铺id查询第二天的的菜品数量 </p>
     *
     * @param storeId 店铺id
     * @return java.util.List<com.pojo.DishesTypes>
     * @since 2021/12/25
     */
    List<Dishes> selectDishesNextDayStatus1ByStoreId(@Param("storeId") String storeId,
                                                     @Param("curTime") Date curTime);

    /**
     * 根据店铺id查询总销售额
     *
     * @param storeId 店铺id
     */
    Integer selectTotalPrice(String storeId);

    /**
     * <p> 查询昨天下今天的订单 </p>
     *
     * @param storeId 店铺id
     * @param curTime 时间
     * @return java.util.List<com.pojo.Dishes>
     * @since 2021/12/28
     */
    List<Dishes> selectDishesTodayStatus1ByStoreId(@Param("storeId") String storeId,
                                                   @Param("curTime") Date curTime,
                                                   @Param("nextTime") Date nextTime);

    /**
     * <p> 查询店铺菜品数量的统计 </p>
     *
     * @param storeId 店铺id
     * @return java.lang.Integer
     * @since 2021/12/29
     */
    List<Dishes> selectStoreDishes(String storeId);

    /**
     * <p> 查询所有菜品数量 </p>
     *
     * @param storeId 店铺id
     * @return java.lang.Integer
     * @since 2021/12/29
     */
    Integer selectTotalDishes(String storeId);
}
