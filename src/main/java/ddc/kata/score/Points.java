package ddc.kata.score;

import ddc.kata.model.Point;

import java.util.Objects;

/**
 * @author ddacosta 15/07/2018
 * @project KataTennis
 */
public class Points implements Score {

    final Point playerOnePoint;
    final Point playerTwoPoint;

    public Points(Point playerOnePoint, Point playerTwoPoint) {
        this.playerOnePoint = playerOnePoint;
        this.playerTwoPoint = playerTwoPoint;
    }

    public Point getPlayerOnePoint() {
        return playerOnePoint;
    }

    public Point getPlayerTwoPoint() {
        return playerTwoPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Points points = (Points) o;
        return playerOnePoint == points.playerOnePoint &&
                playerTwoPoint == points.playerTwoPoint;
    }

    @Override
    public int hashCode() {

        return Objects.hash(playerOnePoint, playerTwoPoint);
    }

    @Override
    public String toString() {
        return "Points{" +
                "playerOnePoint=" + playerOnePoint +
                ", playerTwoPoint=" + playerTwoPoint +
                '}';
    }

    @Override
    public String getName() {
        return "Points";
    }
}
