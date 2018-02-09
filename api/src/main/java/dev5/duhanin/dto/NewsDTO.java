package dev5.duhanin.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class NewsDTO {
    @ApiModelProperty(hidden = true, readOnly = true)
    private long id;
    @NotNull(message = "error.title.notnull")
    private String title;
    @NotNull(message = "error.content.notnull")
    private String content;
    @ApiModelProperty(hidden = true, readOnly = true)
    private long recipientNews;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecipientNews() {
        return recipientNews;
    }

    public void setRecipientNews(long recipientNews) {
        this.recipientNews = recipientNews;
    }
}
