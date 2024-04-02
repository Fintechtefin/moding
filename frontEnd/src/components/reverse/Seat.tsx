import { ChangeEvent } from "react";
import { useRecoilValue, useSetRecoilState } from "recoil";
import {
  isSelectableSelector,
  isSelectedSelector,
  selectSeatsAtom,
} from "@recoil/reserveStore";
import { IoClose } from "react-icons/io5";

interface Props {
  seatId: string;
  max: number;
  isOccupied: boolean;
}

const Seat = ({ seatId, max, isOccupied }: Props) => {
  const setSelectedSeats = useSetRecoilState(selectSeatsAtom);
  const isSelected = useRecoilValue(isSelectedSelector(seatId));
  const isSelectable = useRecoilValue(isSelectableSelector({ seatId, max }));

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const isSelected = e.target.checked;
    if (!isOccupied && isSelectable) {
      setSelectedSeats((prev) =>
        isSelected ? [...prev, seatId] : prev.filter((seat) => seat !== seatId)
      );
    }
  };

  const seatStatusClass = `w-[4vh] h-[3.3vh] rounded-b-sm rounded-t-xl flex justify-center items-center font-bold text-[1.5vh] cursor-pointer ${
    isOccupied || !isSelectable
      ? "bg-bgGray"
      : isSelected
      ? "bg-red-600"
      : "bg-textGray"
  }`;

  return (
    <label className="relative">
      <div className={seatStatusClass}>
        {isOccupied || !isSelectable ? (
          <IoClose className="text-[4vh] text-textGray" />
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
