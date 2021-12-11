package com.service;

import com.mapper.DishesMapper;
import com.pojo.Dishes;
import com.util.SqlSessionFactoryUtils;
import java.util.Collections;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * <p> 对dishes表进行操作 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service
 * @className DishesService
 * @date 2021/12/8 11:21
 * @TODO
 **/
public class DishesService {
    /**
     * 创建SqlSessionFactory工厂
     */
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    /**
     * <p> 根据店铺id查询菜品信息 </p>
     *
     * @param storeId 店铺id
     * @return java.util.List<com.pojo.Dishes>
     * @since 2021/12/11
     */
    public List<Dishes> selectDishesByStoreId(int storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            return dishesMapper.selectDishesByStoreId(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
