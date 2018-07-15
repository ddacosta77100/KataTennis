package ddc.kata.score;

import java.util.Objects;
import java.util.UUID;

/**
 * @author ddacosta 15/07/2018
 * @project KataTennis
 */
public class Deuce implements Score {

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && o.getClass().equals(getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Deuce";
    }

    @Override
    public String getName() {
        return "Deuce";
    }
}
