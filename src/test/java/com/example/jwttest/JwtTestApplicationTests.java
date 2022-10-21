package com.example.jwttest;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class JwtTestApplicationTests {

    private String secretKey="#$%HZ2205&*Y";
    // 使用jwt生成token值
    @Test
    void buildToken() {
        // header部分
        Map<String,Object> header=new HashMap<>();
        header.put("alg","HS256");
        header.put("typ","JWT");
        //数据负载的自定义部分
        Map<String,Object> body=new HashMap<>();
        body.put("username","zhangsan");
        body.put("realname","张三");

        String token=  Jwts.builder().setHeader(header)
                //负载公共部分
                .setId("user_0001")
                //设置主题
                .setSubject("user login info")
                //设置过期时间
                .setExpiration(new Date(new Date().getTime()+(1000*60*60)))
                //设置发行人
                .setIssuer("java2205")
                //负载私有数据添加
                .addClaims(body)
                //设置签名算法和密钥，密钥是自己定义的
                .signWith(SignatureAlgorithm.HS256,secretKey).compact();
        System.out.println(token);
    }

    //验签和解码jwt
    @Test
    public  void getJwt(){
        String jwt="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ1c2VyXzAwMDEiLCJzdWIiOiJ1c2VyIGxvZ2luIGluZm8iLCJleHAiOjE2NjU5OTM2MjQsImlzcyI6ImphdmEyMjA1IiwidXNlcm5hbWUiOiJ6aGFuZ3NhbiIsInJlYWxuYW1lIjoi5byg5LiJIn0.gE90VVKBtaBJAtjjRQPIiSpi7GIN-OhiIlOFJqs2L-E";
        try {
            Jws<Claims> claimsJwts= Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
            JwsHeader jwsHeader=  claimsJwts.getHeader();
            System.out.println(jwsHeader.getAlgorithm());
            System.out.println(jwsHeader.getType());
            Claims body= claimsJwts.getBody();
            System.out.println("读取负载公有数据");
            System.out.println(body.getId());
            System.out.println(body.getSubject());
            System.out.println(body.getIssuer());
            // 读取负载私有数据
            String username=  body.get("username",String.class);
            System.out.println("username:"+username);
            String realname=  body.get("realname",String.class);
            System.out.println("realname:"+realname);
        } catch (ExpiredJwtException e) {
            System.out.println("token过期");
        } catch (MalformedJwtException e) {
            System.out.println("token被篡改");
        } catch (SignatureException e) {
            System.out.println("签名密钥有误");
        } catch (Exception e) {
            System.out.println("token数据异常，请联系管理员");
        }
    }

}
