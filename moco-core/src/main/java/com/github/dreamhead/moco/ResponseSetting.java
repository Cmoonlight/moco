package com.github.dreamhead.moco;

import com.github.dreamhead.moco.handler.AndResponseHandler;
import com.github.dreamhead.moco.resource.Resource;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import static com.github.dreamhead.moco.Moco.*;
import static com.github.dreamhead.moco.handler.ResponseHandlers.responseHandler;
import static com.google.common.collect.Lists.newArrayList;

public abstract class ResponseSetting {
    protected ResponseHandler handler;

    public void response(String content) {
        this.response(text(content));
    }

    public void response(Resource resource) {
        this.response(responseHandler(resource));
    }

    public void response(ResponseHandler handler) {
        this.handler = handler;
    }

    public void response(ResponseHandler... handler) {
        this.response(new AndResponseHandler(newArrayList(handler)));
    }

    public void redirectTo(String url) {
        this.response(status(HttpResponseStatus.FOUND.getCode()), header("Location", url));
    }
}
