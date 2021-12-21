package com.service;

import com.mapper.DishesMapper;
import com.pojo.Dishes;
import com.pojo.DishesTypes;
import com.pojo.OrderDetails;
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

    /**
     * <p> 新增菜品类型名 </p>
     *
     * @param dishesTypeName 菜品类型名称
     * @return int 影响的行数
     * @since 2021/12/21
     */
    public int insertDishesType(String dishesTypeName) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            int typeId = dishesMapper.selectDishesTypeByName(dishesTypeName);
            if (typeId == 0) {
                int insertId = dishesMapper.insertDishesType(dishesTypeName);
                sqlSession.commit();
                return insertId;
            }
            return typeId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * <p> 新增菜品 </p>
     *
     * @param dishes 菜品信息
     * @return int 影响的行数
     * @since 2021/12/21
     */
    public int insertDishes(Dishes dishes) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            int insertId = dishesMapper.insertDishes(dishes);
            sqlSession.commit();
            return insertId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public boolean updateDishesSales(String dishesId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            Integer affectedRows = dishesMapper.updateDishesSales(dishesId);
            sqlSession.commit();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
