import toast from "react-hot-toast";

export const getDate = (date: string) => {
  const now = new Date();
  const specificDate = new Date(date);
  now.setHours(0, 0, 0, 0);
  specificDate.setHours(0, 0, 0, 0);

  const diffInHours =
    (specificDate.getTime() - now.getTime()) / (1000 * 60 * 60);
  const diffInDays = diffInHours / 24;

  const rtf = new Intl.RelativeTimeFormat("ko-KR", { numeric: "always" });

  let relativeTime;

  if (Math.abs(diffInHours) < 24) {
    // 시간 단위로 변환
    relativeTime = rtf.format(Math.round(diffInHours), "hour");
  } else if (Math.abs(diffInDays) < 7) {
    // 일 단위로 변환
    relativeTime = rtf.format(Math.round(diffInDays), "day");
  } else if (Math.abs(diffInDays) < 28) {
    // 주 단위로 변환 (7일 이상, 28일 미만)
    relativeTime = rtf.format(Math.ceil(diffInDays / 7), "week");
  } else {
    // 4주가 지나면 날짜 형식으로 보여주기
    // 날짜 형식은 '년-월-일'로 표시
    relativeTime = specificDate.toLocaleDateString("ko-KR", {
      month: "2-digit",
      day: "2-digit",
    });
  }

  return relativeTime;
};

export const toastMsg = (msg: string) => toast(msg, { duration: 1500 });
