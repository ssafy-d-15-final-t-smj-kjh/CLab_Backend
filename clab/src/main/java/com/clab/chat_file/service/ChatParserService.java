package com.clab.chat_file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.clab.chat_file.dto.ParsedMessage;

public interface ChatParserService {
	List<ParsedMessage> parseChatLog(MultipartFile file);

	List<String> extractParticipants(List<ParsedMessage> messages);
}
