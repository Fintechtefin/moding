import {
  selectSeatsAtom,
  selectSeatsLengthSelector,
} from "@recoil/reserveStore";
import { useRecoilValue, useResetRecoilState } from "recoil";
import { toastMsg } from "@util/commonFunction";
import { ToasterMsg } from "@components/Common";
import moviePost from "@assets/images/영화포스터.jpg";
import axios from "axios";
import { useNavigate } from "react-router-dom";

interface Props {
  max: number;
}

const MovieInfo = ({ max }: Props) => {
  const selectSeatsLength = useRecoilValue(selectSeatsLengthSelector);
  const selectSeats = useRecoilValue(selectSeatsAtom);
  const resetSeats = useResetRecoilState(selectSeatsAtom);

  const navigate = useNavigate();

  const handleClick = async () => {
    if (selectSeatsLength < max) {
      toastMsg("인원을 선택해주세요");
      return;
    }

    try {
      const [line, col] = selectSeats[0];

      const data = {
        fundingId: 1,
        seat: {
          seat: [
            {
              col,
              line,
            },
          ],
        },
        userId: 1,
      };

      const res = await axios.post(
        `${import.meta.env.VITE_BASE_URL}/api/reservations/make`,
        data,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: localStorage.getItem("jwt"),
          },
        }
      );

      const res1 = await axios.get(
        `${import.meta.env.VITE_BASE_URL}:8085/reservations/create/${res.data}`
      );

      resetSeats();

      navigate(`/user/ticket/${res.data}`, {
        state: res1.data,
        replace: true,
      });
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="text-black bg-white w-[100%] h-[20vh] rounded-t-[3vh] ">
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
          className="h-[10vh] object-cover rounded-[0.8vh]"
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
