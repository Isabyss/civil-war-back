package com.basementinteractive.civilwar.resource.model;

import lombok.Getter;

@Getter
public enum ResourceType {
    WOOD("Wood"),
    ORE("Ore"),
    FOOD("Food");

    private final String description;

    ResourceType(String description) {
        this.description = description;
    }
}

