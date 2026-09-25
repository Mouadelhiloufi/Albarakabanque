package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(
        Long id,
        LocalDateTime date,
        BigDecimal montant,
        TypeTransaction type,
        String lieu,
        Long idCompte
) {
    public Transaction {
    }

    public Transaction(LocalDateTime date, BigDecimal montant, TypeTransaction type,
                       String lieu, Long idCompte) {
        this(null, date, montant, type, lieu, idCompte);
    }
}
