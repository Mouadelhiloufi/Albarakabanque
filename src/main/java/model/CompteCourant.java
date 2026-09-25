package model;

import java.math.BigDecimal;

public final class CompteCourant extends Compte {
    private final BigDecimal decouvertAutorise;

    public CompteCourant(Long id, String numero, BigDecimal solde, Long idClient,
                         BigDecimal decouvertAutorise) {
        super(id, numero, solde, idClient);
        this.decouvertAutorise = decouvertAutorise;
    }

    public BigDecimal getDecouvertAutorise() {
        return decouvertAutorise;
    }

    @Override
    protected BigDecimal soldeMinimumApresRetrait() {
        return decouvertAutorise.negate();
    }

    @Override
    public String typeCompte() {
        return "COURANT";
    }
}
