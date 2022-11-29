package com.example.trabalhoiago.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;

    private long id;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public long getId() {
        return id;
    }
}
