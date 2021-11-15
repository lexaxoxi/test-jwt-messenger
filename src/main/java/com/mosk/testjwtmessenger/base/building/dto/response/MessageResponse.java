package com.mosk.testjwtmessenger.base.building.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {

    private Long id;
    private String text;
    private String author;
}
