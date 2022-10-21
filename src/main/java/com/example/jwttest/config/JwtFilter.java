package com.example.jwttest.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/18
 * @Description:
 * @Version:  1.0
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();
        uri = request.getRequestURI();
        System.out.println(uri);
        if(uri.startsWith("/dologin")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("token");  // 获取请求头的认证信息
        if(null != token &&  !"".equals(token)) {
            // 解析Jwt信息
            try{
                Claims claims = JwtUtils.verifyToken(token);
                // 获取用户权限
                String authorities = claims.get("authorities", String.class);
                //获得用户名
                String username=claims.get("username",String.class);
                // Spring Security 的上下文中存储的是 UsernamePasswordAuthenticationToken对象
                // 第一个参数是用户名，第二个参数是密码，不需要给，第三个参数是权限
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                // 设置当前usernamePasswordAuthenticationToken的一些信息，这些信息会从当前request中抽取出来
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
                // 将usernamePasswordAuthenticationToken设置到 Security的上下文当中。
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(request, response);
            }
            catch (ExpiredJwtException e){//篡改了
                response.setStatus(401);
            }catch (UnsupportedJwtException e){ //过期了
                response.setStatus(560);
            }catch (Exception e){
                e.printStackTrace();
                response.setStatus(500);// 未知异常
            }
        }else {
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
        }
    }


}