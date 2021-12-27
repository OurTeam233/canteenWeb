package com.service.StudentService;

import com.pojo.Student;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service.StudentService
 * @interfaceName StudentService
 * @date 2021/12/27 20:00
 **/
public interface StudentService {

    /**
     * <p> 向数据库中插入学生对象 </p>
     *
     * @param student 要插入的学生信息
     * @return int 返回主键的值，0表示插入失败
     * @since 2021/12/14
     */
    int insertStudent(Student student);

    /**
     * <p> 更新学生表中的信息 </p>
     *
     * @param student 要更新的学生信息
     * @return int 返回影响的行数，0表示更新失败
     * @since 2021/12/14
     */
    int updateStudentById(Student student);

    /**
     * <p> 根据学生号查询学生信息 </p>
     *
     * @param sequence 要查询的学生id
     * @return Student 返回查询到的学生信息
     * @since 2021/12/14
     */
    Student selectStudentBySequence(String sequence);
}
