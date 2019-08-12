package de.cl.playground.spring.file.upload.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.cl.playground.spring.file.upload.serialization.Base64Deserializer;
import de.cl.playground.spring.file.upload.serialization.Base64Serializer;
import lombok.Data;

@Data
public class FileDto {

    private String fileName;

    @JsonSerialize(using = Base64Serializer.class)
    @JsonDeserialize(using = Base64Deserializer.class)
    private byte[] data;

}
