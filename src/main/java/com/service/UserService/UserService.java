package com.service.UserService;

import com.pojo.Result;
import com.pojo.Store;
import com.pojo.User;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service.UserService
 * @interfaceName UserService
 * @date 2021/12/27 20:02
 **/
public interface UserService {
    /**
     * <p> 查询用户是否存在 </p>
     *
     * @param username 用户名
     * @param password 密码
     * @param userType 用户类型
     * @return int 返回用户id
     * @since 2021/12/11
     */
    int selectUserStore(String username, String password, int userType);

    /**
     * <p> 查询用户是否存在 </p>
     *
     * @param sequence       学号
     * @param departmentName 院系名
     * @param className      班级名
     * @return com.pojo.Result
     * @since 2021/12/11
     */
    Result selectUserStudent(String sequence, String departmentName, String className);

    /**
     * <p> 查询所有用户信息 </p>
     *
     * @return java.util.List<com.pojo.User>
     * @since 2021/12/24
     */
    List<User> selectUser();

    /**
     * <p> 新增用户信息 </p>
     *
     * @param user  用户对象
     * @param store 店铺对象
     * @return boolean
     * @since 2021/12/24
     */
    boolean insertUser(User user);

    /**
     * <p> 通过用户id删除用户 </p>
     *
     * @param id 用户id
     * @return boolean
     * @since 2021/12/24
     */
    boolean deleteUserById(String id);

    /**
     * <p> 更新用户信息 </p>
     *
     * @param user 用户对象
     * @return boolean
     * @since 2021/12/24
     */
    boolean updateUser(User user);
}
