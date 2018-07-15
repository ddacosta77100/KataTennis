package ddc.kata.score;

import ddc.kata.model.Player;
import ddc.kata.model.Point;

import java.util.Objects;

/**
 * @author ddacosta 15/07/2018
 * @project KataTennis
 */
public class Forty implements Score {

    final Player player;
    final Point otherPlayerPoint;

    public Forty(Player player, Point otherPlayerPoint) {
        this.player = player;
        this.otherPlayerPoint = otherPlayerPoint;
    }

    public Player getPlayer() {
        return player;
    }

    public Point getOtherPlayerPoint() {
        return otherPlayerPoint;
    }

    @Override
    public String getName() {
        return "Forty";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forty forty = (Forty) o;
        return Objects.equals(player, forty.player) &&
                otherPlayerPoint == forty.otherPlayerPoint;
    }

    @Override
    public int hashCode() {

        return Objects.hash(player, otherPlayerPoint);
    }

    @Override
    public String toString() {
        return "Forty{" +
                "player=" + player +
                ", otherPlayerPoint=" + otherPlayerPoint +
                '}';
    }
}
