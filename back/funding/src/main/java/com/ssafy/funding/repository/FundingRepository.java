package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Funding;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FundingRepository extends CrudRepository<Funding, Integer> {

    public interface FundingListResponseInterface {
        int getFundingId();

        String getPoster();

        int getCrowdCnt();

        int getPeopleCnt();
    }

    public interface OpenFundingResponseInterface {
        String getCinemaName();

        String getMovieDate();

        int getCrowdCnt();

        int getPrice();

        int getPeopleCnt();
    }

    @Query(
            value =
                    "select funding.funding_id fundingId, movie.poster, funding.people_count crowdCnt, "
                            + "(select count(*) from orders where orders.funding_id=funding.funding_id) as peopleCnt from funding join movie on funding.movie_id=movie.movie_id order by peopleCnt desc limit 10",
            nativeQuery = true)
    List<FundingListResponseInterface> getProgressRanking();

    @Query(
            value =
                    "select funding.funding_id fundingId, movie.poster, funding.people_count crowdCnt, "
                            + "(select count(*) from movie_funding mf where mf.movie_id=movie.movie_id) as peopleCnt from funding join movie on funding.movie_id=movie.movie_id order by peopleCnt desc limit 10",
            nativeQuery = true)
    List<FundingListResponseInterface> getRequestRanking();

    @Query(
            value =
                    "select funding.people_count crowdCnt, funding.price, funding.time movieDate, cinema.name cinemaName, (select count(*) from orders where orders.funding_id=funding.funding_id) peopleCnt from funding "
                            + "join cinema on funding.cinema_id=cinema.cinema_id where funding.movie_id=1 order by funding.funding_id desc limit 1",
            nativeQuery = true)
    OpenFundingResponseInterface getOpenFundingInfo(int movieId);
}
