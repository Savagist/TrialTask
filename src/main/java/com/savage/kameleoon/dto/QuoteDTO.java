package com.savage.kameleoon.dto;
import com.savage.kameleoon.models.Client;
import com.savage.kameleoon.models.Vote;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public class QuoteDTO {

    @NotEmpty(message = "Quote should not be empty")
    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Client author;

    private Vote votes;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Client getAuthor() {
        return author;
    }

    public void setAuthor(Client author) {
        this.author = author;
    }

    public Vote getVotes() {
        return votes;
    }

    public void setVotes(Vote votes) {
        this.votes = votes;
    }
}
