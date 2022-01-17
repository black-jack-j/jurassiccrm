package com.jurassic.jurassiccrm.document.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DocumentTO {
    private DocumentType type;
    private Map<String, String> fields = new HashMap<>();
}
