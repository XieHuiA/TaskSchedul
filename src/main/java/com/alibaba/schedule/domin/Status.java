package com.alibaba.schedule.domin;

import com.alibaba.schedule.mapper.EnumType;

/**
 * Created by muming on 16/5/31.
 */
public enum Status implements EnumType {
    STOPPED(0),
    RUNNING(1),
    RESTARTING(2);

    private int val;

    Status(int val) {
        this.val = val;
    }

    @Override
    public int value() {
        return val;
    }
}
