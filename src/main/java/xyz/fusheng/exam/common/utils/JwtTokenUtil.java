package xyz.fusheng.exam.common.utils; /**
 * @author: code-fusheng
 * @Date: 2020/9/30 15:30
 */

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import xyz.fusheng.exam.common.config.JwtConfig;
import xyz.fusheng.exam.security.entity.SelfUser;

import java.util.Date;

/**
 * @FileName: JwtTokenUtil
 * @Author: code-fusheng
 * @Date: 2020/9/30 15:30
 * @version: 1.0
 * Description: Jwt Token 工具类
 */

@Slf4j
public class JwtTokenUtil {

    /**
     * 私有化构造器
     */
    private JwtTokenUtil(){}

    public static String createAccessToken(SelfUser selfUser) {
        // 登录成功生成 Jwt
        String token = Jwts.builder()
                // 放入用户名和用户id
                .setId(selfUser.getUserId()+"")
                // 主体
                .setSubject(selfUser.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("zh")
                // 自定义属性，放入用户拥有的权限
                .claim("authorities", JSON.toJSONString(selfUser.getAuthorities()))
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + JwtConfig.expiration))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, JwtConfig.secret)
                .compact();
        return token;
    }

}
