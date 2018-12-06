package org.inria.restlet.mta.internals;

import java.util.Date;

public class Tweet {
    private Date date;
    private String title;
    private String body;

    public Tweet(String title, String body, Date date) {
        this.title = title;
        this.body = body;
        this.date = date;
        //Test
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
