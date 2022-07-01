package com.company.cinemaproject.screen.seat;

import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Seat;

@UiController("Seat.browse")
@UiDescriptor("seat-browse.xml")
@LookupComponent("seatsTable")
public class SeatBrowse extends StandardLookup<Seat> {
}