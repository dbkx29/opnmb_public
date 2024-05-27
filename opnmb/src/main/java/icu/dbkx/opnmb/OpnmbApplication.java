package icu.dbkx.opnmb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("icu.dbkx.opnmb.generator.mapper")
public class OpnmbApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpnmbApplication.class, args);
    }

}
