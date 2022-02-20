package com.lab6.addressbook.service;

import com.lab6.addressbook.model.BuddyInfoModel;

import java.util.List;

public interface AddressBookService {
    void saveBuddy(String addressBookName, BuddyInfoModel buddyInfoModel);
    void removeBuddy(String addressBookName, String firstName);
    void updateBuddyAddress(String addressBookName, String name, String newAddress);
    List<BuddyInfoModel> getBuddies(String addressBookName);
}
