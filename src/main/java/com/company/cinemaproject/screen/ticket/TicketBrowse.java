package com.company.cinemaproject.screen.ticket;

import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Ticket;

@UiController("Ticket.browse")
@UiDescriptor("ticket-browse.xml")
@LookupComponent("ticketsTable")
public class TicketBrowse extends StandardLookup<Ticket> {
}