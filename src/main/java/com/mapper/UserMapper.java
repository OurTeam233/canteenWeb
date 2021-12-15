package com.mapper;

import com.pojo.StudentInfo;
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
     * 查询商家用户
     */
     User selectUserStore(@Param("username") String username, @Param("password") String password);

    /**
     * 查询学生用户
     */
     StudentInfo selectUserStudent(@Param("sequence") String sequence, @Param("departmentName") String departmentName, @Param("className") String className);
}
