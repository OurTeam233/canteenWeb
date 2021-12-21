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

    private String token;

    private String message;

    private String code;

    private String openId;

    private Integer sum;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
                ", token='" + token + '\'' +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", openId='" + openId + '\'' +
                ", sum=" + sum +
                ", id=" + id +
                '}';
    }
}
