import OptionItem from "@components/myPage/OptionItem";
import { MenuOptionItem } from "@util/types";

interface Props {
  title: string;
  items: MenuOptionItem[];
}

const OptionSection = ({ title, items }: Props) => {
  return (
    <div className="flex flex-col gap-[1.5vh]">
      <div className="text-textGray text-[2vh]">{title}</div>
      <div className="flex flex-col gap-[1vh] text-[2.3vh] font-bold">
        {items.map((item) => (
          <OptionItem key={item.name} {...item} />
        ))}
      </div>
    </div>
  );
};

export default OptionSection;
