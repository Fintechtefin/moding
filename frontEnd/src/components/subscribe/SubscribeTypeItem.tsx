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
    <label className="flex items-center justify-center cursor-pointer">
      <input
        type="radio"
        name="subscribe"
        value={value}
        className="peer hidden"
        checked={checkedValue === value}
        onChange={onChange}
      />
      <span
        className={`peer-checked:bg-[#C00202] peer-checked:text-white peer-checked:font-bold text-black bg-[#EAEBED] text-[2vh] p-[0.8vh] rounded-[1vh] transition duration-150 ease-in-out`}
      >
        {label}
      </span>
    </label>
  );
};

export default SubscribeTypeItem;
