import { PiArmchairFill } from "react-icons/pi";
import { IoClose } from "react-icons/io5";
import { ChangeEvent } from "react";

interface Props {
  row: string;
  number: number;
  isSelected: boolean;
  isOccupied: boolean;
  onSelect: (row: string, number: number, isSelected: boolean) => void;
}

// const Seat = ({ row, number, isSelected, isOccupied, onSelect }: Props) => {
//   const handleClick = () => {
//     if (!isOccupied) {
//       onSelect(row, number);
//     }
//   };

//   let className =
//     "text-[4.5vh] text-[#A09FB2] flex justify-center items-center cursor-pointer ";
//   if (isSelected) {
//     className += "text-[#C00202]";
//   } else if (isOccupied) {
//     className += "text-[#3D3C4E] cursor-not-allowed";
//   }

//   return (
//     <div className="relative" onClick={handleClick}>
//       <PiArmchairFill className={className} />
//       <div className="absolute right-[50%] top-[15%] text-[1.5vh] font-bold z-[1] translate-x-[50%]  text-black">
//         {number}
//       </div>
//     </div>
//   );
// };

const Seat = ({ row, number, isSelected, isOccupied, onSelect }: Props) => {
  // 체크박스 클릭 핸들러
  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    if (!isOccupied) {
      onSelect(row, number, e.target.checked);
    }
  };

  const seatStatusClass = isOccupied
    ? "bg-[#3D3C4E] cursor-not-allowed"
    : isSelected
    ? "bg-[#C00202] cursor-pointer"
    : "bg-[#A09FB2] cursor-pointer";

  return (
    <label className="relative">
      {/* <PiArmchairFill
        className={`text-[4vh] text-[#A09FB2] flex justify-center items-center cursor-pointer${
          isOccupied ? " text-[#3D3C4E] cursor-not-allowed" : ""
        }${isSelected ? " text-[#C00202]" : ""}`}
      /> */}
      {/* isOccupied ? " bg-[#3D3C4E] cursor-not-allowed" : ""
        }${isSelected ? " bg-[#C00202]" : "" */}
      <div
        className={`w-[4vh] h-[3.5vh] rounded-b-[0.3vh] rounded-t-[1.3vh] flex justify-center items-center font-bold text-[1.5vh] ${seatStatusClass}`}
      >
        {isOccupied ? <IoClose className="text-[2.5vh]" /> : `${row}${number}`}
      </div>
      <input
        className="hidden "
        type="checkbox"
        disabled={isOccupied}
        checked={isSelected}
        onChange={handleChange}
      />
      {/* <div className="absolute right-[50%] top-[15%] text-[1.5vh] font-bold z-[1] translate-x-[50%]  text-black">
        {number}
      </div> */}
    </label>
  );
};

export default Seat;
