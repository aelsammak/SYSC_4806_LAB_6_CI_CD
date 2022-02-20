package com.lab6.addressbook;


import com.lab6.addressbook.model.BuddyInfoModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class featureTest {

    private BuddyInfoModel buddyInfoModel;

    @Test
    public void addressBookFieldTest() {
        buddyInfoModel = new BuddyInfoModel("adi", "194 blohm dr", "613 561 1234");
        assertEquals("abm1", buddyInfoModel.getAddressBookName());
        buddyInfoModel.setAddressBookName("addressbook1");
        assertEquals("addressbook1", buddyInfoModel.getAddressBookName());
    }
}
