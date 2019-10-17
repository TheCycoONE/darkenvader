package com.sealbaker.darkenvader.monsters;

public class MonsterFactory {
    public String picFile;
    public String type;

    public static Monster create(int m) {
        switch (m) {
        case 1:
            return new Angel();
        case 2:
            return new Umbra();
        default:
            return new Angel();
        }
    }
}
