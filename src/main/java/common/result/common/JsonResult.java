package common.result.common;

/**
 * Created by Administrator on 2015/10/15 0015.
 */
public class JsonResult {

    /**
     * 返回码，默认正常：0
     */
    private int state = 0;

    /**
     * 信息
     */
    private String msg = "操作成功";

    /**
     * 数据对象
     */
    private Object data;

    public JsonResult() {
    }

    public JsonResult(int state, String message) {
        this.state = state;
        this.msg = message;
    }

    public JsonResult(int state, String message,Object data) {
        this.state = state;
        this.msg = message;
        this.data = data;
    }

    public JsonResult(ResultCode resultCode) {
        this.state = resultCode.getStatusCode();
        this.msg = resultCode.getErrorMsg();
    }

    public JsonResult(ResultCode resultCode,Object data) {
        this.state = resultCode.getStatusCode();
        this.msg = resultCode.getErrorMsg();
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public JsonResult setState(int code) {
        this.state = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return state == 0;
    }
}