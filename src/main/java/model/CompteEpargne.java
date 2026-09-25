package model;

import java.math.BigDecimal;

public final class CompteEpargne extends Compte {
    private final BigDecimal tauxInteret;

    public CompteEpargne(Long id, String numero, BigDecimal solde, Long idClient,
                         BigDecimal tauxInteret) {
        super(id, numero, solde, idClient);
        this.tauxInteret = tauxInteret;
    }

    public BigDecimal getTauxInteret() {
        return tauxInteret;
    }

    @Override
    protected BigDecimal soldeMinimumApresRetrait() {
        return BigDecimal.ZERO;
    }

    @Override
    public String typeCompte() {
        return "EPARGNE";
    }
}
