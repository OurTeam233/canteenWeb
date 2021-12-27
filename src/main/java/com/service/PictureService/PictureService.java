package com.service.PictureService;

import com.pojo.Picture;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service.PictureService
 * @interfaceName PictureService
 * @date 2021/12/27 19:55
 **/
public interface PictureService {
    /**
     * <p> 查询所有的轮播图信息 </p>
     *
     * @return java.util.List<com.pojo.Picture>
     * @since 2021/12/11
     */
    List<Picture> selectAllSlideshow();
}
