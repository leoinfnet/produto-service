package br.com.acme.produtoservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class ResponsePayload {
    private String message;
    private LocalDateTime dateTime;
}
