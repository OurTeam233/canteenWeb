package com.service.PictureService;

import com.mapper.DishesMapper;
import com.mapper.PictureMapper;
import com.pojo.Dishes;
import com.pojo.Picture;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collections;
import java.util.List;

/**
 * <p> 图片服务类 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service
 * @className PictureService
 * @date 2021/12/10 9:43
 * @TODO
 **/
public class PictureServiceImpl implements PictureService {
    /**
     * 创建SqlSessionFactory工厂
     */
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * <p> 查询所有的轮播图信息 </p>
     *
     * @return java.util.List<com.pojo.Picture>
     * @since 2021/12/11
     */
    @Override
    public List<Picture> selectAllSlideshow() {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            PictureMapper pictureMapper = sqlSession.getMapper(PictureMapper.class);
            // 执行sql并返回结果
            return pictureMapper.selectAllSlideshow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
