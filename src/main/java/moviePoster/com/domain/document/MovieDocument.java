package moviePoster.com.domain.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "movies")
@Setting(settingPath = "elasticsearch/movies-settings.json")
public class MovieDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "russian_analyzer", searchAnalyzer = "russian_analyzer")
    private String name;

    @Field(type = FieldType.Text, analyzer = "russian_analyzer", searchAnalyzer = "russian_analyzer")
    private String title;
}