import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useRecoilState, useRecoilValue, useResetRecoilState } from "recoil";

import NoneNavHeader from "@components/NoneNavHeader";
import SeatType from "@components/reverse/SeatType";
import Seat from "@components/reverse/Seat";
import MovieInfo from "@components/reverse/MovieInfo";
import screen from "@assets/images/screen.webp";
import Prompt from "@components/reverse/Prompt";
import {
  occupiedSeatsAtom,
  selectSeatsAtom,
  selectSeatsLengthSelector,
} from "@recoil/reserveStore";
import { axiosApi } from "@util/commons";
import { ToasterMsg } from "@components/Common";
import axios from "axios";
import Loading from "@pages/payment/Loading";
import { GoEye } from "react-icons/go";

const ReservePage = () => {
  const resetSeats = useResetRecoilState(selectSeatsAtom);
  const selectSeatsLength = useRecoilValue(selectSeatsLengthSelector);
  const [occupiedSeats, setOccupiedSeats] = useRecoilState(occupiedSeatsAtom);
  const [see, setSee] = useState(0);
  const [isLoading, setIsLoading] = useState(true);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const maxSeats = 1;

  const navigate = useNavigate();
  const { state } = useLocation();

  const aaa = async () => {
    fetch(
      `${import.meta.env.VITE_BASE_URL}/api//reservations/decrease/${
        state.fundinfo.fundingId
      }`,
      {
        method: "GET",
        headers: { Authorization: localStorage.getItem("jwt")! },
        keepalive: true,
      }
    );
  };

  const handleBackButtonClick = () => {
    aaa();
    if (selectSeatsLength) {
      setIsModalOpen(true); // 모달을 열어 사용자에게 확인 요청
    } else {
      navigate(-1); // 뒤로 가기, `useNavigate` 훅 사용
    }
  };

  const closeModal = () => setIsModalOpen(false);

  const confirmAndGoBack = () => {
    // 데이터 리셋 및 뒤로 가기 로직
    resetSeats();
    closeModal();
    navigate(-1);
  };

  console.log(state.movieId);

  console.log(state.fundinfo.fundingId);

  const rows = "ABCDEFGH".split("");
  const numbers = Array.from({ length: 8 }, (_, i) => i + 1);

  useEffect(() => {
    const fetchOccupiedSeats = async () => {
      try {
        await axiosApi().get(
          `/reservations/check/payment/${state.fundinfo.fundingId}`
        );

        const res = await axiosApi().get(
          `/reservations/get/seat/${state.fundinfo.fundingId}`
        );
        const seats = res.data.match(/[A-Za-z]\d+/g) || [];
        console.log(seats);
        setOccupiedSeats(seats);
        const res1 = await axiosApi().get(
          `/reservations/increase/${state.fundinfo.fundingId}`
        );
        setSee(res1.data);
        console.log(res1);
      } catch (err) {
        console.log(err);
        if (
          axios.isAxiosError(err) &&
          err.response?.data.code === "Reservation_400_2"
        ) {
          navigate(`/fund/list/${state.movieId}`, {
            state: { type: "list", err: err.response.data.message },
            replace: true,
          });
        }
      } finally {
        setIsLoading(false);
      }
    };

    fetchOccupiedSeats();

    window.addEventListener("beforeunload", aaa);

    return () => {
      window.removeEventListener("beforeunload", aaa);
    };
  }, []);

  useEffect(() => {
    const handleBeforeUnload = (e: BeforeUnloadEvent) => {
      if (selectSeatsLength) {
        e.preventDefault();
        e.returnValue = "";
        aaa();
      }
    };

    window.addEventListener("beforeunload", handleBeforeUnload);
    return () => window.removeEventListener("beforeunload", handleBeforeUnload);
  }, [selectSeatsLength]); // myArray가 변경될 때마다 이 useEffect를 다시 실행합니다.

  return (
    <div className="h-[100vh] flex flex-col">
      <NoneNavHeader
        centerText="좌석예매"
        onBackButtonClick={handleBackButtonClick}
      />
      {isLoading ? (
        <Loading />
      ) : (
        <>
          <div className=" absolute right-6 top-4 text-[1.5vh] flex items-center gap-[1vh] text-red-600">
            <GoEye />
            {see}명이 보고있어요
          </div>
          <div className="flex flex-col justify-between h-[93vh]">
            <div className="flex-1 flex flex-col gap-[5vh] p-[3vh]">
              <SeatType />
              {/* <GoEye className="text-red-600" /> */}
              <div className="flex flex-col items-center gap-[5vh]">
                <div className="relative flex flex-col items-center">
                  <img
                    className="w-[100%] h-[5vh]"
                    src={screen}
                    alt="영화관스크린"
                  />
                  <div className="text-[2vh] text-[#A09FB0] font-bold">
                    SCREEN
                  </div>
                </div>
                <div className="flex flex-col gap-[2vh]">
                  {rows.map((row) => (
                    <div key={row} className="flex gap-[2vh]">
                      <div className="flex items-center justify-center gap-[1vh]">
                        {numbers.map((number) => {
                          const seatId = `${row}${number}`;
                          return (
                            <Seat
                              key={seatId}
                              seatId={seatId}
                              max={maxSeats}
                              isOccupied={occupiedSeats.includes(seatId)}
                            />
                          );
                        })}
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            </div>
            <MovieInfo max={maxSeats} />
          </div>
        </>
      )}
      {isModalOpen && (
        <Prompt onClose={closeModal} onConfirm={confirmAndGoBack} />
      )}
      <ToasterMsg />
    </div>
  );
};

export default ReservePage;
