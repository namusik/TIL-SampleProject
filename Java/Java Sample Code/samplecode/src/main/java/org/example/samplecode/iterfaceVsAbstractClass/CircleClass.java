package org.example.samplecode.iterfaceVsAbstractClass;

import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public abstract class CircleClass {
    private String color;
    private List<String> allowedColors = Arrays.asList("RED", "GREEN", "BLUE");

    public boolean isValid() {
        return allowedColors.contains(getColor());
    }

    public void changeColor() {
        setColor("GREEN");
    }
}
