package com.griddynamics.sqlutility.model;

import java.util.List;

public record Actor(String fullName, int id, List<Integer> movieIds) {

}
