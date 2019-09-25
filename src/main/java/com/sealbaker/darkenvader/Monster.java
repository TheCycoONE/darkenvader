package com.sealbaker.darkenvader;

class Monster extends Actor
{
    String picFile;
    String type;

    public Monster(int m)
    {
        switch (m)
        {
            case 1:
                Angel();
                break;
            case 2:
                Umbra();
                break;
            default:
                Angel();
                break;
        }
    }

    private void Angel()
    {
        type = "Angel";
        spirit = 30;
        attack = 10;
        defence = 10;
        score = 10;
        picFile = "PAngel.gif";
    }

    private void Umbra()
    {
        type = "Umbra";
        spirit = 10;
        attack = 5;
        defence = 10;
        score = 5;
        picFile = "PUmbra.gif";
    }
}
