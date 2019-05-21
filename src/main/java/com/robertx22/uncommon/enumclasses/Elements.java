package com.robertx22.uncommon.enumclasses;

public enum Elements {
    None(0, new RGB(0, 0, 0)), Fire(1, new RGB(255, 0, 0)), Water(2, new RGB(0, 128, 255)), Thunder(3, new RGB(255, 255, 0)), Nature(4, new RGB(0, 204, 0));

    public RGB color;

    Elements(int i, RGB color) {
        this.i = i;
        this.color = color;
    }

    public int i = 0;

    public static Elements byNumber(int i) {

        if (i == 1) {
            return Fire;
        } else if (i == 2) {
            return Water;
        } else if (i == 3) {
            return Thunder;
        } else if (i == 4) {
            return Nature;
        }
        return None;
    }

    public static class RGB {
        RGB(int R, int G, int B) {
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
    }

}
