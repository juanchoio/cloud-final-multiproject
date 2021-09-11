package co.com.poli.commons.utils;

import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.*;

@Component
public class ResponseBuilder {

    //supongamos que todo esta bien
    public Response success(){
        return Response.builder()
                .data(OK)
                .status(OK.value())
                .build();
    }

    public Response success(Object data){
        return Response.builder()
                .data(data)
                .status(OK.value())
                .build();
    }

    public Response successCreated(Object data){
        return Response.builder()
                .data(data)
                .status(CREATED.value())
                .build();
    }

    public Response successDeleted(Object data){
        return Response.builder()
                .data(data)
                .status(NO_CONTENT.value())
                .build();
    }

    public Response failed(Object data){
        return Response.builder()
                .data(data)
                .status(INTERNAL_SERVER_ERROR.value())
                .build();
    }

    public Response failedNotFound(){
        return Response.builder()
                .data(NOT_FOUND)
                .status(NOT_FOUND.value())
                .build();
    }

}
