package com.simbirsoft.integration.demo.controller;

import com.simbirsoft.integration.demo.storage.IncomeMessageStorage;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messages")
@RequiredArgsConstructor
@Slf4j
public class MessagesWebPageResource {

    private final IncomeMessageStorage incomeMessageStorage;

    @GetMapping()
    public String getMessages(Model model) {
        final Set<Map.Entry<LocalDateTime, Object>> entries = incomeMessageStorage.getMessages().entrySet();
        model.addAttribute("messageEntries", entries);

        return "messagesPage";

    }
}
