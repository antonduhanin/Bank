package dev5.duhanin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.decimal4j.util.DoubleRounder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TransactionDTO {
    @ApiModelProperty(hidden = true, readOnly = true)
    private long id;
    @NotNull(message = "error.idCard.notnull")
    @Min(1)
    private long idCard;
    @ApiModelProperty(hidden = true, readOnly = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    @NotNull
    @Min(0)
    private float summa;
    @ApiModelProperty(hidden = true, readOnly = true)
    private String title;
    @ApiModelProperty(hidden = true,readOnly = true)
    private String nameRecipient;
    @NotNull(message = "error.recipientNumber.notnull")
    @Min(1)
    private long recipientNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCard() {
        return idCard;
    }

    public void setIdCard(long idCard) {
        this.idCard = idCard;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public float getSumma() {
        return summa;
    }

    public void setSumma(float summa) {
        summa = (float) DoubleRounder.round(summa,2);
        this.summa = summa;
    }

    public long getRecipientNumber() {
        return recipientNumber;
    }

    public void setRecipientNumber(long recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    public String getNameRecipient() {
        return nameRecipient;
    }

    public void setNameRecipient(String nameRecipient) {
        this.nameRecipient = nameRecipient;
    }
}
