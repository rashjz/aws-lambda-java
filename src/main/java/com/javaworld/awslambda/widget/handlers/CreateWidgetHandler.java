package com.javaworld.awslambda.widget.handlers;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.javaworld.awslambda.widget.model.Widget;

public class CreateWidgetHandler implements RequestHandler<Widget, Widget>  {
    public Widget handleRequest(Widget widget, Context context) {
        System.out.println("Create new widget: " + widget.getName());
        widget.setId("1");
        return widget;
    }
}
