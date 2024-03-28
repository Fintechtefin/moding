import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilValue, useResetRecoilState } from "recoil";

import NoneNavHeader from "@components/NoneNavHeader";
import SeatType from "@components/reverse/SeatType";
import Seat from "@components/reverse/Seat";
import MovieInfo from "@components/reverse/MovieInfo";
import screen from "@assets/images/screen.webp";
import Prompt from "@components/reverse/Prompt";
import {
  selectSeatsAtom,
  selectSeatsLengthSelector,
} from "@recoil/reserveStore";
// import { GoEye } from "react-icons/go";

const ReservePage = () => {
  const resetSeats = useResetRecoilState(selectSeatsAtom);

  const selectSeatsLength = useRecoilValue(selectSeatsLengthSelector);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [max] = useState(1);
  const occupiedSeats: string[] = ["B5", "C6", "D7"];

  const navigate = useNavigate();

  const handleBackButtonClick = () => {
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

  const rows = "ABCDEFGH".split("");
  const numbers = Array.from({ length: 8 }, (_, i) => i + 1);

  useEffect(() => {
    const handleBeforeUnload = (e: BeforeUnloadEvent) => {
      if (selectSeatsLength) {
        e.preventDefault();
        e.returnValue = "";
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
              <div className="text-[2vh] text-[#A09FB0] font-bold">SCREEN</div>
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
                          max={max}
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
        <MovieInfo max={max} />
      </div>
      {isModalOpen && (
        <Prompt onClose={closeModal} onConfirm={confirmAndGoBack} />
      )}
    </div>
  );
};

export default ReservePage;
