package com.robertx22.uncommon.enumclasses;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Elements implements IColor {
    None(0, new RGB(0, 0, 0), false),
    Fire(1, new RGB(255, 0, 0), true),
    Water(2, new RGB(0, 128, 255), true),
    Thunder(3, new RGB(255, 255, 0), true),
    Nature(4, new RGB(0, 204, 0), true),
    All(5, new RGB(0, 0, 0), false);

    public boolean isSingleElement = true;
    private RGB color;

    Elements(int i, RGB color, boolean isSingleElement) {
        this.i = i;
        this.color = color;
        this.isSingleElement = isSingleElement;
    }

    public int i = 0;

    public static List<Elements> getAllSingleElements() {

        return Arrays.stream(Elements.values())
                .filter(x -> x.isSingleElement)
                .collect(Collectors.toList());

    }

    public static List<Elements> getAllExceptNone() {

        return Arrays.stream(Elements.values())
                .filter(x -> x.isSingleElement || x == Elements.All)
                .collect(Collectors.toList());

    }

    @Override
    public RGB getRGBColor() {
        return color;
    }

    public static class RGB {
        public RGB(int R, int G, int B) {
            this.R = R;
            this.G = G;
            this.B = B;
        }

        private int R;
        private int G;
        private int B;

        public float getR() {
            return R / 255.0F; // you need to divide by 255 for the color to work as intended..
        }

        public float getG() {
            return G / 255.0F;
        }

        public float getB() {
            return B / 255.0F;
        }

        public int getIntR() {
            return R;
        }

        public int getIntG() {
            return G;
        }

        public int getIntB() {
            return B;
        }
    }

}
