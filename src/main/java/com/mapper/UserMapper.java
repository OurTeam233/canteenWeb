package com.mapper;

import com.pojo.StudentInfo;
import com.pojo.User;
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
public interface UserMapper {
    /**
     * 查询商家用户
     */
     User selectUserStore(@Param("username") String username,
                          @Param("password") String password);

    /**
     * 查询学生用户
     */
     StudentInfo selectUserStudent(@Param("sequence") String sequence,
                                   @Param("departmentName") String departmentName,
                                   @Param("className") String className);

     /**
      * 查询用户名是否存在
      */
     User selectByUsername(String username);

     /**
      * 查询所有用户信息
      */
     List<User> selectUser();

     /**
      * 增加用户
      */
     Integer insertUser(@Param("username") String username,
                        @Param("password") String password,
                        @Param("relationId") String relationId);

     /**
      * 删除用户
      */
     Integer deleteUser(@Param("id") String id);

     /**
      * 修改用户名或者密码
      */
     Integer updateUser(@Param("id") String id,
                        @Param("username") String username,
                        @Param("password") String password);

     /**
      * 通过id查询用户信息
      */
     User selectById(String id);
}
