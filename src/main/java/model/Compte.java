package model;

import java.math.BigDecimal;
import java.util.UUID;

public abstract sealed class Compte
        permits CompteCourant, CompteEpargne {

    private final Long id;
    private final String numero;
    private BigDecimal solde;
    private final Long idClient;

    protected Compte(Long id, String numero, BigDecimal solde, Long idClient) {
        this.id = id == null
                ? Math.abs(UUID.randomUUID().getMostSignificantBits())
                : id;
        this.numero = numero;
        this.solde = solde;
        this.idClient = idClient;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void deposer(BigDecimal montant) {
        verifierMontant(montant);
        solde = solde.add(montant);
    }

    public void retirer(BigDecimal montant) {
        verifierMontant(montant);

        var nouveauSolde = solde.subtract(montant);
        if (nouveauSolde.compareTo(soldeMinimumApresRetrait()) < 0) {
            throw new IllegalArgumentException("Solde insuffisant");
        }

        solde = nouveauSolde;
    }

    protected abstract BigDecimal soldeMinimumApresRetrait();

    public abstract String typeCompte();

    private void verifierMontant(BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant doit être supérieur à zéro");
        }
    }
}
