import FundingCompletedMenuItem from "./FundingCompletedMenuItem";

interface Props {
  state: string;
  handleChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const FundingCompletedMenu = ({ state, handleChange }: Props) => {
  return (
    <div className="w-[100%] h-[5vh] flex border-b-[1px] border-b-red-600 text-textGray">
      <FundingCompletedMenuItem
        state={state}
        handleChange={handleChange}
        value="success"
        text="펀딩성공"
      />
      <FundingCompletedMenuItem
        state={state}
        handleChange={handleChange}
        value="fail"
        text="펀딩실패"
      />
    </div>
  );
};

export default FundingCompletedMenu;
