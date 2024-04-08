package com.erick.technicaltest.springbootcrudspaceships.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// This class will be used to send the response to the client
@Data
@NoArgsConstructor
public class ApiResponse {

        private String message;
        private Date date = new Date();
        private String url;

        public ApiResponse(String message, String url) {
            this.message = message;
            this.url = url.replace("uri=", "");
        }

}
