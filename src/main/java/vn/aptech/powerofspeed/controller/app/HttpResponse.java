package vn.aptech.powerofspeed.controller.app;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HttpResponse {
    protected String timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String message;
    protected String DeveloperMessage;
    protected String path;
    protected String requestMethod;
    protected Map<?,?> data;
}
