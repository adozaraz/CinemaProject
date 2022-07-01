package com.company.cinemaproject.screen.theater;

import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Theater;

@UiController("Theater.edit")
@UiDescriptor("theater-edit.xml")
@EditedEntityContainer("theaterDc")
public class TheaterEdit extends StandardEditor<Theater> {
}