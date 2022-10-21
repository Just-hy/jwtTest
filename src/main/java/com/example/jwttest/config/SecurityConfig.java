package com.example.jwttest.config;

import com.example.jwttest.pojo.Permission;
import com.example.jwttest.service.PermissionService;
import com.example.jwttest.utils.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/14
 * @Description:
 * @Version:  1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtFilter jwtFilter;//JWT过滤器
    @Autowired
    private PermissionService permissionService;
    public SecurityConfig(
            AutowireCapableBeanFactory autowireCapableBeanFactory,
            JwtFilter jwtFilter) {
        // jwt过滤器
        this.jwtFilter = jwtFilter;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 配置哪些请求需要认证，哪些请求可以直接放过
        //  指明哪个路径需要什么权限才能访问
        List<Permission> permissions=permissionService.getAllPermissions();
        for (Permission permission:permissions
        ) {
            http.authorizeRequests()
                    //具备 student_update 权限 才能/student/update 的资源。
                    .antMatchers(permission.getPerurl())
                    .hasAuthority(permission.getPercode());
        }
        //http.authorizeRequests().antMatchers("/dologin").permitAll();

        // 禁用 spring security 默认的表单模式
        http .formLogin().disable()
                //添加 Jwt的过滤器
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        ObjectMapper objectMapper=new ObjectMapper();
                        String json=  objectMapper.writeValueAsString(JsonResult.error().message("权限不足").code(403));
                        response.getWriter().print(json);
                        System.out.println("403");
                    }
                });
    }

}