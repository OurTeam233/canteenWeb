package com.pojo;

/**
 * <p> 用于小程序登录验证信息类 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.pojo
 * @className StudentInfo
 * @date 2021/12/14 9:18
 * @TODO
 **/
public class StudentInfo {
    /**
     * 班级名称
     */
    private String className;
    /**
     * 学院名称
     */
    private String departmentName;
    /**
     * 学号
     */
    private String sequence;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "className='" + className + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", sequence='" + sequence + '\'' +
                '}';
    }
}
