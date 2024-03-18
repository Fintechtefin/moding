import { useState } from "react";
import NoneNavHeader from "@components/NoneNavHeader";
import SeatType from "@components/reverse/SeatType";
import Seat from "@components/reverse/Seat";
import MovieInfo from "@components/reverse/MovieInfo";
import screen from "@assets/images/screen.webp";

const Reserve = () => {
  const [selectedSeats, setSelectedSeats] = useState<string[]>([]);
  const [max, setMax] = useState(2);
  const occupiedSeats = new Set(["B5", "C6", "D7"]);

  const handleSelect = (row: string, number: number, isSelected: boolean) => {
    const seatId = `${row}${number}`;
    if (isSelected) {
      const len = selectedSeats.length;

      if (len < max) {
        // 좌석을 선택한 경우, 배열에 추가
        setSelectedSeats((prev) => [...prev, seatId]);
      } else {
        alert(`선택하신 관람인원은 ${max}명 입니다.`);
      }
    } else {
      // 좌석 선택을 취소한 경우, 해당 좌석을 배열에서 제거
      setSelectedSeats((prev) => prev.filter((seat) => seat !== seatId));
    }
    // setSelectedSeats((prev) => {
    //   const updatedSelectedSeats = new Set([...prev]);

    //   return updatedSelectedSeats;
    // });
    // if (!isSelected) {
    //   selectedSeats.delete(seatId);
    // } else {
    //   selectedSeats.add(seatId);
    // }
    // setSelectedSeats(new Set([...selectedSeats]));
  };

  const rows = "ABCDEF".split("");
  const numbers = Array.from({ length: 8 }, (_, i) => i + 1);

  return (
    <div className="h-[100vh] flex flex-col gap-[5vh]">
      <NoneNavHeader centerText="좌석예매" />
      <div className="flex flex-col gap-[5vh]">
        <SeatType />
        <div className="flex flex-col items-center gap-[5vh]">
          <div className="relative flex flex-col items-center">
            {/* <div className="w-[80%] h-[2.5vh] rounded-t-[50%] bg-[red] blur-[6vh] absolute top-[5vh] opacity-100"></div> */}
            <img className="w-[100%] h-[5vh] px-[3vh]" src={screen} alt="" />
            <div className=" text-[#A09FB0] font-bold">SCREEN</div>
          </div>
          <div className="flex flex-col gap-[3vh]">
            {rows.map((row) => (
              <div key={row} className="flex gap-[2vh]">
                {/* <div className="flex items-center justify-center text-[1.5vh]">
                  {row}
                </div> */}
                <div className="flex items-center justify-center gap-[1vh]">
                  {numbers.map((number) => (
                    <Seat
                      key={`${row}${number}`}
                      row={row}
                      number={number}
                      isSelected={selectedSeats.includes(`${row}${number}`)}
                      isOccupied={occupiedSeats.has(`${row}${number}`)}
                      onSelect={handleSelect}
                    />
                  ))}
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
      <MovieInfo count={selectedSeats.length} max={max} />
    </div>
  );
};

export default Reserve;
