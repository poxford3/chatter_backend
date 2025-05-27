package com.poxford3dev.chatter_backend.Payload.Response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private String message;
    private Integer id;
}
