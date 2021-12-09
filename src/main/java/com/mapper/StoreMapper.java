package com.mapper;

import com.pojo.Dishes;
import com.pojo.Store;

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
public interface StoreMapper {
    /**
     * <p> 查询所有店铺 </p>
     *
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/9
     */
    List<Store> selectStore();

    /**
     * <p> 根据食堂编号查询店铺 </p>
     *
     * @param canteenId 食堂编号
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/9
     */
    List<Store> selectStoreByCanteenId(int canteenId);

    /**
     * <p> 根据标签编号查询店铺 </p>
     *
     * @param tagsId 标签编号
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/9
     */
    List<Store> selectStoreByTagsId(int tagsId);
}
