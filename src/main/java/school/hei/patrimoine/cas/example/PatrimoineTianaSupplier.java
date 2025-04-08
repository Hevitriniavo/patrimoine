package school.hei.patrimoine.cas.example;

import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;

import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

public class PatrimoineTianaSupplier implements Supplier<Patrimoine> {

    @Override
    public Patrimoine get() {
        var tiana = new Personne("Tiana");

        var dateDebut = LocalDate.of(2025, 4, 8);
        var dateFinProjet = LocalDate.of(2025, 12, 31);
        var dateFin = LocalDate.of(2026, 3, 31);

        var compteBancaire = new Compte("Compte bancaire", dateDebut, dateDebut, ariary(60_000_000));

        var terrain = new Materiel(
                "Terrain bâti",
                dateDebut,
                dateDebut,
                ariary(100_000_000),
                0.10
        );

        var depensesMensuelles = new FluxArgent(
                "Dépenses famille",
                compteBancaire,
                dateDebut,
                dateFin,
                1,
                ariary(-4_000_000)
        );

        var depensesProjet = new FluxArgent(
                "Dépenses projet",
                compteBancaire,
                LocalDate.of(2025, 6, 1),
                dateFinProjet,
                5,
                ariary(-5_000_000)
        );

        var revenuProjet1 = new FluxArgent(
                "Revenu projet 10%",
                compteBancaire,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 1),
                1,
                ariary(7_000_000)
        );
        var revenuProjet2 = new FluxArgent(
                "Revenu projet 90%",
                compteBancaire,
                LocalDate.of(2026, 1, 31),
                LocalDate.of(2026, 1, 31),
                31,
                ariary(63_000_000)
        );

        var pret = new FluxArgent(
                "Prêt bancaire",
                compteBancaire,
                LocalDate.of(2025, 7, 27),
                LocalDate.of(2025, 7, 27),
                27,
                ariary(20_000_000)
        );
        var remboursementPret = new FluxArgent(
                "Remboursement prêt",
                compteBancaire,
                LocalDate.of(2025, 8, 27),
                LocalDate.of(2026, 7, 27),
                27,
                ariary(-2_000_000)
        );

        return Patrimoine.of(
                "Patrimoine de Tiana",
                MGA,
                dateDebut,
                tiana,
                Set.of(compteBancaire, terrain, depensesMensuelles, depensesProjet, revenuProjet1, revenuProjet2, pret, remboursementPret)
        );
    }


}