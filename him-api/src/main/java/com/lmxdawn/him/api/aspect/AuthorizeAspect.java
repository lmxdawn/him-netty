package com.lmxdawn.him.api.aspect;

import com.lmxdawn.him.api.utils.JwtUtils;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.api.exception.JsonException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录验证 AOP
 */
@Aspect
@Component
@Slf4j
public class AuthorizeAspect {


    @Pointcut("@annotation(com.lmxdawn.him.api.annotation.CheckLoginAnnotation)")
    public void checkLoginVerify() {
    }

    /**
     * 登录验证
     * @param
     */
    @Before("checkLoginVerify()")
    public void doLoginVerify() {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new JsonException(ResultEnum.NOT_NETWORK);
        }
        HttpServletRequest request = attributes.getRequest();

        try {
            Long uid = Long.valueOf(request.getParameter("uid"));


            String token = request.getParameter("token");
            if (token == null) {
                throw new JsonException(ResultEnum.LOGIN_VERIFY_FALL);
            }

            // 验证 token
            Claims claims = JwtUtils.parse(token);
            if (claims == null) {
                throw new JsonException(ResultEnum.LOGIN_VERIFY_FALL);
            }
            Long jwtUid = Long.valueOf(claims.get("uid").toString());
            if (uid.compareTo(jwtUid) != 0) {
                throw new JsonException(ResultEnum.LOGIN_VERIFY_FALL);
            }

        }catch (Exception e) {
            throw new JsonException(ResultEnum.LOGIN_VERIFY_FALL);
        }

    }
    
}
