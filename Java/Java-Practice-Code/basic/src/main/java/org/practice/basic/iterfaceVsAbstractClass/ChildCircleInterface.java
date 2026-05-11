package org.practice.basic.iterfaceVsAbstractClass;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChildCircleInterface implements CircleInterface{
    private String color;
    @Override
    public String getColor() {
        return this.color;
    }
}
