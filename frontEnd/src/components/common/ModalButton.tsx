interface Props {
  func: () => void;
  type: boolean;
  title: string;
}

const ModalButton = ({ func, type, title }: Props) => {
  const confirm = type ? "bg-red-600" : "bg-white text-black";

  return (
    <button onClick={func} className={`flex-1 p-[1.5vh] rounded-lg ${confirm}`}>
      {title}
    </button>
  );
};

export default ModalButton;
