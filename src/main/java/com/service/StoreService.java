package com.service;

import com.mapper.CollectionMapper;
import com.mapper.DishesMapper;
import com.mapper.StoreMapper;
import com.mysql.cj.log.Log;
import com.pojo.Dishes;
import com.pojo.Store;
import com.util.SqlSessionFactoryUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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
     * @param keyword 搜索词
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

    /**
     * <p> 开关店铺 </p>
     *
     * @param storeId 店铺编号
     * @param status 要更改的店铺状态
     * @return boolean
     * @since 2021/12/24
     */
    public boolean updateStoreStatus(String storeId, String status) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
            // 执行sql并返回结果
            boolean updatable = storeMapper.updateStoreStatus(storeId, status) > 0;
            sqlSession.commit();
            return updatable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * <p> 根据学生推荐店铺 </p>
     *
     * @param studentId 学生编号
     * @return java.util.List<com.pojo.Store>
     * @since 2021/12/24
     */
    public List<Store> recommendStore(String studentId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
            CollectionMapper collectionMapper = sqlSession.getMapper(CollectionMapper.class);
            // 所有店铺
            List<Store> storeList = storeMapper.selectStore();
            storeList.sort(Comparator.comparingInt(Store::getId));
            // 该学生收藏的店铺
            List<com.pojo.Collections> collectionsList = collectionMapper.selectCollectionByStudentId(studentId);
            collectionsList.sort(Comparator.comparingInt(com.pojo.Collections::getStoreId));
            // 为每个店铺设计推荐值 O(nm)
            for (int i = 0, j = 0; i < storeList.size(); i++) {
                Store store = storeList.get(i);
                int storeId = store.getId();
                Integer attention = store.getAttention();
                Integer sales = store.getSales();
                Double score = store.getScore();
                // 初始化推荐值
                double recommended = attention * sales * score * 0.3;
                int sumRandomNumber = 20;
                while (j < collectionsList.size() && collectionsList.get(j).getStoreId() < storeId) {
                    j++;
                }
                if (j < collectionsList.size() && collectionsList.get(j).getStoreId() == storeId) {
                    int t = RandomUtils.nextInt(0, 20);
                    sumRandomNumber -= t;
                    recommended *= 40 - t;
                }
                // 与收藏量有关
                int t = RandomUtils.nextInt(0, sumRandomNumber);
                sumRandomNumber -= t;
                attention *= 20 - t;
                // 与销量有关
                t = RandomUtils.nextInt(0, sumRandomNumber);
                sumRandomNumber -= t;
                sales *= 20 - t;
                // 与评分有关
                t = RandomUtils.nextInt(0, sumRandomNumber);
                sumRandomNumber -= t;
                score *= 20 - t;
                // 设置推荐值
                store.setRecommendValue(recommended * attention * sales * score);
            }
            // 进行排序
            storeList.sort(Comparator.comparingDouble(Store::getRecommendValue).reversed());
            // 返回结果
            return storeList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * <p> 新增店铺 </p>
     *
     * @param store 店铺
     * @return Integer 插入后的主键
     * @since 2021/12/24
     */
    public Integer insertStore(Store store) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
            // 执行sql并返回结果
            storeMapper.insertStore(store);
            sqlSession.commit();
            return store.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
