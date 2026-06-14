package com.clab.chatFile.service;

import org.springframework.web.multipart.MultipartFile;

public interface ChatFileService {
	String getFullPath(String fileName);
	int storeFile(MultipartFile file);
	int delete(int id);
}
