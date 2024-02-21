package com.backend.velanmai.Common;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    private int status;
    private Object data;
    private Object data1;
    private Object error;

    public Object getData1() {
        return data1;
    }

    public void setData1(Object data1) {
        this.data1 = data1;
    }

    public ApiResponse() {
        this.status = HttpStatus.OK.value();
        this.data = data;
        this.data1=data1;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
