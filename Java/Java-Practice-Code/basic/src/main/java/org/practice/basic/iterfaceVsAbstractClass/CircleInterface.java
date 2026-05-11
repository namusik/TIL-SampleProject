package org.practice.basic.iterfaceVsAbstractClass;

import java.util.Arrays;
import java.util.List;

public interface CircleInterface {
    List<String> allowedColors = Arrays.asList("RED", "GREEN", "BLUE");

    String getColor();

    default boolean isValid() {
        return allowedColors.contains(getColor());
    }

}
