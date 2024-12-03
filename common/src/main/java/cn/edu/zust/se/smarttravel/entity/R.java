package cn.edu.zust.se.smarttravel.entity;

import lombok.Data;

/**
 * 统一返回结果
 * @param <T>
 */
@Data
public class R<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    public static <T> R<T> success(int code,String msg,T data){
        R<T> r=new R<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> R<T> success() {
        return R.success(1,"success",null);
    }
    public static <T> R<T> success(String msg) {
        return R.success(1,msg,null);
    }
    public static <T> R<T> success(T data) {
        return R.success(1,"success",data);
    }

    public static <T> R<T> success(String msg,T data){
        return R.success(1,msg,data);
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = 0;
        return r;
    }

}
