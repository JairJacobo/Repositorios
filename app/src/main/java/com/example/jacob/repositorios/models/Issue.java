package com.example.jacob.repositorios.models;

import java.util.List;

public class Issue {
    private String url;
    private String repository_url;
    private String labels_url;
    private String comments_url;
    private String events_url;
    private String html_url;
    private int id;
    private int number;
    private String title;
    private User user;
    private List<Label> labels;
    private String state;
    private boolean locked;
    private Object assignee;
    private List<Object> assignees;
    private Object milestone;
    private int comments;
    private String created_at;
    private String updated_at;
    private Object closed_at;
    private String author_association;
    private String body;
    private Pull_request pull_request;

    public String getUrl() {
        return url;
    }

    public String getRepository_url() {
        return repository_url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public String getState() {
        return state;
    }

    public boolean isLocked() {
        return locked;
    }

    public Object getAssignee() {
        return assignee;
    }

    public List<Object> getAssignees() {
        return assignees;
    }

    public Object getMilestone() {
        return milestone;
    }

    public int getComments() {
        return comments;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public Object getClosed_at() {
        return closed_at;
    }

    public String getAuthor_association() {
        return author_association;
    }

    public String getBody() {
        return body;
    }

    public Pull_request getPull_request() {
        return pull_request;
    }
}
