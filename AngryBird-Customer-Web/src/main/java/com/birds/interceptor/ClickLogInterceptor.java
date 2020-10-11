package com.birds.interceptor;

import com.birds.configs.JwtConfig;
import com.birds.pojo.ClickLog;
import com.birds.service.ClickLogService;
import com.birds.utils.HeadersUtils;
import com.birds.utils.IPUtil;
import com.birds.utils.JsonUtils;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class ClickLogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ClickLogService clickLogService;
    @Autowired
    private HeadersUtils headersUtils;


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws SignatureException {

         //当Access-Control-Allow-Credentials设为true的时候，Access-Control-Allow-Origin不能设为星号
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 允许指定域访问跨域资源
        //response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:9006, http://127.0.0.1:8080");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));// *
        // 允许浏览器发送的请求消息头
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));// *
        // 允许浏览器在预检请求成功之后发送的实际请求方法名
        response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
        // 设置响应数据格式
        response.setHeader("Content-Type", "application/json");

        //解析此次点击的请求数据
        Map<String, String> info = headersUtils.getHeadersInfo(request);
        String ip = info.get("x-forwarded-for");
        String cityInfo = null;
        //获取城市信息
        try {
            if(!StringUtils.isEmpty(ip)){
                cityInfo = IPUtil.getCityInfo(ip);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //封装对象
        ClickLog clickLog = new ClickLog();
        clickLog.setIp(ip);
        clickLog.setCity(cityInfo);
        clickLog.setHeader(JsonUtils.objectToJson(info));
        //记录本次点击日志
        clickLogService.insertClickLog(clickLog);
        return true;
    }
}