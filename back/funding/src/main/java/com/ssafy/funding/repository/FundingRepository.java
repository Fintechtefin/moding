package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Funding;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FundingRepository extends CrudRepository<Funding, Integer> {

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
                            + "join cinema on funding.cinema_id=cinema.cinema_id where funding.movie_id=:movieId order by funding.funding_id desc limit 1",
            nativeQuery = true)
    OpenFundingResponseInterface getOpenFundingInfo(int movieId);

    @Query(
            value =
                    "select movie.movie_id movieId, movie.title, movie.poster, funding.date,"
                            + "(select sum(count) from orders where orders.funding_id=funding.funding_id) attendCnt,"
                            + "(select reservation_id from reservation where reservation.funding_id=funding.funding_id) reservationId,"
                            + "(select funding_final_result from funding_history where funding_history.funding_id=funding.funding_id) fundingFinalResult,"
                            + "funding.people_count goalCnt from funding "
                            + "join movie on movie.movie_id=funding.movie_id "
                            + "join orders on orders.funding_id=funding.funding_id where orders.user_id=:userId and movie.status='CLOSED' group by funding.funding_id order by funding.funding_id desc",
            nativeQuery = true)
    List<AfterMoodingResponseInterface> getMyFundingResult(int userId);

    public interface AfterMoodingResponseInterface {
        int getMovieId();

        String getTitle();

        String getPoster();

        LocalDate getDate();

        int getAttendCnt();

        int getGoalCnt();

        int getReservationId();

        int getFundingFinalResult();
    }

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
}
