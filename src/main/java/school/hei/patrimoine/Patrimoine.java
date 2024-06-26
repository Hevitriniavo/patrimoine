package school.hei.patrimoine;

import school.hei.patrimoine.possession.Argent;
import school.hei.patrimoine.possession.Possession;

import java.time.Instant;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public record Patrimoine(Personne possesseur, Instant t, Set<Possession> possessions) {

    public int getValeurComptable() {
        if (possessions.isEmpty()) {
            return 0;
        }
        return possessions.stream()
                .mapToInt(Possession::getValeurComptable)
                .sum();
    }


    public Patrimoine projectionFuture(Instant tFutur) {
        return new Patrimoine(
                possesseur,
                tFutur,
                possessions.stream().map(p -> p.projectionFuture(tFutur)).collect(toSet()));
    }

    public int getMontantCompteCourant() {
        for (var possession :possessions()) {
            if (possession instanceof Argent && "Compte Courant".equals(possession.getNom())) {
                return possession.getValeurComptable();
            }
        }
        return 0;
    }
}
