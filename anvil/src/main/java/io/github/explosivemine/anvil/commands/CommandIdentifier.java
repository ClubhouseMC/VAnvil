package io.github.explosivemine.anvil.commands;

import lombok.Getter;

public enum CommandIdentifier {
    ANVIL("vanvil");

    @Getter private final String label;

    CommandIdentifier(String label) {
        this.label = label;
    }

}