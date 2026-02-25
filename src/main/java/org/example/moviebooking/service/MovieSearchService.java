package org.example.moviebooking.service;

import org.example.moviebooking.domain.MovieIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieSearchService {

    private final ElasticsearchOperations operations;

    /**
     * Search for movies by title using Elasticsearch.
     *
     * @param title The title of the movie to search for.
     * @return A list of MovieIndex objects that match the search criteria.
     */
    public List<MovieIndex> search(String title) {

        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q.match(m -> m
                        .field("title")
                        .query(title)))
                .build();

        return operations.search(query, MovieIndex.class)
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}