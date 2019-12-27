import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.lang.Math;
import java.lang.Math;


public class Canvas extends JPanel implements MouseListener, MouseMotionListener{
    private ArrayList<Point> draw_queue = new ArrayList<Point>();

    int brushSize = 3;
    int brush_index = 0;
    int width, height;
    double half = 350;
    float frame = 0;

    BufferedImage buffer;
    Graphics2D canv; 

    public Canvas(){
        width = 700;
        height = 700;

        setPreferredSize(new Dimension(width,height));
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.BLACK);
        setOpaque(false);

        half = (int) width/2;

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setup_canv(buffer);
    }

    @Override
    protected void paintComponent(Graphics g){
        frame++;
        if(frame%1000 == 0)
            frame = 0;

        applyBrush(canv);

        if(draw_queue.size() > 1){
            double x,y,xx,yy;

            for(int i=0; i<360; i+=30){
                Point a = draw_queue.get(0);
                Point b = draw_queue.get(1);

                a = rotate(translate(a, -350), i);
                b = rotate(translate(b, -350), i);
                drawSeg(canv, translate(a, 350), translate(b, 350));

                a = mirror(a);
                b = mirror(b);
                drawSeg(canv, translate(a, 350), translate(b, 350));
            }

            draw_queue.remove(0);
        }

        super.paintComponents(g);
        var g2d = (Graphics2D) g;
        g2d.drawImage(buffer,0,0,null);
    }

    private void setup_canv(BufferedImage buffer){
        canv = buffer.createGraphics();
        canv.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        canv.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    private void applyBrush(Graphics2D canv){
        switch(brush_index){
            case 0:
                canv.setColor(Color.getHSBColor(frame/100,1,1));
                break;
            case 1:
                canv.setColor(Color.red);
                break;
            case 2:
                canv.setColor(Color.green);
                break;
            case 3:
                canv.setColor(Color.blue);
                break;
            case 4:
                canv.setColor(Color.white);
                break;
            case 5:
                canv.setColor(Color.getHSBColor(1,0,frame/100));
                break;
            case 6:
                canv.setColor(Color.black);
                break;
            default:
                canv.setColor(Color.white);
        }
    }

    public void chooseBrush(int n){
        brush_index = n;
    }

    private Point mirror(Point p){ 
        double x = -p.getX();
        double y = p.getY();
        return new Point((int)x, (int)y); 
    } 

    private Point rotate(Point p, double angle){ 
        angle = Math.toRadians(angle);
        double s = Math.sin(angle);
        double c = Math.cos(angle);
        double x = p.getX();
        double y = p.getY();

        double xn = x * c - y * s;
        double yn = x * s + y * c;

        return new Point((int)xn, (int)yn); 
    } 

    private Point translate(Point p, double n){
        double x = p.getX() + n;
        double y = p.getY() + n;

        return new Point((int)x, (int)y); 
    }

    private void drawSeg(Graphics canv, Point a, Point b){
        double x = a.getX();
        double y = a.getY();

        double xx = b.getX();
        double yy = b.getY();

        canv.drawLine((int)x,(int)y,(int)xx,(int)yy);
    }

    public void clear(){
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setup_canv(buffer);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        draw_queue.add(new Point(x,y));
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        draw_queue.add(new Point(x,y));
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        draw_queue = new ArrayList<Point>();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
