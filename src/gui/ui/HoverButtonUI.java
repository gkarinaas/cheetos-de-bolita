package gui.ui;

import gui.ui.UIConstants;
import rpg.utils.cache.ImageCache;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class HoverButtonUI extends BasicButtonUI {

    protected int width;
    protected int height;
    protected ImageIcon[] parts;
    protected ImageIcon[] partsHover;

    @Override
    protected void installDefaults(AbstractButton b) {

        initParts();
        assert UIConstants.FONT != null; /** Verifica que FONT no sea nulo **/
        b.setFont(UIConstants.FONT.deriveFont(Font.PLAIN, 24));
        b.setForeground(Color.BLACK);
        b.setDoubleBuffered(true);
        b.setOpaque(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        b.setIconTextGap(5);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        String text = b.getText();
        this.width = b.getFontMetrics(b.getFont()).stringWidth(text) + (5);
        this.height = 48;
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {

        return new Dimension(Math.max(width + 54, 84), 48);
    }

    @Override
    public Dimension getMaximumSize(JComponent c) {
        return getPreferredSize(c);
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        return getPreferredSize(c);
    }

    /**Inicializa las partes del botón en sus estados normal y hover**/
    protected void initParts() {

        /**Inicializa los arreglos de imágenes **/
        parts = new ImageIcon[3];
        partsHover = new ImageIcon[3];

        /** Agrega las imágenes a la caché **/
        ImageCache.addImage("userLeftSide", "corazon.png");
        ImageCache.addImage("userCenterSide", "corazon.png");
        ImageCache.addImage("userRightSide", "corazon.png");
        ImageCache.addImage("userHoverLeftSide", "corazon.png");
        ImageCache.addImage("userHoverCenterSide", "corazon.png");
        ImageCache.addImage("userHoverRightSide", "corazon.png");

        /** Obtenemos las imágenes de la caché y las almacenamos en los arreglos correspondientes **/
        parts[0] = ImageCache.getImageIcon("userLeftSide");
        parts[1] = ImageCache.getImageIcon("userCenterSide");
        parts[2] = ImageCache.getImageIcon("userRightSide");
        partsHover[0] = ImageCache.getImageIcon("userHoverLeftSide");
        partsHover[1] = ImageCache.getImageIcon("userHoverCenterSide");
        partsHover[2] = ImageCache.getImageIcon("userHoverRightSide");
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        Graphics2D g2d = (Graphics2D) g;
        AbstractButton button = (AbstractButton) c;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ImageIcon[] images = button.getModel().isRollover() ? partsHover : parts;
        drawButtonParts(g2d, images);
        g2d.translate(-width - 27, 0);
        g2d.setColor(Color.WHITE);
        super.paint(g2d, c);
    }

    protected void drawButtonParts(Graphics2D g2d, ImageIcon[] parts) {

        g2d.drawImage(parts[0].getImage(), 0, 0, null);
        g2d.translate(27, 0);
        g2d.drawImage(parts[1].getImage(), 0, 0,
                width, height, null);
        g2d.translate(width, 0);
        g2d.drawImage(parts[2].getImage(), 0, 0, null);
    }
}