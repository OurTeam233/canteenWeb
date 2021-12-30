package com.mapper;

import com.pojo.LostFound;

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
public interface LostFoundMapper {
    /**
     * <p> 发布失物招领帖子 </p>
     *
     * @param lostFound 失物招领信息
     * @return java.lang.Integer
     * @since 2021/12/30
     */
    Integer insertLostFound(LostFound lostFound);

    /**
     * <p> 查询贴子 </p>
     *
     * @param types 类型
     * @return java.util.List<com.pojo.LostFound>
     * @since 2021/12/30
     */
    List<LostFound> selectLostFound(String types);

    /**
     * <p> 通过关键字查找物品 </p>
     *
     * @param keyword 关键字
     * @return boolean
     * @since 2021/12/30
     */
    List<LostFound> selectLostFoundByKeyword(String keyword);
}
