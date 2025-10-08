package com.example.server.interceptor;

import Utils.JwtUtil;
import constant.JwtClaimsConstant;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import properties.JwtProperties;

/**
 * jwt interceptor
 */
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * testify jwt token
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1、get the token from the header
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、testify the token
        try {
            log.info("JWT token:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            log.info("current user id：", userId);
            //3、succeed, then pass
            return true;
        } catch (Exception ex) {
            //4、failed, then response 401 status code
            log.info("JWT token verify failed:{}", ex.getMessage());
            response.setStatus(401);
            return false;
        }
    }
}
