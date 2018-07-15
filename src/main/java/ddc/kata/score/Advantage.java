package ddc.kata.score;

import ddc.kata.model.Player;

import java.util.Objects;

/**
 * @author ddacosta 15/07/2018
 * @project KataTennis
 */
public class Advantage implements Score {

    final Player player;

    public Advantage(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advantage advantage = (Advantage) o;
        return Objects.equals(player, advantage.player);
    }

    @Override
    public int hashCode() {

        return Objects.hash(player);
    }

    @Override
    public String toString() {
        return "Advantage{" +
                "player=" + player +
                '}';
    }

    @Override
    public String getName() {
        return "Advantage";
    }
}
