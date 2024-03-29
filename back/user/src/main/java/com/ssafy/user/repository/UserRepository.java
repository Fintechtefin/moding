package com.ssafy.user.repository;

import com.ssafy.user.domain.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

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

    Optional<User> findBySocialId(String socialLoginId);

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
}
