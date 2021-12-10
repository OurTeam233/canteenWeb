package com.mapper;

import com.pojo.Picture;

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
public interface PictureMapper {
    /**
     * 查询所有轮播图
     */
    List<Picture> selectAllSlideshow();
}
