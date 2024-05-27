package icu.dbkx.opnmb.common.properties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix = "opchat.jwt")
@Data
public class JwtProperties {
    /**
     * 生成jwt令牌相关配置
     */
    public static String secretKey = "T3BjaGF0X0lzX1RIRV9CRVNUX+iBiuWkqV/ova/ku7ZfZXZlcg==";

    public static long ttl = 720000;

    public static String tokenName = "token";
}
