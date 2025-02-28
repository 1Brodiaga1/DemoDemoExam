package ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class AppStyles {
    // Цвета из руководства по стилю
    public static final Color PRIMARY_BG = Color.WHITE;
    public static final Color SECONDARY_BG = new Color(244, 232, 211); // #F4E8D3
    public static final Color ACCENT_COLOR = new Color(103, 186, 128); // #67BA80

    // Шрифты
    public static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font REGULAR_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font SMALL_FONT = new Font("Segoe UI", Font.PLAIN, 12);

    // Границы
    public static final Border PANEL_BORDER = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
    public static final int PADDING = 10;
}