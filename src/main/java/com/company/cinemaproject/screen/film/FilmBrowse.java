package com.company.cinemaproject.screen.film;

import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Film;

@UiController("Film.browse")
@UiDescriptor("film-browse.xml")
@LookupComponent("filmsTable")
public class FilmBrowse extends StandardLookup<Film> {
}