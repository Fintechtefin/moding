import { IoClose } from "react-icons/io5";
import { ChangeEvent } from "react";

interface Props {
  seatId: string;
  isSelected: boolean;
  isOccupied: boolean;
  isSelectable: boolean;
  onSelect: (seatId: string, isSelected: boolean) => void;
}

const Seat = ({
  seatId,
  isSelected,
  isOccupied,
  isSelectable,
  onSelect,
}: Props) => {
  // 체크박스 클릭 핸들러
  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    if (!isOccupied && isSelectable) {
      onSelect(seatId, e.target.checked);
    }
  };

  const seatStatusClass =
    isOccupied || !isSelectable
      ? "bg-[#3D3C4E]"
      : isSelected
      ? "bg-[#C00202]"
      : "bg-[#A09FB2]";

  return (
    <label className="relative">
      <div
        className={`w-[4vh] h-[3.5vh] rounded-b-[0.3vh] rounded-t-[1.3vh] flex justify-center items-center font-bold text-[1.5vh] cursor-pointer ${seatStatusClass}`}
      >
        {isOccupied || !isSelectable ? (
          <IoClose className="text-[2.5vh]" />
        ) : (
          seatId
        )}
      </div>
      <input
        className="hidden "
        type="checkbox"
        disabled={isOccupied || !isSelectable}
        checked={isSelected}
        onChange={handleChange}
      />
    </label>
  );
};

export default Seat;
