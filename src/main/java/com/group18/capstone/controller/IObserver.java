package com.group18.capstone.controller;

import java.util.UUID;

public interface IObserver {
    void update(UUID randomuuid, String emailAddress, String firstName);

}
