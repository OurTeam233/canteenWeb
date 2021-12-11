package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;


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
public interface UserMapper {
    /**
     * 查询用户
     */
     User selectUser(@Param("username") String username, @Param("password") String password);
}
