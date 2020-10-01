package com.birds.rest;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @description rest工具
 * @author 冯海军
 * @date 2020/09/12
 *
 */
public class Result<T> 
{
	// 响应码
	private int code;
	// 信息
	private String msg;
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private T data;
	
	/**
	 * 返回成功时调用
	 * @param data
	 * @return
	 */
	public static <T> Result<T> success(T data)
	{
		return new Result<T>(data);
	}
	
	/**
	 * 返回失败时调用
	 * @param data
	 * @return
	 */
	public static <T> Result<T> error(CodeMsg cm)
	{
		return new Result<T>(cm);
	}
	
	private Result(T data){
		this.code = 0;
		this.msg = "操作成功";
		this.data = data;
	}
	
	private Result(CodeMsg cm){
		if(cm == null)
		{
			return;
		}
		this.code = cm.getCode();
		this.msg = cm.getMsg();
	}
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}

