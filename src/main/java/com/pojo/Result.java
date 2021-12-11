package com.pojo;

/**
 * <p> 成功或者失败的结果集 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.pojo
 * @className Result
 * @date 2021/12/10 17:27
 * @TODO
 **/
public class Result {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                '}';
    }
}
