package com.company.cinemaproject.screen.session;

import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Session;

@UiController("Session_.browse")
@UiDescriptor("session-browse.xml")
@LookupComponent("sessionsTable")
public class SessionBrowse extends StandardLookup<Session> {
}