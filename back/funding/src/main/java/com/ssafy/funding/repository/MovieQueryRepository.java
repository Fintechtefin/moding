package com.ssafy.funding.repository;

import static com.ssafy.funding.domain.QGenre.genre;
import static com.ssafy.funding.domain.QMovie.movie;
import static com.ssafy.funding.domain.QMovieGenre.movieGenre;
import static com.ssafy.funding.domain.QMovieLike.movieLike;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.funding.dto.GenreSearchCondition;
import com.ssafy.funding.dto.response.MovieGenreResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MovieQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    //    private final ConditionFilter conditionFilter;

    public Slice<MovieGenreResponse> findByGenre(
            GenreSearchCondition condition, PageRequest pageRequest) {

        final List<MovieGenreResponse> movies =
                jpaQueryFactory
                        .select(makeProjections())
                        .from(movie)
                        .join(movie.movieGenreList, movieGenre)
                        .on(movie.id.eq(movieGenre.movie.id))
                        .join(movieGenre.genre, genre)
                        .on(movieGenre.genre.id.eq(genre.id))
                        .where(genre.parentGenreId.eq(condition.getParentGenreId()))
                        .groupBy(movie.id)
                        .orderBy(orderSpecifier(condition.getOrder(), movie.id))
                        .offset(pageRequest.getOffset())
                        .limit(pageRequest.getPageSize())
                        .fetch();

        return new SliceImpl<MovieGenreResponse>(
                getCurrentPageMovies(movies, pageRequest),
                pageRequest,
                hasNext(movies, pageRequest));
    }

    private OrderSpecifier<?> orderSpecifier(String orderByField, NumberPath<Integer> movieId) {

        Expression<Long> likeCountExpression =
                JPAExpressions.select(movieLike.count())
                        .from(movieLike)
                        .where(movieLike.movieId.eq(movieId.intValue()));

        if ("likeAsc".equals(orderByField)) {
            //            return movieLike.count().asc();
            return Expressions.asNumber(likeCountExpression).asc();
        } else if ("likeDesc".equals(orderByField)) {
            return Expressions.asNumber(likeCountExpression).desc();
        } else if ("titleAsc".equals(orderByField)) {
            return movie.title.asc();
        } else {
            return movie.title.desc();
        }
    }

    private static ConstructorExpression<MovieGenreResponse> makeProjections() {
        return Projections.constructor(
                MovieGenreResponse.class, movie.id, movie.poster, movie.status);
    }

    private List<MovieGenreResponse> getCurrentPageMovies(
            final List<MovieGenreResponse> movies, final PageRequest pageRequest) {
        if (hasNext(movies, pageRequest)) {
            return movies.subList(0, movies.size() - 1);
        }
        return movies;
    }

    private boolean hasNext(
            final List<MovieGenreResponse> challenges, final PageRequest pageRequest) {
        return challenges.size() > pageRequest.getPageSize();
    }
}
