package com.fengye.springmvc.views;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
@Component("helloViews")
public class HelloViews implements View {
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().println("hello view,time:"+new Date());

    }
}
