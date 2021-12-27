package com.service.StatisticsService;

import com.pojo.Dishes;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service.StatisticsService
 * @interfaceName StatisticsService
 * @date 2021/12/27 19:56
 **/
public interface StatisticsService {

    /**
     * <p> 根据店铺id查询第二天的的菜品数量 </p>
     *
     * @param storeId 店铺id
     * @return java.util.List<com.pojo.Dishes>
     * @since 2021/12/25
     */
    List<Dishes> selectDishesStatus1ByStoreId(String storeId);

    /**
     * <p> 根据店铺id查询总销售额 </p>
     *
     * @param storeId 店铺id
     * @return Integer 销售额
     * @since 2021/12/25
     */
    Integer selectTotalPrice(String storeId);
}
