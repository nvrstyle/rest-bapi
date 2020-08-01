package ru.lubich.bapi.controller.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.lubich.bapi.view.DataView;
import ru.lubich.bapi.view.ErrorView;
import ru.lubich.bapi.view.ResultView;

@RestControllerAdvice
public class DataResponse implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object returnView, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(returnView == null){
            return new ResultView("success");
        }
        else if((returnView instanceof ErrorView)){
            return returnView;
        }
        else {
            return new DataView(returnView);
        }
        //if (methodParameter.getParameterType() != void.class) {
        //    DataView dataView = new DataView(returnView);
        //    return dataView;
        //}
        //SuccessView successView = new SuccessView("success");
        //return successView;
    }
}
