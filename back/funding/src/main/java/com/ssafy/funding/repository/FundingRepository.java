package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.FundingStatus;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FundingRepository extends CrudRepository<Funding, Integer> {

    @Query(
            value =
                    "select movie.movie_id movieId, movie.poster, funding.people_count crowdCnt, "
                            + "ifnull((SELECT sum(count) FROM orders WHERE orders.funding_id = funding.funding_id),0) AS peopleCnt, "
                            + "movie.status "
                            + "from funding join movie on funding.movie_id=movie.movie_id "
                            + "where movie.status='OPEN' order by peopleCnt desc limit 10",
            nativeQuery = true)
    List<FundingProgressResponseInterface> getProgressRanking();

    @Query(
            value =
                    "select movie.movie_id movieId, movie.poster, "
                            + "ifnull((select count(*) from movie_funding where movie_funding.movie_id=movie.movie_id),0) as requestCnt,"
                            + "movie.status "
                            + "from movie left outer join movie_funding on movie.movie_id=movie_funding.movie_id "
                            + "where movie.status='NONE' or movie.status='READY_TO_OPEN' "
                            + "group by movieId, poster, requestCnt, status "
                            + "order by requestCnt desc limit 10",
            nativeQuery = true)
    List<FundingRequestResponseInterface> getRequestRanking();

    @Query(
            value =
                    "select funding.funding_id fundingId, funding.people_count crowdCnt, funding.price price, funding.time time, funding.date date, cinema.name cinemaName, ifnull((select sum(count) from orders where orders.funding_id=funding.funding_id),0) peopleCnt, "
                            + "ifnull((select count(*) from orders where orders.funding_id=funding.funding_id and orders.user_id= :userId),0) as participant from funding "
                            + "join cinema on funding.cinema_id=cinema.cinema_id "
                            + "where funding.movie_id= :movieId order by funding.funding_id desc limit 1",
            nativeQuery = true)
    OpenFundingResponseInterface getOpenFundingInfo(int movieId, int userId);

    @Query(
            value =
                    "select movie.movie_id movieId, movie.title, movie.poster, funding.date,"
                            + "(select sum(count) from orders where orders.funding_id=funding.funding_id) attendCnt,"
                            + "ifnull((select reservation_id from reservation where reservation.funding_id=funding.funding_id),0) reservationId,"
                            + "funding.people_count goalCnt from funding "
                            + "join movie on movie.movie_id=funding.movie_id "
                            + "join orders on orders.funding_id=funding.funding_id "
                            + "where (orders.user_id=:userId and movie.status='CLOSED' and (select funding_final_result from funding_history where funding_history.funding_id=funding.funding_id)=true) or (orders.user_id=:userId and movie.status='RESERVATION') "
                            + "group by funding.funding_id,movieId,title,poster,date,attendCnt,reservationId,goalCnt "
                            + "order by funding.funding_id desc",
            nativeQuery = true)
    List<AfterMoodingSuccessResponseInterface> getMySuccessFundingResult(int userId);

    @Query(
            value =
                    "select movie.movie_id movieId, movie.title, movie.poster, funding.date,"
                            + "ifnull((select sum(count) from orders where orders.funding_id=funding.funding_id),0) attendCnt,"
                            + "funding.people_count goalCnt from funding "
                            + "join movie on movie.movie_id=funding.movie_id "
                            + "join orders on orders.funding_id=funding.funding_id "
                            + "where orders.user_id=:userId and movie.status='CLOSED' and (select funding_final_result from funding_history where funding_history.funding_id=funding.funding_id)=false "
                            + "group by funding.funding_id,movieId,title,poster,date,attendCnt,goalCnt "
                            + "order by funding.funding_id desc",
            nativeQuery = true)
    List<AfterMoodingFailureResponseInterface> getMyFailureFundingResult(int userId);

    public interface AfterMoodingSuccessResponseInterface {
        int getMovieId();

        String getTitle();

        String getPoster();

        LocalDate getDate();

        int getAttendCnt();

        int getGoalCnt();

        int getReservationId();
    }

    public interface AfterMoodingFailureResponseInterface {
        int getMovieId();

        String getTitle();

        String getPoster();

        LocalDate getDate();

        int getAttendCnt();

        int getGoalCnt();
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

        String getTime();

        int getCrowdCnt();

        int getPrice();

        int getPeopleCnt();

        int getFundingId();

        LocalDate getDate();

        int getParticipant();
    }
}
