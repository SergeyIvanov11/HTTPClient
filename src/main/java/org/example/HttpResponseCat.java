package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HttpResponseCat {
    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final String upvotes;

    public HttpResponseCat(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") String upvotes
    ) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public String getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "HttpResponse about cats" +
                "\n id = " + id +
                "\n text = " + text +
                "\n user = " + user +
                "\n upvotes = " + upvotes + "\n";
    }
}
