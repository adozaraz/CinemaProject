package com.company.cinemaproject.screen.session;

import com.company.cinemaproject.entity.Ticket;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("SessionSeats.browse")
@UiDescriptor("session-seats-browse.xml")
@LookupComponent("sessionsTable")
public class SessionSeatsBrowse extends StandardLookup<Session> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private GroupTable<Session> sessionsTable;

    @Install(to = "ticketsTable.isFree", subject = "columnGenerator")
    private Component ticketsTableIsFreeColumnGenerator(Ticket ticket) {
        CheckBox isFree = uiComponents.create(CheckBox.class);
        isFree.setValue(ticket.getClient() == null);
        return isFree;
    }

    @Autowired
    private CollectionLoader<Ticket> ticketsDl;

    @Subscribe("sessionsTable")
    public void onSessionsTableSelection(Table.SelectionEvent<Session> event) {
        if (sessionsTable.getSingleSelected() != null) {
            ticketsDl.setParameter("session", sessionsTable.getSingleSelected());
        } else {
            ticketsDl.removeParameter("session");
        }
        ticketsDl.load();
    }
}