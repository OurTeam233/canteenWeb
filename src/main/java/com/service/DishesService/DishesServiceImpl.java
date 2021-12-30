package com.service.DishesService;

import com.mapper.DishesMapper;
import com.pojo.Dishes;
import com.pojo.DishesTypes;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collections;
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
public class DishesServiceImpl implements DishesService{
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
    @Override
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
     * @return boolean
     * @since 2021/12/21
     */
    @Override
    public Integer insertDishesType(String dishesTypeName) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            Object typeId = dishesMapper.selectDishesTypeByName(dishesTypeName);
            // 如果没有该类型
            if (typeId == null || (int) typeId == 0) {
                DishesTypes dishesTypes = new DishesTypes();
                dishesTypes.setName(dishesTypeName);
                dishesMapper.insertDishesType(dishesTypes);
                sqlSession.commit();
                return dishesTypes.getId();
            }
            return (int) typeId;
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
    @Override
    public int insertDishes(Dishes dishes, String dishTypeName, String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            DishesServiceImpl dishesServiceImpl = new DishesServiceImpl();
            // 获取菜品类型id
            Integer dishesTypeId = dishesServiceImpl.insertDishesType(dishTypeName);
            // 完善菜品信息
            dishes.setDishesTypeId(dishesTypeId);
            dishes.setStoreId(Integer.valueOf(storeId));
            dishes.setPrice(dishes.getPrice() * 100);
            dishes.setVipPrice(dishes.getVipPrice() * 100);
            // 执行sql并返回结果
            int insertId = dishesMapper.insertDishes(dishes);
            sqlSession.commit();
            return insertId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * <p> 将菜品售卖数量增加1 </p>
     *
     * @param dishesId 菜品id
     * @since 2021/12/22
     */
    @Override
    public void updateDishesSales(String dishesId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            Integer affectedRows = dishesMapper.updateDishesSales(dishesId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p> 通过菜品类型id删除菜品类型 </p>
     *
     * @param dishesTypeId 菜品类型id
     * @return boolean
     * @since 2021/12/22
     */
    @Override
    public boolean delDishesTypeById(String dishesTypeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            Integer affectedRows = dishesMapper.delDishesTypeById(dishesTypeId);
            sqlSession.commit();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * <p> 通过菜品id删除菜品 </p>
     *
     * @param dishesId 菜品类型id
     * @return boolean
     * @since 2021/12/23
     */
    @Override
    public boolean delDishesById(String dishesId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            Integer affectedRows = dishesMapper.delDishesById(dishesId);
            sqlSession.commit();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * <p> 查询所有菜品类型 </p>
     *
     * @return boolean
     * @since 2021/12/23
     */
    @Override
    public List<DishesTypes> selectDishesTypes(String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            return dishesMapper.selectDishesTypes(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
