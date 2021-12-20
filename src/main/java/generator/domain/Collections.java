package generator.domain;

import java.io.Serializable;

/**
 * 收藏表
 * @TableName collections
 */
public class Collections implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 学生编号
     */
    private Integer studentId;

    /**
     * 店铺编号
     */
    private Integer storeId;

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 学生编号
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * 学生编号
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 店铺编号
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * 店铺编号
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Collections other = (Collections) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentId=").append(studentId);
        sb.append(", storeId=").append(storeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}