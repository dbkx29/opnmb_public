package icu.dbkx.opnmb.common.interceptor;

import icu.dbkx.opnmb.common.exception.HttpErrorEnum;
import icu.dbkx.opnmb.common.utils.JwtUtil;
import icu.dbkx.opnmb.common.utils.RequestContext;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String token = getTokenFromHeader(request);
        log.trace("拦截到了：{} 请求", uri);
        if(token==null) {//没有token就给椰爬
            response.sendError(HttpErrorEnum.TOKEN_NOT_FOUND.getHttpCode(), HttpErrorEnum.TOKEN_NOT_FOUND.getMsg());
            return false;
        }else{//有token就验证token
            if(!JwtUtil.validateToken(token)){
                log.trace("token验证失败");
                response.sendRedirect("/view/login");
                response.sendError(HttpErrorEnum.TOKEN_INVALID.getHttpCode(), HttpErrorEnum.TOKEN_INVALID.getMsg());
                return false;
            }

            Claims claims = JwtUtil.getClaimsFromToken(token);
            int user_id = (int) claims.get("user_id");
            RequestContext.setContext(user_id);
            log.trace("token验证成功,用户id:{}",RequestContext.getContext());
        }
        return true;
    }

    /**
     * 从header中获取token
     */
    private String getTokenFromHeader(HttpServletRequest request) {
        return request.getHeader(HEADER_AUTHORIZATION);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContext.removeContext();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

}
