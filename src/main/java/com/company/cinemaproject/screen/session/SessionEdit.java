package com.company.cinemaproject.screen.session;

import io.jmix.ui.Notifications;
import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Session;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.temporal.ChronoUnit;

@UiController("Session_.edit")
@UiDescriptor("session-edit.xml")
@EditedEntityContainer("sessionDc")
public class SessionEdit extends StandardEditor<Session> {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SessionEdit.class);
    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Session editedSession = getEditedEntity();
        if (ChronoUnit.MINUTES.between(editedSession.getTimeStart(), editedSession.getTimeStop()) < getEditedEntity().getFilm().getDuration()) {
            notifications.create().withCaption("Session length cannot be less that film's length").show();
            event.preventCommit();
        } else {
            event.resume();
        }
    }
}