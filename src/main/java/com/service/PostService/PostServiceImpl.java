package com.service.PostService;


import com.mapper.PictureMapper;
import com.mapper.PostMapper;
import com.pojo.Picture;
import com.pojo.Post;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collections;
import java.util.List;

/**
 * 帖子表(Post)表服务实现类
 *
 * @author makejava
 * @since 2021-12-29 12:29:45
 */
public class PostServiceImpl {
    /**
     * 创建SqlSessionFactory工厂
     */
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * <p> 查询所有帖子 </p>
     *
     * @return java.util.List<com.pojo.Post>
     * @since 2021/12/29
     */
    public List<Post> selectPost(String type) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
            // 执行sql并返回结果
            return postMapper.selectPost(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * <p> 新增贴子 </p>
     *
     * @param post 帖子
     * @param pictureList 图片
     * @return boolean
     * @since 2021/12/29
     */
    public boolean insertPost(Post post, List<String> pictureList) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
            PictureMapper pictureMapper = sqlSession.getMapper(PictureMapper.class);
            // 执行sql并返回结果
            postMapper.insertPost(post);
            // 构造图片
            Picture picture = new Picture();
            picture.setRelationTable(1);
            picture.setRelationTableId(post.getId());
            // 添加图片
            for (String pictureUrl : pictureList) {
                picture.setPictureUrl(pictureUrl);
                boolean insertable = pictureMapper.insertPicture(picture) > 0;
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

    public boolean deleteById(Integer id) {
        return false;
    }

    /**
     * <p> 根据用户id查询帖子 </p>
     *
     * @param userId 用户id
     * @return java.util.List<com.pojo.Post>
     * @since 2021/12/30
     */
    public List<Post> SelectPostById(String userId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
            // 执行sql并返回结果
            return postMapper.SelectPostById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
