package com.griddynamics.sqlutility.data;

import java.util.List;

public record Actor(String fullName, int id, List<Integer> movieIds) {

}
