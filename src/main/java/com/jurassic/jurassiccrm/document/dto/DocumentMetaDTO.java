package com.jurassic.jurassiccrm.document.dto;

import com.jurassic.jurassiccrm.document.entity.Document;
import com.jurassic.jurassiccrm.document.repository.DocumentMeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.unit.DataSize;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentMetaDTO {

    private Long id;

    private String name;

    private String contentType;

    private String description;

    private String author;

    private String size;

    public static DocumentMetaDTO buildFromMeta(DocumentMeta documentMeta) {
        return new DocumentMetaDTO(
                documentMeta.getId(), documentMeta.getName(),
                documentMeta.getContentType(), documentMeta.getDescription(),
                documentMeta.getAuthor(), humanReadableSizeInKB(documentMeta.getSize())
        );
    }

    public static DocumentMetaDTO buildFromDocument(Document document) {
        return new DocumentMetaDTO(
                document.getId(), document.getName(),
                document.getContentType(), document.getDescription(),
                document.getAuthor(), humanReadableSizeInKB(document.getSize())
        );
    }

    private static String humanReadableSizeInKB(long size) {
        return String.format("%sKB", DataSize.ofBytes(size).toKilobytes());
    }

}
