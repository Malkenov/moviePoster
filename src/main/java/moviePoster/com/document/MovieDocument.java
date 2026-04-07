package moviePoster.com.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "movies")
public class MovieDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "russian_analyzer")
    // говорит для elasticsearch как искать по этому полю,в даном случае(по словам)
    private String name;

    @Field(type = FieldType.Text, analyzer = "russian_analyzer")
    private String title;

}