package moviePoster.com.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import moviePoster.com.domain.document.MovieDocument;

public interface MovieSearchRepository extends ElasticsearchRepository<MovieDocument, Long> {

    @Query("""
            {
              "bool": {
                "should": [
                  {
                    "multi_match": {
                      "query": "?0",
                      "fields": ["name^4", "title^3"],
                      "type": "best_fields",
                      "fuzziness": "AUTO",
                      "operator": "or"
                    }
                  },
                  {
                    "match_phrase": {
                      "name": { "query": "?0", "boost": 4 }
                    }
                  },
                  {
                    "match_phrase": {
                      "title": { "query": "?0", "boost": 3 }
                    }
                  }
                ],
                "minimum_should_match": 1
              }
            }
            """)
    Page<MovieDocument> searchByQuery(String query, Pageable pageable);

}