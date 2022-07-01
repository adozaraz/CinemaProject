package com.company.cinemaproject.screen.seat;

import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Seat;

@UiController("Seat.edit")
@UiDescriptor("seat-edit.xml")
@EditedEntityContainer("seatDc")
public class SeatEdit extends StandardEditor<Seat> {
}