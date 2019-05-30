package com.jack.util;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Random;

//生成密码
public class PasswordUtil {
    private final static String[] word = {
        "a","b","c","e","f","g","h","j","p"
            ,"q","r","s","t","u","v","w","x","y","z"
            ,"A","B","C","E","F","G","H","J","P"
            ,"Q","R","S","T","U","V","W","X","Y","Z"
    };
    public final static String[] num = {
        "2","3","4","5","6","7","8","9"
    };

    public static String randomPassword() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(new Date().getTime());
        boolean flag = false;
        //8~10
        int length = random.nextInt(3) + 8;
        for (int i = 0; i < length; i++) {
            if (flag) {
                stringBuffer.append(num[random.nextInt(num.length)]);
            } else {
                stringBuffer.append(word[random.nextInt(word.length)]);
            }
            flag = !flag;
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(randomPassword());
        System.out.println(randomPassword());
        System.out.println(randomPassword());
    }
}
