package com.service.StudentService;

import com.mapper.ClassMapper;
import com.mapper.PictureMapper;
import com.mapper.StudentMapper;
import com.pojo.Picture;
import com.pojo.Student;
import com.pojo.StudentClass;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collections;
import java.util.List;

/**
 * <p> 学生用户信息 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service
 * @className ClassService
 * @date 2021/12/13 20:33
 * @TODO
 **/
public class StudentServiceImpl implements StudentService{
    /**
     * 创建SqlSessionFactory工厂
     */
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    /**
     * <p> 向数据库中插入学生对象 </p>
     *
     * @param student 要插入的学生信息
     * @return int 返回主键的值，0表示插入失败
     * @since 2021/12/14
     */
    @Override
    public int insertStudent(Student student) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            // 执行sql并返回结果
            int insertStudent = studentMapper.insertStudent(student);
            sqlSession.commit();
            return insertStudent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * <p> 更新学生表中的信息 </p>
     *
     * @param student 要更新的学生信息
     * @return int 返回影响的行数，0表示更新失败
     * @since 2021/12/14
     */
    @Override
    public int updateStudentById(Student student) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            // 执行sql并返回结果
            int updateStudentById = studentMapper.updateStudentById(student);
            sqlSession.commit();
            return updateStudentById;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * <p> 根据学生号查询学生信息 </p>
     *
     * @param sequence 要查询的学生id
     * @return Student 返回查询到的学生信息
     * @since 2021/12/14
     */
    @Override
    public Student selectStudentBySequence(String sequence) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            // 执行sql并返回结果
            return studentMapper.selectBySequence(sequence);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
