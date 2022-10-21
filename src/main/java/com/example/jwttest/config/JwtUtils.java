package com.example.jwttest.config;

import com.example.jwttest.pojo.Userinfo;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/17
 * @Description:
 * @Version:  1.0
 */
public class JwtUtils {
    //密钥
    private static String signkey = "#$%HZ2205&*Y";

    /**
     * 生成tokena
     *
     * @param map           负载的个性化数据
     * @param expireMinutes 过期分钟
     * @return
     */
    public static String createToken(Map<String, Object> map, int expireMinutes) {
        JwtBuilder jwtBuilder = Jwts.builder();
        // 设置负载私有数据
        jwtBuilder.addClaims(map);

        // Calendar calendar=Calendar.getInstance();
        //  calendar.add(Calendar.MINUTE,expireMinutes);
        //设置过期时间
        // jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setExpiration(new Date(new Date().getTime() + (1000 * 60 * expireMinutes)));

        //签名
        jwtBuilder.signWith(SignatureAlgorithm.HS256, signkey);
        return jwtBuilder.compact();
    }

    /**
     * token验证
     *
     * @param token token值
     * @return 调用者要处理异常，只要发生异常代表认证失败
     */
    public static Claims verifyToken(String token) {
        return Jwts.parser().setSigningKey(signkey).parseClaimsJws(token).getBody();
    }

    public static void main(String[] args) {
        Map<String, Object> body = new HashMap<>();
        body.put("username", "zhangsan");
        body.put("realname", "张三");
        Userinfo user = new Userinfo(1, "xiaoli", "李莉莉","");
        body.put("login_user", user);
        int minutes = 20;
        String token = createToken(body, minutes);
        System.out.println(token);
        Claims claims = null;
        try {
            claims = verifyToken(token);
            String username = claims.get("username", String.class);
            System.out.println(username);
            String realname = claims.get("realname", String.class);
            System.out.println(realname);
            Map<String, Object> userinfoMap = claims.get("login_user", HashMap.class);
            for (Object obj : userinfoMap.values()
            ) {
                System.out.println(obj);
            }

        } catch (ExpiredJwtException e) {
            System.out.println("token过期");
        } catch (MalformedJwtException e) {
            System.out.println("token被篡改");
        } catch (SignatureException e) {
            System.out.println("签名密钥有误");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("token数据异常，请联系管理员");
        }


    }
}