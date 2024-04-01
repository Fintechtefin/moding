package com.ssafy.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    private String line;
    private Integer col;

    @Override
    public boolean equals(Object o) {
        if (o instanceof Seat) {
            Seat s = (Seat) o;
            if (line.equals(s.getLine()) && col.equals(s.getCol())) {
                return true;
            }
        }
        return false;
    }
}
