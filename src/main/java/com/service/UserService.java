package com.service;

import com.mapper.UserMapper;
import com.pojo.Result;
import com.pojo.StudentInfo;
import com.pojo.User;
import com.util.JwtUtil;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> 对User表进行操作 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service
 * @className DishesService
 * @date 2021/12/8 11:21
 * @TODO
 **/
public class UserService {
    /**
     * 创建SqlSessionFactory工厂
     */
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * <p> 查询用户是否存在 </p>
     *
     * @param username 用户名
     * @param password 密码
     * @param userType 用户类型
     * @return int 返回用户id
     * @since 2021/12/11
     */
    public int selectUserStore(String username, String password, int userType) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行sql并返回用户对象
            User user = userMapper.selectUserStore(username, password);
            // 判断用户对象是否存在并符合要求
            if (user != null && user.getUserTypes().equals(userType)) {
                return user.getRelationId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



    /**
     * <p> 查询用户是否存在 </p>
     *
     * @param sequence 学号
     * @param departmentName 院系名
     * @param className 班级名
     * @return com.pojo.Result
     * @since 2021/12/11
     */
    public Result selectUserStudent(String sequence, String departmentName, String className) {
        // 初始化
        Result result = new Result();
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行sql并返回用户对象
            StudentInfo studentInfo = userMapper.selectUserStudent(sequence, departmentName, className);
            // 判断用户对象是否存在并符合要求
            result.setSuccess(studentInfo != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
