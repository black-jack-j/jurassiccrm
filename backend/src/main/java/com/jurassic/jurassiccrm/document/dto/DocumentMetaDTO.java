package com.jurassic.jurassiccrm.document.dto;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.document.entity.Document;
import com.jurassic.jurassiccrm.document.repository.DocumentMeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.unit.DataSize;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentMetaDTO {

    private Long id;

    private String name;

    private String docType;

    private String contentType;

    private String description;

    private User author;

    private String size;

    private Timestamp created;

    private Timestamp lastUpdate;

    public static DocumentMetaDTO buildFromMeta(DocumentMeta documentMeta) {
        return new DocumentMetaDTO(
                documentMeta.getId(), documentMeta.getName(), documentMeta.getType(),
                documentMeta.getContentType(), documentMeta.getDescription(),
                documentMeta.getAuthor(), humanReadableSizeInKB(documentMeta.getSize()),
                documentMeta.getCreated(), documentMeta.getLastUpdate()
        );
    }

//    public static DocumentMetaDTO buildFromDocument(Document document) {
//        return new DocumentMetaDTO(
//                document.getId(), document.getName(), document.getType(),
//                document.getContentType(), document.getDescription(),
//                document.getAuthor(), humanReadableSizeInKB(document.getSize()),
//                document.getCreated(), document.getLastUpdate()
//        );
//    }

    private static String humanReadableSizeInKB(long size) {
        return String.format("%sKB", DataSize.ofBytes(size).toKilobytes());
    }

}
