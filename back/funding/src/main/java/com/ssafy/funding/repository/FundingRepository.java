package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Funding;
import java.time.LocalDate;
import java.util.List;

import com.ssafy.funding.domain.FundingStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FundingRepository extends CrudRepository<Funding, Integer> {

    @Query(
            value =
                    "select movie.movie_id movieId, movie.poster, funding.people_count crowdCnt, " +
                            "(SELECT count(*) FROM orders WHERE orders.funding_id = funding.funding_id) AS peopleCnt, "  +
                            "movie.status " +
                            "from funding join movie on funding.movie_id=movie.movie_id " +
                            "where movie.status='OPEN' order by peopleCnt desc limit 10",
            nativeQuery = true)
    List<FundingProgressResponseInterface> getProgressRanking();


    @Query(
            value =
                    "select movie.movie_id movieId, movie.poster, " +
                            "(select count(*) from movie_funding where movie_funding.movie_id=movie.movie_id) as requestCnt," +
                            "movie.status " +
                            "from movie left outer join movie_funding on movie.movie_id=movie_funding.movie_id "+
                            "where movie.status='NONE' or movie.status='READY_TO_OPEN' "+
                            "group by movieId, poster, requestCnt, status "+
                            "order by requestCnt desc limit 10",
            nativeQuery = true)
    List<FundingRequestResponseInterface> getRequestRanking();

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
                            + "join orders on orders.funding_id=funding.funding_id where orders.user_id=:userId and movie.status='CLOSED' "
                            + "group by funding.funding_id,movieId,title,poster,date,attendCnt,reservationId,fundingFinalResult,goalCnt "+
                            " order by funding.funding_id desc",
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

    public interface FundingProgressResponseInterface {
        int getMovieId();

        String getPoster();

        int getCrowdCnt();

        int getPeopleCnt();

        FundingStatus getStatus();

    }

    public interface FundingRequestResponseInterface {
        int getMovieId();

        String getPoster();

        long getRequestCnt();

        FundingStatus getStatus();
    }

    public interface OpenFundingResponseInterface {
        String getCinemaName();

        String getMovieDate();

        int getCrowdCnt();

        int getPrice();

        int getPeopleCnt();
    }
}
