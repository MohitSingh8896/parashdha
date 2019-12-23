package project.namramuni.ViewModels.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import project.namramuni.ViewModels.List.UserList;

public class UserResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("current_otp")
    @Expose
    private String current_otp;

    @SerializedName("is_register")
    @Expose
    private Boolean isRegister;

    @SerializedName("user_status")
    @Expose
    private String userStatus;

    @SerializedName("data")
    @Expose
    @Nullable
    private UserList data;

    public Boolean getRegister() {
        return isRegister;
    }

    public void setRegister(Boolean register) {
        isRegister = register;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getCurrent_otp() {
        return current_otp;
    }

    public void setCurrent_otp(String current_otp) {
        this.current_otp = current_otp;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Nullable
    public UserList getData() {
        return data;
    }

    public void setData(@Nullable UserList data) {
        this.data = data;
    }
}
