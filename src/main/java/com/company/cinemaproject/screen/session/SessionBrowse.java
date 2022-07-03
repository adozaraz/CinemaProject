package com.company.cinemaproject.screen.session;

import com.company.cinemaproject.app.SessionService;
import io.jmix.ui.action.list.RemoveAction;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@UiController("Session_.browse")
@UiDescriptor("session-browse.xml")
@LookupComponent("sessionsTable")
public class SessionBrowse extends StandardLookup<Session> {
    @Autowired
    private Button removeBtn;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private GroupTable<Session> sessionsTable;
    @Named("sessionsTable.remove")
    private RemoveAction<Session> sessionsTableRemove;

    @Subscribe("sessionsTable")
    public void onSessionsTableSelection(Table.SelectionEvent<Session> event) {
        if (sessionService.isAnyTicketBought(sessionsTable.getSingleSelected())) {
            removeBtn.setEnabled(false);
            sessionsTableRemove.setEnabled(false);
        }
    }
}