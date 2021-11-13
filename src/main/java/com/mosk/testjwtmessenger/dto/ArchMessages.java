package com.mosk.testjwtmessenger.dto;

import java.util.List;

public record ArchMessages(String name, List<MessageDTO> messages) {
}
