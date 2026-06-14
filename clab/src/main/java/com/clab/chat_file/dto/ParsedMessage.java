package com.clab.chat_file.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParsedMessage {
    private String sender;
    private LocalDateTime time;
    private String content;
}
