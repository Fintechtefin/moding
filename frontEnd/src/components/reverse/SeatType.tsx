interface Props {
  label: string;
  color: string;
}

const SeatItem = ({ label, color }: Props) => {
  return (
    <div className="flex items-center gap-[0.5vh]">
      <div className="text-[2vh]">{label}</div>
      <div
        className={`w-[2.5vh] h-[2.3vh] bg-${color} rounded-t-[1vh] rounded-b-[0.3vh] shj`}
      ></div>
    </div>
  );
};

const SeatType = () => {
  return (
    <div className="flex justify-end gap-[2vh]">
      <SeatItem label="선택불가" color="bgGray" />
      <SeatItem label="선택가능" color="textGray" />
      <SeatItem label="선택좌석" color="red-600" />
    </div>
  );
};

export default SeatType;
