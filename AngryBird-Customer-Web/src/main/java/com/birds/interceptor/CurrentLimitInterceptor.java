package com.birds.interceptor;

import com.birds.exception.GlobalException;
import com.birds.pojo.ClickLog;
import com.birds.pojo.CodeMsg;
import com.birds.service.ClickLogService;
import com.birds.utils.HeadersUtils;
import com.birds.utils.IPUtil;
import com.birds.utils.JsonUtils;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * IP限流拦截器
 */
@Component
public class CurrentLimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private HeadersUtils headersUtils;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${redisConfig.count}")
    private Integer REDISCOUNT;


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws SignatureException {

        // 解析ip
        Map<String, String> info = headersUtils.getHeadersInfo(request);
        String ip = info.get("x-forwarded-for");
        if(StringUtils.isEmpty(ip)){
            return true;
        }
        //查询ip在reids中是否超过了请求次数
        Object o = redisTemplate.opsForValue().get(ip);
        if(StringUtils.isEmpty(o)){
            //第一次访问，设置请求次数
            redisTemplate.opsForValue().set(ip,1,60, TimeUnit.SECONDS);
        }else{
            //连续访问,判断请求次数是否已经过了
            Integer count = (Integer)redisTemplate.opsForValue().get(ip);
            if(count>=REDISCOUNT){
                //抛出ip限流
                throw new GlobalException(CodeMsg.REUQESTTOMANY);
            }else{
                Long expire = redisTemplate.opsForValue().getOperations().getExpire(ip);
                redisTemplate.opsForValue().set(ip,count+1,expire, TimeUnit.SECONDS);
            }
        }
        return true;
    }
}