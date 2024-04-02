import { useRecoilValue, useResetRecoilState } from "recoil";
import {
  selectSeatsAtom,
  selectSeatsLengthSelector,
} from "@recoil/reserveStore";
import { toastMsg } from "@util/commonFunction";
import { ToasterMsg } from "@components/Common";
import moviePost from "@assets/images/영화포스터.jpg";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { axiosApi } from "@util/commons";

interface Props {
  max: number;
}

const MovieInfo = ({ max }: Props) => {
  const selectSeatsLength = useRecoilValue(selectSeatsLengthSelector);
  const selectSeats = useRecoilValue(selectSeatsAtom);
  const resetSelectSeats = useResetRecoilState(selectSeatsAtom);

  const navigate = useNavigate();

  const handleClick = async () => {
    if (selectSeatsLength < max) {
      toastMsg("인원을 선택해주세요");
      return;
    }

    try {
      const [line, col] = selectSeats[0];

      // const col = Number(col1);

      console.log(line, col);

      const seatData = {
        fundingId: 1,
        seats: {
          seat: [
            {
              col: 11,
              line: "J",
            },
          ],
        },
      };

      console.log(seatData);

      const res = await axiosApi().post(`reservations/make`, seatData);

      console.log(res.data);

      const res1 = await axiosApi().get(`/reservations/get/${7}`);

      console.log(res1);

      resetSelectSeats();

      navigate(`/user/ticket/${7}`, {
        state: res1.data,
        replace: true,
      });
    } catch (err) {
      console.log(err);
      if (
        axios.isAxiosError(err) &&
        err.response?.data.code === "Reservation_400_6"
      ) {
        toastMsg("이미 예약 된 자석입니다.");
        resetSelectSeats();
      }
    }
  };

  return (
    <div className="text-black bg-white w-full h-[20vh] rounded-t-2xl ">
      <div className="p-[2vh] flex gap-[3vh]">
        <div className="flex flex-col justify-between grow text-[2.5vh]">
          <div className="flex gap-[2vh]">
            <div className="font-bold">CGV 강남</div>
            <div>3.15(금) 15:00</div>
          </div>
          <div className="flex justify-between">
            <div className="font-bold">엘리멘탈</div>
            <div>{`인원 ${selectSeatsLength} / ${max}`}</div>
          </div>
        </div>
        <img
          className="h-[10vh] object-cover rounded"
          src={moviePost}
          alt="영화포스터"
        />
      </div>
      <button
        className=" w-[100%] h-[6vh] bg-red-600 shadow-bgRed text-white font-bold text-[2.5vh] "
        onClick={handleClick}
      >
        예매
      </button>
      <ToasterMsg />
    </div>
  );
};

export default MovieInfo;
