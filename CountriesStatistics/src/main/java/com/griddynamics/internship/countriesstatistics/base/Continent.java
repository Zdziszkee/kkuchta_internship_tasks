package com.griddynamics.internship.countriesstatistics.base;

import com.griddynamics.internship.countriesstatistics.base.exceptions.InvalidContinentException;

public enum Continent {
    ASIA("azja"), AFRICA("afryka"), NORTH_AMERICA("ameryka północna"), SOUTH_AMERICA("ameryka południowa"), ANTARCTICA("antarktyda"), EUROPE(
            "europa"), AUSTRALIA("australia i oceania");
    private final String alias;

    Continent(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public static Continent getContinentByAlias(String alias) {
        for (Continent continent : values()) {
            if (continent.getAlias()
                         .equalsIgnoreCase(alias)) {
                return continent;
            }
        }
        throw new InvalidContinentException();
    }
}
