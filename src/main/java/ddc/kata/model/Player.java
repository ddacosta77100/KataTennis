package ddc.kata.model;

import ddc.kata.generic.Builder;

import java.util.Objects;
import java.util.UUID;

/**
 * @author ddacosta 15/07/2018
 * @project KataTennis
 */
public class Player {

    private Player(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.kind = builder.kind;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public PlayerKind getKind() {
        return kind;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name) &&
                kind == player.kind;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, kind);
    }

    private final String id;
    private final String name;
    private final PlayerKind kind;


    /*===========================================================================*/
    /* INNER CLASS                                                              */
    /*==========================================================================*/
    public static class Builder implements ddc.kata.generic.Builder<Player> {

        public Builder(final String name, PlayerKind kind) {
            id = UUID.randomUUID().toString();
            this.name = name;
            this.kind = kind;
        }

        @Override
        public Player build() {
            return new Player(this);
        }

        private final String id;
        private final String name;
        private final PlayerKind kind;
    }
}
