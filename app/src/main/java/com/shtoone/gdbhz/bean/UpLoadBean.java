package com.shtoone.gdbhz.bean;

/**
 * Created by liangfeng on 2017/11/14.
 */

public class UpLoadBean {

    /**
     * success : 1
     * description : 超标处置成功！
     */

    private boolean success;
    private String description;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UpLoadBean{" +
                "success=" + success +
                ", description='" + description + '\'' +
                '}';
    }
}
