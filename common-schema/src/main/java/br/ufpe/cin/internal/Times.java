package br.ufpe.cin.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Times {
    @Field(type = FieldType.Date, store = true)
    private Date createdAt;
    @Field(type = FieldType.Date, store = true)
    private Date persistedAt;
    private Long platformTime;
}
