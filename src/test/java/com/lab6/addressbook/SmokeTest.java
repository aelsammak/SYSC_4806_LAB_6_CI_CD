package com.lab6.addressbook;

import com.lab6.addressbook.controller.AddressBookController;
import com.lab6.addressbook.controller.GUIController;
import com.lab6.addressbook.repository.AddressBookRepo;
import com.lab6.addressbook.service.AddressBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private AddressBookController addressBookController;

    @Autowired
    private GUIController guiController;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private AddressBookRepo addressBookRepo;

    @Test
    public void contextLoadsMainController() throws Exception {
        assertThat(addressBookController).isNotNull();
    }

    @Test
    public void contextLoadsGUIController() throws Exception {
        assertThat(guiController).isNotNull();
    }

    @Test
    public void contextLoadsService() throws Exception {
        assertThat(addressBookService).isNotNull();
    }

    @Test
    public void contextLoadsRepo() throws Exception {
        assertThat(addressBookRepo).isNotNull();
    }

}
