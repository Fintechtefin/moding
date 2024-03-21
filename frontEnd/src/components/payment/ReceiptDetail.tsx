interface Props {
  label: string;
  value: string;
}

const ReceiptDetail = ({ label, value }: Props) => {
  return (
    <div className="flex justify-between">
      <div className="text-[2vh]">{label}</div>
      <div className="font-bold text-[2vh]">{value}</div>
    </div>
  );
};

export default ReceiptDetail;
