package cn.nosum.common.dto;

import cn.nosum.common.enums.ResultEnum;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class ResultInfo implements Serializable {

    private static final long serialVersionUID = 1L;
	/**
     * 结果码
     */
	private int code;

	/**
     * 结果详情
     */
	private String msg;

	/**
	 * 具体接口的业务返回信息，由具体接口义务定义 返回的结果包装在data中，data可以是单个对象
	 */
	private Object data;
	
	
	/**
	 * error: 错误信息类型，JOSN对象,如果发生异常，这里放的是异常信息, errorCode：错误编码 errorSn：错误编号
	 * errorMsg：错误信息 errorCause：错误信息 errorAction：错误接口名
	 */
	private JSONObject error;


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}



	public JSONObject getError() {
		return error;
	}

	public void setError(JSONObject error) {
		this.error = error;
	}

	public ResultInfo() {

	}
	
	public ResultInfo(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public ResultInfo(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResultInfo(Object data) {
		this.code = ResultEnum.SUCCESS.getCode();
		this.msg = ResultEnum.SUCCESS.getMsg();
		this.data = data;
	}
	 
	public static ResultInfo valueOf(Object data) {
		return new ResultInfo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
	}
	public static ResultInfo valueOf(ResultEnum resultEnum) {
		return new ResultInfo(resultEnum.getCode(), resultEnum.getMsg());
	}

	@Override
    public String toString() {
        return "ResultInfo [code=" + code
        		+ ", msg=" + msg
        		+ ", data=" + data;
    }
}