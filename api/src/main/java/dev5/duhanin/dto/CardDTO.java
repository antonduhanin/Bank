package dev5.duhanin.dto;

import io.swagger.annotations.ApiModelProperty;
import org.decimal4j.util.DoubleRounder;

import javax.validation.constraints.NotNull;

public class CardDTO {
    @ApiModelProperty(hidden = true, readOnly = true)
    private long id;
    @ApiModelProperty(hidden = true, readOnly = true)
    private float balance;
    @NotNull
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
        balance = (float) DoubleRounder.round(balance,2);
        this.balance = balance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
