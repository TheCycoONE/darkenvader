package com.sealbaker.darkenvader;

// Darkenvader
// An adventure
// by Stephen E. Baker

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Darkenvader
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            JFrame c = new Console();
            c.pack();
            c.setResizable(false);
            c.setVisible(true);
        });
    }
}
