package com.service.DishesService;

import com.pojo.Dishes;
import com.pojo.DishesTypes;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service.DishesService
 * @interfaceName DishesService
 * @date 2021/12/27 19:50
 **/
public interface DishesService {

    /**
     * <p> 根据店铺id查询菜品信息 </p>
     *
     * @param storeId 店铺id
     * @return java.util.List<com.pojo.Dishes>
     * @since 2021/12/11
     */
    List<Dishes> selectDishesByStoreId(int storeId);

    /**
     * <p> 新增菜品类型名 </p>
     *
     * @param dishesTypeName 菜品类型名称
     * @return boolean
     * @since 2021/12/21
     */
    Integer insertDishesType(String dishesTypeName);

    /**
     * <p> 新增菜品 </p>
     *
     * @param dishes 菜品信息
     * @return int 影响的行数
     * @since 2021/12/21
     */
    int insertDishes(Dishes dishes, String dishTypeName, String storeId);

    /**
     * <p> 将菜品售卖数量增加1 </p>
     *
     * @param dishesId 菜品id
     * @since 2021/12/22
     */
    void updateDishesSales(String dishesId);

    /**
     * <p> 通过菜品类型id删除菜品类型 </p>
     *
     * @param dishesTypeId 菜品类型id
     * @return boolean
     * @since 2021/12/22
     */
    boolean delDishesTypeById(String dishesTypeId);

    /**
     * <p> 通过菜品id删除菜品 </p>
     *
     * @param dishesId 菜品类型id
     * @return boolean
     * @since 2021/12/23
     */
    boolean delDishesById(String dishesId);

    /**
     * <p> 查询所有菜品类型 </p>
     *
     * @return boolean
     * @since 2021/12/23
     */
    List<DishesTypes> selectDishesTypes(String storeId);
}
