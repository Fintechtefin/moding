import { useState } from "react";
import tt from "@assets/images/moviePost.png";

import "@assets/styles/Ticket.scss";
import TicketBack from "./TicketBack";

const Ticket = () => {
  const [isFlipped, setIsFlipped] = useState(false);

  const handleClick = () => {
    setIsFlipped((prev) => !prev);
  };

  return (
    <div className="card-container w-[42vh] h-[77vh] " onClick={handleClick}>
      <div className={`card ${isFlipped ? "is-flipped" : ""}`}>
        <img
          className="card-face card-front ticket-mask w-[45vh] h-[77vh]"
          src={tt}
        />
        <TicketBack />
      </div>
    </div>
  );
};

export default Ticket;
