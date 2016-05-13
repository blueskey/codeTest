package common.result.common;

/**
 * Created by Administrator on 2015/10/15 0015.
 */
public enum CommonResultCode implements ResultCode {
    /** 操作成功 */
    SUCCESS("SUCCESS", 0,"操作成功"),
    
    /**操作失败标示**/
    FAIL("FAIL",-1,"失败"),
    
    /** 接口异常 */
    INTERFACE_ERROR("PARAM_ERROR", 1,"接口异常"),
    
    /** 登录超时 */
    LOGIN_TIMEOUT("LOGIN_TIMEOUT", 590, "登录超时，请重新登录！"),
    
    /** 无权访问 */
    NOT_ALLOW_TO_ACCESS("NOT_ALLOW_TO_ACCESS", 591, "对不起，您无权访问该功能！"),

    /** 参数错误 */
    PARAM_ERROR("PARAM_ERROR", 501,"参数错误"),

    EXPORT_DATA_EMPTY("EXPORT_DATA_EMPTY", 592,"无导出数据"),

    EXPORT_DATA_TWO_MUCH("EXPORT_DATA_TWO_MUCH", 593,"导出数据过多"),

    /** 系统错误 */
    SYSTEM_ERROR("SYSTEM_ERROR", 500,"系统错误"),

    EXPORT_DATA_ERROR("EXPORT_DATA_ERROR" ,592,"导出数据失败" );

    private String errorMsg;

    private String errorCode;

    private int    statusCode;

    private CommonResultCode(String errorCode,int statusCode,String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.statusCode = statusCode;
    }
    private CommonResultCode(int statusCode,String errorMsg) {
        this.errorMsg = errorMsg;
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
