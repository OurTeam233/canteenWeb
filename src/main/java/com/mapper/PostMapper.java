package com.mapper;


import com.pojo.Post;

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
public interface PostMapper {
    /**
     * <p> 查询所有帖子 </p>
     *
     * @return java.util.List<com.pojo.Post>
     * @since 2021/12/29
     */
    List<Post> selectPost(String type);

    /**
     * <p> 新增贴子 </p>
     *
     * @param post 帖子
     * @return boolean
     * @since 2021/12/29
     */
    Integer insertPost(Post post);

    /**
     * <p> 根据用户id查询帖子 </p>
     *
     * @param userId 用户id
     * @return java.util.List<com.pojo.Post>
     * @since 2021/12/30
     */
    List<Post> SelectPostById(String userId);
}
