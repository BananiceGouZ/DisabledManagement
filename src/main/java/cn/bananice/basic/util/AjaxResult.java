package cn.bananice.basic.util;

public class AjaxResult {

    private Boolean success;
    private String message;

    private Object resultObj;

    public static AjaxResult getAjaxResult() {
        return new AjaxResult().setSuccess(true).setMessage("操作成功");
    }

    public Boolean getSuccess() {
        return success;
    }

    public AjaxResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public AjaxResult setResultObj(Object resultObj) {
        this.resultObj = resultObj;
        return this;
    }

}
