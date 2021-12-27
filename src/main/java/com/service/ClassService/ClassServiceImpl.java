package com.service.ClassService;

import com.mapper.ClassMapper;
import com.mapper.DishesMapper;
import com.pojo.Dishes;
import com.pojo.StudentClass;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collections;
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
public class ClassServiceImpl implements ClassService {
    /**
     * 创建SqlSessionFactory工厂
     */
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * <p> 方法班级和学院名 </p>
     *
     * @return java.util.List<com.pojo.Dishes>
     * @since 2021/12/11
     */
    @Override
    public List<StudentClass> selectClass() {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            ClassMapper classMapper = sqlSession.getMapper(ClassMapper.class);
            // 执行sql并返回结果
            return classMapper.selectClass();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
