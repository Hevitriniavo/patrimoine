package school.hei.patrimoine.cas.example;

import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.TransfertArgent;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;

import static school.hei.patrimoine.modele.Argent.ariary;

public class PatrimoineBakoSupplier implements Supplier<Patrimoine> {

    @Override
    public Patrimoine get() {
        var bako = new Personne("Bako");

        var dateDebut = LocalDate.of(2025, 4, 8);
        var dateFin = LocalDate.of(2025, 12, 31);

        var compteBNI = new Compte("Compte courant BNI", dateDebut.minusDays(1), dateDebut, ariary(2_000_000));
        var compteBMOI = new Compte("Compte épargne BMOI", dateDebut.minusDays(1), dateDebut, ariary(625_000));
        var coffreFort = new Compte("Coffre-fort maison", dateDebut.minusDays(1), dateDebut, ariary(1_750_000));

        var salaire = new FluxArgent(
                "Salaire net",
                compteBNI,
                dateDebut,
                dateFin,
                2,
                ariary(2_125_000)
        );

        var virementEpargne = new TransfertArgent(
                "Épargne",
                compteBNI,
                compteBMOI,
                dateDebut.plusDays(1),
                dateFin,
                3,
                ariary(200_000)
        );

        var loyer = new FluxArgent(
                "Loyer colocation",
                compteBNI,
                dateDebut,
                dateFin,
                26,
                ariary(-600_000)
        );

        var depensesVie = new FluxArgent(
                "Dépenses de vie",
                compteBNI,
                dateDebut,
                dateFin,
                1,
                ariary(-700_000)
        );

        var ordinateur = new Materiel(
                "Ordinateur portable",
                dateDebut,
                dateDebut,
                ariary(3_000_000),
                -0.12
        );

        return Patrimoine.of(
                "Patrimoine de Bako",
                ariary(0).devise(),
                dateDebut,
                bako,
                Set.of(
                        compteBNI,
                        compteBMOI,
                        coffreFort,
                        salaire,
                        virementEpargne,
                        loyer,
                        depensesVie,
                        ordinateur
                )
        );
    }
}