package model;

import java.util.UUID;

public record Client(Long id, String nom, String email) {

    public Client(String nom, String email) {
        this(Math.abs(UUID.randomUUID().getMostSignificantBits()), nom, email);
    }
}
