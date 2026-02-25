package org.example.moviebooking.repository;

import org.example.moviebooking.domain.MovieIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MovieSearchRepository extends ElasticsearchRepository<MovieIndex, String> {
}
