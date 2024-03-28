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

export function formatDateWithDay(dateString: string) {
  const date = new Date(dateString);
  const formattedDate = date.toLocaleDateString("ko-KR", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    weekday: "short",
  });

  return formattedDate.replace(/(\d{4})\. (\d{2})\. (\d{2})\./, "$1.$2.$3");
}

export function addMinutesToTime(time: string, minutesToAdd: number) {
  // 문자열 형태의 시간을 Date 객체로 파싱
  let timeParts = time.split(":");
  let date = new Date();
  date.setHours(parseInt(timeParts[0]), parseInt(timeParts[1]), 0, 0);

  // 분 추가
  date.setMinutes(date.getMinutes() + minutesToAdd);

  // 시간을 HH:MM 형식의 문자열로 포맷
  let hours = date.getHours().toString().padStart(2, "0");
  let minutes = date.getMinutes().toString().padStart(2, "0");

  return `${hours}:${minutes}`;
}

export const toastMsg = (msg: string) => toast(msg, { duration: 1500 });
