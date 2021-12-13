package com.service;

import com.alibaba.fastjson.JSON;
import com.mapper.DishesMapper;
import com.mapper.UserMapper;
import com.pojo.Dishes;
import com.pojo.Result;
import com.pojo.User;
import com.util.JwtUtil;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collections;
import java.util.List;

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
     * @return com.pojo.Result
     * @since 2021/12/11
     */
    public Result selectUser(String username, String password, int userType) {
        // 初始化
        Result result = new Result();
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行sql并返回用户对象
            User user = userMapper.selectUser(username, password);
            // 判断用户对象是否存在并符合要求
            if (user != null && user.getUserTypes().equals(userType)) {
                // 存在用户
                result.setSuccess(true);
                // 生成jwt
                String jwtToken = JwtUtil.generateToken(user.getRelationId() + "", user.getUserTypes() + "");
                result.setToken(jwtToken);
            } else {
                result.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
