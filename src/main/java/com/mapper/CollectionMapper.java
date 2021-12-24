package com.mapper;

import com.pojo.Collections;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.mapper
 * @date 2021/12/8 8:35
 **/
public interface CollectionMapper {

    /**
     * <p> 查询学生是否关注店铺 </p>
     *
     * @param studentId 学生id
     * @param storeId 店铺id
     * @return boolean
     * @since 2021/12/20
     */
    Collections selectByStudentAndStore(@Param("studentId") String studentId,
                                        @Param("storeId") String storeId);

    /**
     * <p> 添加收藏 </p>
     * @param studentId 学生id
     * @param storeId 店铺id
     * @return int
     * @since 2021/12/20
     */
    int insertStudentCollectStore(@Param("studentId") String studentId,
                                  @Param("storeId") String storeId);

    /**
     * <p> 取消收藏 </p>
     *
     * @param studentId 学生id
     * @param storeId 店铺id
     * @return int
     * @since 2021/12/20
     */
    int delStudentCollectStore(@Param("studentId") String studentId,
                               @Param("storeId") String storeId);

    /**
     * <p> 获取该学生的所有收藏店铺 </p>
     *
     * @param studentId 学生id
     * @return java.util.List<com.pojo.Collections>
     * @since 2021/12/24
     */
    List<Collections> selectCollectionByStudentId(String studentId);
}
