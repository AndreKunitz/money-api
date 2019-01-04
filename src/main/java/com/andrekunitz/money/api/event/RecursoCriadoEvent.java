package com.andrekunitz.money.api.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

    private HttpServletResponse response;
    private long codigo;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, long codigo) {
        super(source);
        this.response = response;
        this.codigo = codigo;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public long getCodigo() {
        return codigo;
    }
}
