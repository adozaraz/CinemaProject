package com.company.cinemaproject.screen.client;

import io.jmix.ui.screen.*;
import com.company.cinemaproject.entity.Client;

@UiController("Client.edit")
@UiDescriptor("client-edit.xml")
@EditedEntityContainer("clientDc")
public class ClientEdit extends StandardEditor<Client> {
}