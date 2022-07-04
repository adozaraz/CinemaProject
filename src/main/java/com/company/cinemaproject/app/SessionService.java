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
        //uery("select ticket from Ticket ticket where ticket.session = :session")
        /*return dataManager.load(Boolean.class)
                .query("select true from Ticket ticket where ticket.session = :session and ticket.client is not null")
                .parameter("session", singleSelected).one();*/
        return dataManager.loadValue("select case " +
                                "when exists (select true from Ticket ticket where ticket.session = :session " +
                                "and ticket.client is not null) " +
                                "then true else false end from Ticket tickets",
                Boolean.class)
                .store("main")
                .parameter("session", singleSelected)
                .one();
    }
}