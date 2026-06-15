package com.clab.chat_file.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clab.chat_file.dto.ParsedMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service // Spring Bean으로 등록
public class ChatParserServiceImpl implements ChatParserService {

    private static final Pattern MESSAGE_PATTERN =
            Pattern.compile("^\\[(.+?)\\]\\s*\\[(오전|오후)\\s*(\\d{1,2}:\\d{2})\\]\\s*(.+)$");

    private static final Pattern DATE_PATTERN =
            Pattern.compile("^-+\\s*(.+?)\\s*-+$");
    
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy년 M월 d일 EEEE", Locale.KOREAN);

    /**
     * MultipartFile을 받아서 한 줄씩 읽으며 ParsedMessage 리스트로 파싱합니다.
     */
    public List<ParsedMessage> parseChatLog(MultipartFile file) {
        List<ParsedMessage> parsedMessages = new ArrayList<>();
        LocalDate currentDate = null;

        // 💡 MultipartFile에서 InputStream을 열고, BufferedReader로 한 줄씩 읽기 (try-with-resources로 자원 자동 반환)
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            while (
            		(line = reader.readLine()) != null) {
                // 빈 줄 건너뛰기
                if (line.trim().isEmpty()) {
                    continue;
                }

                // 1. 날짜 구분선 패턴 매칭
                Matcher dateMatcher = DATE_PATTERN.matcher(line);
                if (dateMatcher.matches()) {
                    try {
                        String dateStr = dateMatcher.group(1).trim();
                        currentDate = LocalDate.parse(dateStr, DATE_FORMATTER);
                    } catch (Exception e) {
                        log.error("날짜 파싱 실패 라인: {}", line, e);
                    }
                    continue;
                }

                // 2. 일반 메시지 패턴 매칭
                Matcher msgMatcher = MESSAGE_PATTERN.matcher(line);
                if (msgMatcher.matches()) {
                    if (currentDate == null) {
                        log.warn("상단에 기준 날짜 정보가 없어 메시지를 스킵합니다: {}", line);
                        continue;
                    }

                    String sender = msgMatcher.group(1);
                    String amPm = msgMatcher.group(2);
                    String timeStr = msgMatcher.group(3);
                    String content = msgMatcher.group(4);

                    LocalTime localTime = parseLocalTime(amPm, timeStr);
                    LocalDateTime dateTime = LocalDateTime.of(currentDate, localTime);

                    parsedMessages.add(new ParsedMessage(sender, dateTime, content));
                } else {
                    // 3. 패턴에 맞지 않는 경우 (줄바꿈된 멀티라인 메시지 처리)
                    if (!parsedMessages.isEmpty()) {
                        ParsedMessage lastMessage = parsedMessages.get(parsedMessages.size() - 1);
                        lastMessage.setContent(lastMessage.getContent() + "\n" + line);
                    }
                }
            }

        } catch (Exception e) {
            log.error("채팅 로그 파일 처리 중 에러 발생", e);
            throw new RuntimeException("파일 파싱 중 오류가 발생했습니다.", e);
        }

        return parsedMessages;
    }
    
    // 참여자 이름 목록 (순서 유지, 중복 제거)
    @Override
    public List<String> extractParticipants(List<ParsedMessage> messages) {
        return messages.stream()
            .map(ParsedMessage::getSender)
            .distinct()
            .collect(Collectors.toList());
    }

    private LocalTime parseLocalTime(String amPm, String timeStr) {
        String[] timeParts = timeStr.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        if ("오후".equals(amPm) && hour != 12) {
            hour += 12;
        } else if ("오전".equals(amPm) && hour == 12) {
            hour = 0;
        }

        return LocalTime.of(hour, minute);
    }
}