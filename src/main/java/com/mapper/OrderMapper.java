package com.mapper;

import com.pojo.Dishes;
import com.pojo.StudentClass;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.mapper
 * @date 2021/12/8 8:35
 **/
public interface ClassMapper {
    List<StudentClass> selectClass();
}
