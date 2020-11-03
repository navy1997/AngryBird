package com.birds.pojo;

/**
 * 错误信息
 */
public class CodeMsg {
	
	private int code;
	private String msg;
	
	public static final CodeMsg SUCCESS = new CodeMsg(0, "操作成功");
	
	public static final CodeMsg REGIST_SUCCESS = new CodeMsg(0, "注册成功，请到邮箱激活用户");

	public static final CodeMsg USERNOTEXISTS = new CodeMsg(200001, "用户名或密码错误");
	public static final CodeMsg USERNOTFIND = new CodeMsg(200002, "用户信息获取失败");
	public static final CodeMsg USERDELERROR = new CodeMsg(200003, "用户删除失败");
	public static final CodeMsg USERBASE = new CodeMsg(200004, "用户数据操作失败");

	public static final CodeMsg TOKENNOTEXISTS = new CodeMsg(300001, "请求头token不存在");
	public static final CodeMsg TOKENOVERTIME = new CodeMsg(300002, "token过期");
	public static final CodeMsg TOKENERROR = new CodeMsg(300003, "token校验错误，请重新登录");

	public static final CodeMsg DBERROR = new CodeMsg(400001, "服务繁忙，请稍后重试");

	public static final CodeMsg FILE_ERROR = new CodeMsg(600001,"文件未找到");


	public static final CodeMsg REUQESTTOMANY = new CodeMsg(700001,"请求太频繁啦，请60秒后再查询");
	
	public static final CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务端异常");



	
	//临期管理模块异常
	public CodeMsg(int code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
	
}
