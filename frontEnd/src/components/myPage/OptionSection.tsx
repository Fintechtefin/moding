import OptionItem from "@components/myPage/OptionItem";

const OPTION = "flex flex-col gap-[1.7vh]";
const OPTION_TITLE = "text-[#6E6E6D] text-[2vh]";
const OPTION_CONTENT = "flex flex-col gap-[1vh] text-[2.3vh] font-bold ";

interface Props {
  title: string;
  items: { name: string; count: number | null; url: string }[];
}

const OptionSection = ({ title, items }: Props) => {
  return (
    <div className={OPTION}>
      <div className={OPTION_TITLE}>{title}</div>
      <div className={OPTION_CONTENT}>
        {items.map((item) => (
          <OptionItem key={item.name} {...item} />
        ))}
      </div>
    </div>
  );
};

export default OptionSection;
