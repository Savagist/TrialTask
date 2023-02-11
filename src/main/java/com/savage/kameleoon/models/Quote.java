package com.savage.kameleoon.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONTENT")
    @NotEmpty(message = "Quote should not be empty")
    private String content;

    @Column(name = "CREATION_DATE")
    private LocalDateTime createTime;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateTime;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "AUTHOR_ID")
    private Client author;

    @JoinColumn(name = "VOTES_ID")
    @OneToOne(cascade = CascadeType.ALL)
    private Vote votes;

    public Quote() {
    }

    public Quote(String content, LocalDateTime createTime, LocalDateTime updateTime, Client author, Vote votes) {
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.author = author;
        this.votes = votes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
