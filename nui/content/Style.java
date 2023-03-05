package content;

import java.awt.*;

public class Style {

    private final Color[] palette = new Color[4];

    public Style(Styles style) {
        switch (style) {
            case DEFAULT -> {
                palette[0] = Color.GRAY;
                palette[1] = Color.LIGHT_GRAY;
                palette[2] = Color.decode("#ebebeb");
                palette[3] = Color.WHITE;
            }
            case FARM -> {
                palette[0] = Color.decode("#F7F1E5");
                palette[1] = Color.decode("#E7B10A");
                palette[2] = Color.decode("#898121");
                palette[3] = Color.decode("#4C4B16");
            }
            case MEADOW -> {
                palette[0] = Color.decode("#EDF1D6");
                palette[1] = Color.decode("#9DC08B");
                palette[2] = Color.decode("#609966");
                palette[3] = Color.decode("#40513B");
            }
            case END_CRYSTAL -> {
                palette[0] = Color.decode("#191825");
                palette[1] = Color.decode("#865DFF");
                palette[2] = Color.decode("#E384FF");
                palette[3] = Color.decode("#FFA3FD");
            }
            case OBSIDIAN -> {
                palette[0] = Color.decode("#635985");
                palette[1] = Color.decode("#443C68");
                palette[2] = Color.decode("#393053");
                palette[3] = Color.decode("#18122B");
            }
            case MODERN -> {
                palette[0] = Color.decode("#F0EEED");
                palette[1] = Color.decode("#609EA2");
                palette[2] = Color.decode("#C92C6D");
                palette[3] = Color.decode("#332C39");
            }
            case TAIGA -> {
                palette[0] = Color.decode("#698269");
                palette[1] = Color.decode("#B99B6B");
                palette[2] = Color.decode("#F1DBBF");
                palette[3] = Color.decode("#AA5656");
            }
            case ROMAN -> {
                palette[0] = Color.decode("#ECF9FF");
                palette[1] = Color.decode("#FFFBEB");
                palette[2] = Color.decode("#FFE7CC");
                palette[3] = Color.decode("#F8CBA6");
            }
            case DARK_SEA -> {
                palette[0] = Color.decode("#13005A");
                palette[1] = Color.decode("#00337C");
                palette[2] = Color.decode("#1C82AD");
                palette[3] = Color.decode("#03C988");
            }
        }
    }

    public Color getColor(int layer) {
        if (layer > 3)
            throw new IllegalArgumentException("Layer " + layer + " is out of available layers, only 0-3 layers are allowed");
        return palette[layer];
    }
}
