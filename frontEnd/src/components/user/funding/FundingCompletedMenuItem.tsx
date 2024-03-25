interface Props {
  state: string;
  handleChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  value: string;
  text: string;
}

const FundingCompletedMenuItem = ({
  state,
  handleChange,
  value,
  text,
}: Props) => {
  return (
    <label
      className={`flex-1 flex items-center justify-center text-[2vh] cursor-pointer ${
        state === value &&
        "border-t-2 border-t-transparent border-b-2 border-b-red-600 font-bold text-white"
      }`}
    >
      <input
        checked={state === value}
        value={value}
        name="fund_state"
        type="radio"
        className="hidden input"
        onChange={handleChange}
      />
      {text}
    </label>
  );
};

export default FundingCompletedMenuItem;
