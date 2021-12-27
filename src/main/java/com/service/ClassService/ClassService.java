package com.service.ClassService;

import com.pojo.StudentClass;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service.ClassService
 * @interfaceName ClassService
 * @date 2021/12/27 19:46
 **/
public interface ClassService {

    /**
     * <p> 方法班级和学院名 </p>
     *
     * @return java.util.List<com.pojo.Dishes>
     * @since 2021/12/11
     */
    List<StudentClass> selectClass();
}
