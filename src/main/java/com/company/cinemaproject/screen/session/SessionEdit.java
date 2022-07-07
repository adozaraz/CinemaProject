package com.company.cinemaproject.screen.session;

import com.company.cinemaproject.app.SessionService;
import io.jmix.core.Messages;
import io.jmix.ui.Notifications;
import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.temporal.ChronoUnit;

@UiController("Session_.edit")
@UiDescriptor("session-edit.xml")
@EditedEntityContainer("sessionDc")
public class SessionEdit extends StandardEditor<Session> {
    @Autowired
    private Notifications notifications;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private Messages messages;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Session editedSession = getEditedEntity();
        if (ChronoUnit.MINUTES.between(editedSession.getTimeStart(), editedSession.getTimeStop()) < getEditedEntity().getFilm().getDuration()) {
            notifications.create().withCaption(messages.getMessage("sessionLengthError")).show();
            event.preventCommit();
        } else {
            boolean toAdd = sessionService.checkIfSessionExists(getEditedEntity().getId());
            event.resume();
            if (toAdd) {
                sessionService.createTicketsForSession(getEditedEntity());
            }
        }
    }
}