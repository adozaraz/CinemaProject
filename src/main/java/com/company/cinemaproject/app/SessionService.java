package com.company.cinemaproject.app;

import com.company.cinemaproject.entity.Seat;
import com.company.cinemaproject.entity.Session;
import com.company.cinemaproject.entity.Theater;
import com.company.cinemaproject.entity.Ticket;
import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SessionService.class);
    @Autowired
    private DataManager dataManager;

    public void createTicketsForSession(Session session) {
        List<Seat> seats = dataManager.load(Seat.class)
                .query("select seat from Seat seat where seat.theater = :theater")
                .parameter("theater", session.getTheater())
                .list();
        SaveContext saveContext = new SaveContext();
        for (Seat seat : seats) {
            Ticket ticket = dataManager.create(Ticket.class);
            ticket.setSession(session);
            ticket.setSeat(seat);
            ticket.setCost(seat.getDefaultCost());
            saveContext.saving(ticket);
        }
        dataManager.save(saveContext);
    }

    public boolean isAnyTicketBought(Session singleSelected) {
        List<Ticket> tickets = dataManager.load(Ticket.class)
                .query("select ticket from Ticket ticket where ticket.session = :session")
                .parameter("session", singleSelected)
                .list();
        for (Ticket ticket : tickets) {
            log.info(String.valueOf(ticket.getClient()));
            if (ticket.getClient() != null) {
                return true;
            }
        }
        return false;
    }
}