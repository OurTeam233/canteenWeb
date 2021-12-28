package com.service.UserService;

import com.alibaba.fastjson.JSON;
import com.mapper.StoreMapper;
import com.mapper.UserMapper;
import com.pojo.*;
import com.util.JwtUtil;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class UserServiceImpl implements UserService {
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
    @Override
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
        return -1;
    }


    /**
     * <p> 查询用户是否存在 </p>
     *
     * @param sequence       学号
     * @param departmentName 院系名
     * @param className      班级名
     * @return com.pojo.Result
     * @since 2021/12/11
     */
    @Override
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

    /**
     * <p> 查询所有用户信息 </p>
     *
     * @return java.util.List<com.pojo.User>
     * @since 2021/12/24
     */
    @Override
    public List<User> selectUser() {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行sql并返回用户对象
            return userMapper.selectUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * <p> 新增用户信息 </p>
     *
     * @param user  用户对象
     * @return boolean
     * @since 2021/12/24
     */
    @Override
    public boolean insertUser(User user) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行sql并返回用户对象
            // 构造店铺对象
            Store store = new Store();
            store.setName("默认店铺");
            Canteen canteen = new Canteen();
            canteen.setId(1);
            canteen.setName("一食堂");
            store.setCanteen(canteen);
            storeMapper.insertStore(store);
            Integer storeId = store.getId();
            User username = userMapper.selectByUsername(user.getUsername());
            // 存在storeId
            boolean existsStoreId = storeId != null && storeId > 0;
            // 不存在用户名
            boolean notExistsUsername = username == null;

            if (existsStoreId && notExistsUsername) {
                boolean insertable = userMapper.insertUser(user.getUsername(), user.getPassword(), storeId + "") > 0;
                sqlSession.commit();
                return insertable;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * <p> 通过用户id删除用户 </p>
     *
     * @param id 用户id
     * @return boolean
     * @since 2021/12/24
     */
    @Override
    public boolean deleteUserById(String id) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
            // 执行sql并返回用户对象
            // 找到用户开设的店铺
            User user = userMapper.selectById(id);
            // 尝试删除店铺
            boolean deleteStorable = true;
            if (user != null) {
                Integer relationId = user.getRelationId();
                // 删除店铺
                deleteStorable = storeMapper.deleteById(relationId + "") > 0;
            }
            // 删除用户
            boolean deleteUsable = userMapper.deleteUser(id) > 0;
            sqlSession.commit();
            return deleteStorable && deleteUsable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * <p> 更新用户信息 </p>
     *
     * @param user 用户对象
     * @return boolean
     * @since 2021/12/24
     */
    @Override
    public boolean updateUser(User user) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行sql并返回用户对象
            boolean updatable = userMapper.updateUser(user.getId() + "", user.getUsername(), user.getPassword()) > 0;
            sqlSession.commit();
            return updatable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
