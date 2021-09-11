package co.com.poli.commons.utils;


import lombok.Builder;
import lombok.Data;

@Data//usamos Data porque no es una clase de persistencia
@Builder
public class Response {
    private Object data;
    private Integer status;
}
