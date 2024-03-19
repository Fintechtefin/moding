interface Props {
  label: string;
  color: string;
}

const SeatItem = ({ label, color }: Props) => {
  return (
    <div className="flex items-center gap-[0.5vh]">
      <div className="text-[2vh]">{label}</div>
      {/* <PiArmchairFill className={`text-[3vh] text-[${color}]`} /> */}
      <div
        className={`w-[2.5vh] h-[2.3vh] bg-[${color}] rounded-t-[1vh] rounded-b-[0.3vh]`}
      ></div>
    </div>
  );
};

const SeatType = () => {
  return (
    <div className="flex justify-end gap-[2vh] px-[3vh]">
      <SeatItem label="선택불가" color="#3D3C4E" />
      <SeatItem label="선택가능" color="#A09FB2" />
      <SeatItem label="선택좌석" color="#C00202" />
    </div>
  );
};

export default SeatType;
