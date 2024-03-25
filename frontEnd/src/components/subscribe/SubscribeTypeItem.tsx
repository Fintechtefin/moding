import React from "react";

interface OptionProps {
  value: string;
  label: string;
  checkedValue: string;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const SubscribeTypeItem = ({
  value,
  label,
  checkedValue,
  onChange,
}: OptionProps) => {
  return (
    <label
      className={`flex-1 flex items-center justify-center text-[2vh] cursor-pointer ${
        checkedValue === value &&
        "border-t-2 border-t-transparent border-b-2 border-b-red-600 font-bold text-white"
      }`}
    >
      <input
        type="radio"
        name="subscribe"
        value={value}
        className="hidden peer"
        checked={checkedValue === value}
        onChange={onChange}
      />
      {label}
    </label>
  );
};

export default SubscribeTypeItem;
