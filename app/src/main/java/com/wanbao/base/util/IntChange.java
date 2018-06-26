package com.wanbao.base.util;

/**
 * Created by liuzhigang on 2018/6/26/026.
 *
 * @author LiuZG
 */

public class IntChange {
    public static String iChange(int i) {
        if (i < 10) {
            return "0" + i;
        } else {
            return String.valueOf(i);
        }
    }
}
