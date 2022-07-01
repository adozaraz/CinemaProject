package com.company.cinemaproject.screen.theater;

import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Theater;

@UiController("Theater.browse")
@UiDescriptor("theater-browse.xml")
@LookupComponent("theatersTable")
public class TheaterBrowse extends StandardLookup<Theater> {
}