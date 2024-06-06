import { useRecoilValue, useResetRecoilState, useSetRecoilState } from "recoil";
import {
  occupiedSeatsAtom,
  selectSeatsAtom,
  selectSeatsLengthSelector,
} from "@recoil/reserveStore";
import { toastMsg } from "@util/commonFunction";
import { ToasterMsg } from "@components/Common";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import { axiosApi } from "@util/commons";
import { useState } from "react";

interface Props {
  max: number;
}

const MovieInfo = ({ max }: Props) => {
  const selectSeatsLength = useRecoilValue(selectSeatsLengthSelector);
  const selectSeats = useRecoilValue(selectSeatsAtom);
  const resetSelectSeats = useResetRecoilState(selectSeatsAtom);
  const setOccupiedSeats = useSetRecoilState(occupiedSeatsAtom);

  const navigate = useNavigate();
  const location = useLocation();

  console.log(location.state);

  const [fundInfo] = useState(location.state);

  const aaa = async () => {
    const res = await axiosApi().get(
      `/reservations/get/seat/${fundInfo.fundinfo.fundingId}`
    );
    const seats = res.data.match(/[A-Za-z]\d+/g) || [];
    console.log(seats);
    setOccupiedSeats(seats);
  };

  const handleClick = async () => {
    if (selectSeatsLength < max) {
      toastMsg("인원을 선택해주세요");
      return;
    }

    try {
      const seatData = {
        fundingId: fundInfo.fundinfo.fundingId,
        position: selectSeats,
      };

      console.log(seatData);

      const res = await axiosApi().post(`reservations/make`, seatData);

      console.log(res);

      const res1 = await axiosApi().get(`/reservations/get/${res.data}`);

      console.log(res1);

      resetSelectSeats();

      navigate(`/user/ticket/${res.data}`, {
        state: res1.data,
        replace: true,
      });
    } catch (err) {
      console.log(err);
      if (
        axios.isAxiosError(err) &&
        err.response?.data.code === "Reservation_400_6"
      ) {
        toastMsg(err.response.data.message);
        resetSelectSeats();
        aaa();
      }
    }
  };

  return (
    <div className="text-black bg-white w-full rounded-t-2xl flex flex-col justify-between">
      <div className="p-[2vh] flex gap-[3vh]">
        <div className="flex flex-col justify-between flex-1">
          <div className="">
            <div className="text-[2.5vh] font-bold">
              {fundInfo.fundinfo.cinemaName}
            </div>
            <div className="text-[1.5vh]">{`3.15(금) ${fundInfo.fundinfo.time}`}</div>
          </div>
          <div className="flex justify-between text-[2vh]">
            <div className="font-bold">{fundInfo.movieTitle}</div>
            <div>{`인원 ${selectSeatsLength} / ${max}`}</div>
          </div>
        </div>
        <img
          className="h-[12vh] object-cover rounded"
          src={fundInfo.poster}
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
