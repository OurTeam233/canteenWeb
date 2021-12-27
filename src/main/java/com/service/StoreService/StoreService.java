package com.service.StoreService;

import com.pojo.Store;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service.StoreService
 * @interfaceName StoreService
 * @date 2021/12/27 19:57
 **/
public interface StoreService {

    /**
     * <p> 查询所有店铺 </p>
     *
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/9
     */
    List<Store> selectAll();

    /**
     * <p> 根据食堂编号查询店铺 </p>
     *
     * @param canteenId 食堂编号
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/9
     */
    List<Store> selectAllByCanteenId(int canteenId);

    /**
     * <p> 根据标签编号查询店铺 </p>
     *
     * @param tagsId 标签编号
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/9
     */
    List<Store> selectAllByTagsId(int tagsId);

    /**
     * <p> 根据店铺编号查询店铺信息 </p>
     *
     * @param storeId 店铺编号
     * @return com.pojo.Store
     * @since 2021/12/11
     */
    Store selectByStoreId(int storeId);

    /**
     * <p> 根据店铺名或者菜品名搜索店铺 </p>
     *
     * @param keyword 搜索词
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/19
     */
    List<Store> likeSelectStore(String keyword);

    /**
     * <p> 返回学生收藏的所有店铺 </p>
     *
     * @param studentId 学生编号
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/20
     */
    List<Store> selectCollectionStore(String studentId);

    /**
     * <p> 开关店铺 </p>
     *
     * @param storeId 店铺编号
     * @param status 要更改的店铺状态
     * @return boolean
     * @since 2021/12/24
     */
    boolean updateStoreStatus(String storeId, String status);

    /**
     * <p> 根据学生推荐店铺 </p>
     *
     * @param studentId 学生编号
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/24
     */
    List<Store> recommendStore(String studentId);

    /**
     * <p> 新增店铺 </p>
     *
     * @param store 店铺
     * @return Integer 插入后的主键
     * @since 2021/12/24
     */
    Integer insertStore(Store store);

    /**
     * <p> 更新店铺人均消费 </p>
     *
     * @param storeId 店铺编号
     * @return boolean
     * @since 2021/12/27
     */
    boolean updatePerPrice(Integer storeId);
}
