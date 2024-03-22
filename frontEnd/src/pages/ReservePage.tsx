import { useEffect, useState } from "react";
import NoneNavHeader from "@components/NoneNavHeader";
import SeatType from "@components/reverse/SeatType";
import Seat from "@components/reverse/Seat";
import MovieInfo from "@components/reverse/MovieInfo";
import screen from "@assets/images/screen.webp";

const ReservePage = () => {
  const [selectedSeats, setSelectedSeats] = useState<string[]>([]);
  const [max, setMax] = useState(0);
  const occupiedSeats = new Set(["B5", "C6", "D7"]);

  const handleSelect = (seatId: string, isSelected: boolean) => {
    setSelectedSeats((prev) => {
      if (isSelected) {
        return [...prev, seatId];
      } else {
        return prev.filter((seat) => seat !== seatId);
      }
    });
  };

  const rows = "ABCDEF".split("");
  const numbers = Array.from({ length: 8 }, (_, i) => i + 1);

  useEffect(() => {
    setMax(2);
  }, []);

  return (
    <div className="h-[100vh] flex flex-col gap-[5vh]">
      <NoneNavHeader centerText="좌석예매" />
      <div className="flex flex-col gap-[5vh]">
        <SeatType />
        <div className="flex flex-col items-center gap-[5vh]">
          <div className="relative flex flex-col items-center">
            {/* <div className="w-[80%] h-[2.5vh] rounded-t-[50%] bg-[red] blur-[6vh] absolute top-[5vh] opacity-100"></div> */}
            <img
              className="w-[100%] h-[5vh] px-[3vh]"
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
                        isSelected={selectedSeats.includes(seatId)}
                        isOccupied={occupiedSeats.has(seatId)}
                        isSelectable={
                          selectedSeats.length < max ||
                          selectedSeats.includes(seatId)
                        }
                        onSelect={handleSelect}
                      />
                    );
                  })}
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

export default ReservePage;
