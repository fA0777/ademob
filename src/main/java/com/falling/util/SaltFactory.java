package com.falling.util;

import java.util.Random;

public class SaltFactory {
    public static String getSalt(){
        Random random=new Random();
        String salt="";
        for (int i = 0; i < 7; i++) {
            int r = random.nextInt(62);//0-9 10-35 大写字母 36-61 小写字母
            if (r>=0&&r<=9){
                salt+=r;
            }else if (r>=10&&r<=35){
                char r1= (char) (r+55);
                salt+=r1;
            }else{
                char r2= (char) (r+61);
                salt+=r2;
            }
        }
        return salt;
    }
}
