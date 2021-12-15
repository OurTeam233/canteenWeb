package com.mapper;

import com.pojo.Student;
import com.pojo.StudentInfo;
import com.pojo.User;
import org.apache.ibatis.annotations.Param;


/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.mapper
 * @interfaceName DishesMapper
 * @date 2021/12/8 8:35
 **/
public interface StudentMapper {
    /**
     * 查询学生信息
     * @param student 学生对象
     * @return
     */
    int insertStudent(@Param("Student") Student student);

    /**
     * <p> 更新学生信息 </p>
     *
     * @param student 学生对象
     * @return int
     * @throws
     * @since 2021/12/14
     */
    int updateStudentById(@Param("Student") Student student);

    /**
     * <p> 按学号查询学生 </p>
     *
     * @param id 通过学号查找
     * @return com.pojo.Student
     * @since 2021/12/15
     */
    Student selectBySequence(@Param("sequence") String sequence);
}
