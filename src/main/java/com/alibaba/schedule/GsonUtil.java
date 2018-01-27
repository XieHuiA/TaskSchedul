package com.alibaba.schedule;

import com.alibaba.fastjson.JSON;

public class GsonUtil {
	
	public static String toJson(Object o) {
        return JSON.toJSONString(o);
    }

    public static <T> T fromJson(String s, Class<T> clazz) {
        return JSON.parseObject(s, clazz);
    }

}
