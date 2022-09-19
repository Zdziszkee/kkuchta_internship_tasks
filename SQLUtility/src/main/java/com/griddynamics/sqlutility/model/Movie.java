package com.griddynamics.sqlutility.model;

import java.time.LocalDate;
import java.util.List;

public record Movie(int id, LocalDate released, String name, long income, List<Integer> actorsIds) {

}
