package com.service;

import com.mapper.ClassMapper;
import com.mapper.CollectionMapper;
import com.mapper.DishesMapper;
import com.pojo.Collections;
import com.pojo.Dishes;
import com.pojo.StudentClass;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * <p> 班级信息 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service
 * @className ClassService
 * @date 2021/12/13 20:33
 * @TODO
 **/
public class CollectionsService {
    /**
     * 创建SqlSessionFactory工厂
     */
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public boolean selectDishesByStoreId(String studentId, String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            CollectionMapper collectionMapper = sqlSession.getMapper(CollectionMapper.class);
            // 执行sql并返回结果
            Collections collections = collectionMapper.selectByStudentAndStore(studentId, storeId);
            return collections != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
