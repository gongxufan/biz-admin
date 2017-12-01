package gxf.dev.biz.admin.dto;

/**
 * @author gongxufan
 * @date 17-11-15
 **/
public class ResultDto<T> {
    private int code;
    private String message;
    private T data;
    private long count;


    static public ResultDto ok() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(1000);
        resultDto.setMessage("操作成功");
        return resultDto;
    }

    static public ResultDto ok(String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(1000);
        resultDto.setMessage(message);
        return resultDto;
    }

    static public ResultDto fail() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(10001);
        resultDto.setMessage("操作失败");
        return resultDto;
    }

    static public ResultDto fail(String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(1001);
        resultDto.setMessage(message);
        return resultDto;
    }

    static public ResultDto timeOut(String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(1002);
        resultDto.setMessage(message);
        return resultDto;
    }

    static public ResultDto timeOut() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(1002);
        resultDto.setMessage("timeout");
        return resultDto;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
