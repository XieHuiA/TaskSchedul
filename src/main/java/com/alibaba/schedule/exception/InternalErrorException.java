package com.alibaba.schedule.exception;

import com.alibaba.schedule.conf.Config;

/**
 * Created by muming on 16/6/24.
 */
public class InternalErrorException extends ShrekException {
    public InternalErrorException(String errorMessage) {
        super(Config.ERROR_CODE_INTERNAL_ERROR, errorMessage);
    }

    public InternalErrorException(String errorMessage, Throwable cause) {
        super(Config.ERROR_CODE_INTERNAL_ERROR, errorMessage, cause);
    }
}
