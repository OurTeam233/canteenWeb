package com.service.LostFound;

import com.mapper.LostFoundMapper;
import com.mapper.PictureMapper;
import com.pojo.LostFound;
import com.pojo.Picture;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.*;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service.LostFount
 * @className LostFountServiceImpl
 * @date 2021/12/29 18:03
 * @TODO
 **/
public class LostFoundServiceImpl {

    /**
     * 创建SqlSessionFactory工厂
     */
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * <p> 增加帖子 </p>
     *
     * @return java.util.List<com.pojo.LostFound>
     * @since 2021/12/30
     */
    public boolean insertLostFound(LostFound lostFound) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            LostFoundMapper lostFoundMapper = sqlSession.getMapper(LostFoundMapper.class);
            PictureMapper pictureMapper = sqlSession.getMapper(PictureMapper.class);
            // 将对象成员补充完整
            Date startDate = lostFound.getStartDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DATE, 10);
            lostFound.setEndDate(calendar.getTime());
            // 执行sql并返回结果
            boolean insertable = lostFoundMapper.insertLostFound(lostFound) > 0;
            if (!insertable) {
                return false;
            }
            // 添加图片
            List<Picture> pictureList = lostFound.getPictureList();
            for (Picture picture : pictureList) {
                picture.setRelationTable(2);
                picture.setRelationTableId(lostFound.getId());
                insertable = pictureMapper.insertPicture(picture) > 0;
                if (!insertable) {
                    return false;
                }
            }
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * <p> 查询贴子 </p>
     *
     * @param types 类型
     * @return java.util.List<com.pojo.LostFound>
     * @since 2021/12/30
     */
    public List<LostFound> selectLostFound(String types) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            LostFoundMapper lostFoundMapper = sqlSession.getMapper(LostFoundMapper.class);
            // 执行sql并返回结果
            return lostFoundMapper.selectLostFound(types);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * <p> 通过关键字查找物品 </p>
     *
     * @param keyword 关键字
     * @return boolean
     * @since 2021/12/30
     */
    public List<LostFound> selectLostFoundByKeyword(String keyword) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            LostFoundMapper lostFoundMapper = sqlSession.getMapper(LostFoundMapper.class);
            // 执行sql并返回结果
            return lostFoundMapper.selectLostFoundByKeyword(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
