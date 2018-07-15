package ddc.kata.generic;

import java.io.Serializable;

/**
 * @author ddacosta 15/07/2018
 * @project KataTennis
 */
public interface Builder<U> extends Serializable {
    public U build();
}
