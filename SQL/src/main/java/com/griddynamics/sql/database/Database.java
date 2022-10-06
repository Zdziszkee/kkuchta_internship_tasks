package com.griddynamics.sql.database;

import java.sql.Connection;

public interface Database {

    Connection connect(String username, String password);

}
