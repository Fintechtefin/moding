import profileImg from "@assets/images/profileImg.webp";

interface Props {
  content: string;
}

const NoneData = ({ content = "" }: Props) => {
  return (
    <div className=" absolute inset-0 flex flex-col items-center justify-center gap-[3vh]">
      <img className="w-[20vh] h-[20vh] rounded-3xl" src={profileImg} alt="" />
      <div className=" text-textGray text-[3vh]">{content}</div>
    </div>
  );
};

export default NoneData;
