package com.service.CollectionsService;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service.CollectionsServiceImpl
 * @interfaceName CollectionsService
 * @date 2021/12/27 19:48
 **/
public interface CollectionsService {

    /**
     * <p> 判断学生是否收藏了店铺 </p>
     *
     * @param studentId 学生id
     * @param storeId 店铺id
     * @return boolean
     * @since 2021/12/20
     */
    boolean selectStudentCollectStore(String studentId, String storeId);

    /**
     * <p> 新增收藏 </p>
     *
     * @param studentId 学生id
     * @param storeId 店铺id
     * @return boolean
     * @since 2021/12/20
     */
    boolean insertStudentCollectStore(String studentId, String storeId);

    /**
     * <p> 取消收藏 </p>
     *
     * @param studentId 学生id
     * @param storeId 店铺id
     * @return boolean
     * @since 2021/12/20
     */
    boolean delStudentCollectStore(String studentId, String storeId);
}
