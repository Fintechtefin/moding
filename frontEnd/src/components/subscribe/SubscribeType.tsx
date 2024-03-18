import SubscribeTypeItem from "@components/subscribe/SubscribeTypeItem";

interface Props {
  check: string;
  handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const SubscribeType = ({ check, handleChange }: Props) => {
  return (
    <div className="flex mx-[3vh] gap-[2vh]">
      <SubscribeTypeItem
        value="like"
        label="좋아요"
        checkedValue={check}
        onChange={handleChange}
      />
      <SubscribeTypeItem
        value="req"
        label="펀딩 요청"
        checkedValue={check}
        onChange={handleChange}
      />
    </div>
  );
};

export default SubscribeType;
