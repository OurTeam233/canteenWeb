package com.mapper;

import com.pojo.Dishes;
import com.pojo.Store;
import org.apache.ibatis.annotations.Param;

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

    /**
     * <p> 根据店铺id查询店铺信息 </p>
     *
     * @param storeId 店铺id
     * @return com.pojo.Store
     * @since 2021/12/11
     */
    Store selectByStoreId(int storeId);

    /**
     * <p> 通过店铺名或者菜品名进行模糊查询 </p>
     *
     * @param storeName 店铺名
     * @param dishesName 菜品名
     * @param tagsName 标签名
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/19
     */
    List<Store> likeSelectStore(String keyword);

    /**
     * <p> 显示所有收藏的店铺 </p>
     *
     * @param studentId 学生id
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/20
     */
    List<Store> selectCollectionStore(String studentId);

    /**
     * <p> 更改店铺状态 </p>
     *
     * @param storeId 店铺id
     * @param status 更改后的状态
     * @return boolean
     * @since 2021/12/21
     */
    Integer updateStoreStatus(@Param("storeId") String storeId,
                              @Param("status") String status);

    /**
     * <p> 新增店铺 </p>
     *
     * @param store 店铺信息
     * @return java.lang.Integer
     * @since 2021/12/24
     */
    Integer insertStore(Store store);

    /**
     * <p> 根据店铺删除店铺id </p>
     *
     * @param id 店铺id
     * @return java.lang.Integer
     * @since 2021/12/25
     */
    Integer deleteById(String id);
}
