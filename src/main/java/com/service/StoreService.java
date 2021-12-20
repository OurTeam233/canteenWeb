package com.service;

import com.mapper.DishesMapper;
import com.mapper.StoreMapper;
import com.mysql.cj.log.Log;
import com.pojo.Dishes;
import com.pojo.Store;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collections;
import java.util.List;

/**
 * <p> 对Store表进行操作 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service
 * @className DishesService
 * @date 2021/12/8 11:21
 * @TODO
 **/
public class StoreService {
    /**
     * 创建SqlSessionFactory工厂
     */
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    /**
     * <p> 查询所有店铺 </p>
     *
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/9
     */
    public List<Store> selectAll() {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
            // 执行sql并返回结果
            return storeMapper.selectStore();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * <p> 根据食堂编号查询店铺 </p>
     *
     * @param canteenId 食堂编号
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/9
     */
    public List<Store> selectAllByCanteenId(int canteenId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
            // 执行sql并返回结果
            return storeMapper.selectStoreByCanteenId(canteenId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    /**
     * <p> 根据标签编号查询店铺 </p>
     *
     * @param tagsId 标签编号
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/9
     */
    public List<Store> selectAllByTagsId(int tagsId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
            // 执行sql并返回结果
            return storeMapper.selectStoreByTagsId(tagsId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    /**
     * <p> 根据店铺编号查询店铺信息 </p>
     *
     * @param storeId 店铺编号
     * @return com.pojo.Store
     * @since 2021/12/11
     */
    public Store selectByStoreId(int storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
            // 执行sql并返回结果
            return storeMapper.selectByStoreId(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p> 根据店铺名或者菜品名搜索店铺 </p>
     *
     * @param storeName 店铺名称
     * @param dishesName 菜品名称
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/19
     */
    public List<Store> likeSelectStore(String keyword) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
            // 执行sql并返回结果
            return storeMapper.likeSelectStore(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * <p> 返回学生收藏的所有店铺 </p>
     *
     * @param studentId 学生编号
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/20
     */
    public List<Store> selectCollectionStore(String studentId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
            // 执行sql并返回结果
            return storeMapper.selectCollectionStore(studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
