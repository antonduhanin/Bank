package dev5.duhanin.dto;

import io.swagger.annotations.ApiModelProperty;

public class AccountDTO {
    @ApiModelProperty(hidden = true, readOnly = true)
    private long id;
    @ApiModelProperty(hidden = true, readOnly = true)
    private float balance;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
