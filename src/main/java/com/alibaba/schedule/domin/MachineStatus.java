package com.alibaba.schedule.domin;

import com.alibaba.schedule.mapper.EnumType;

/**
 * Created by muming on 16/5/24.
 */
public enum MachineStatus implements EnumType {
    OFF_LINE(0),
    ON_LINE(1);

    private int val;

    MachineStatus(int val) {
        this.val = val;
    }

    @Override
    public int value() {
        return val;
    }
}
