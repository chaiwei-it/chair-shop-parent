package com.mood.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 内容
 *
 * @author chaiwei
 * @time 2018-06-03 21:47
 */
public class IdUtil {

    public static String[] chars = new String[] {  "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9" };

    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x0A]);
        }
        return shortBuffer.toString();

    }

    public static String getCode(Integer digit) {
        Integer num = 1;
        double code = Math.random()*9+1;
        while(num < digit){
            code *= 10;
            num++;
        }
        return (int)code + "";
    }

    public static String generateCode(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String formatStr =formatter.format(new Date());
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x0A]);
        }
        return formatStr + shortBuffer.toString();
    }



//    public static void main(String[] args) {
//        System.out.println(getCode(1));
//    }

}
