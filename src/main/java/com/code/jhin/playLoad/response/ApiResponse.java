package com.code.jhin.playLoad.response;

public class ApiResponse {
    private Boolean success;
    private String message;
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, String message , Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
