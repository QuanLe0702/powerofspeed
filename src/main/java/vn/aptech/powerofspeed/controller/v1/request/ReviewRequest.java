package vn.aptech.powerofspeed.controller.v1.request;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
public class ReviewRequest {
    
    private String content;

    private float rating;
}
