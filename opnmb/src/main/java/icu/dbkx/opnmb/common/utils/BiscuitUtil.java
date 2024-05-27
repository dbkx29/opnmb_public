package icu.dbkx.opnmb.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BiscuitUtil {
    @NotNull
    public static String generateBiscuitId(){
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 8; i++){
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }
}
