import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class Window extends JFrame{
    Canvas canvas;
    public Window(){
        initUI();
    }

    private void initUI(){
        createTopMenu();
        
        canvas = new Canvas();

        add(canvas);

        setTitle("Kaleidoscope");
        setSize(800,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.BLACK);

        pack();
    }

    private void createTopMenu(){
        var menu = new JMenuBar();

        var file_menu = new JMenu("File");
        var save_item = new JMenuItem("Save");
        var exit_item = new JMenuItem("Exit");
        exit_item.addActionListener((e) -> System.exit(0));
        file_menu.add(exit_item);
        file_menu.add(save_item);

        var canvas_menu = new JMenu("Canvas");
        var clear_item = new JMenuItem("Clear");
        clear_item.addActionListener((e) -> canvas.clear());
        var guidelines_item = new JRadioButtonMenuItem("Guidelines");
        guidelines_item.addActionListener((e) -> canvas.clear());
        canvas_menu.add(clear_item);
        canvas_menu.add(guidelines_item);

        var brush_menu = new JMenu("Brush");
        var brush_group = new ButtonGroup();

        var brush1 = new JRadioButtonMenuItem("Rainbow");
        var brush2 = new JRadioButtonMenuItem("Red");
        var brush3 = new JRadioButtonMenuItem("Blue");
        var brush4 = new JRadioButtonMenuItem("Green");
        var brush5 = new JRadioButtonMenuItem("Black-white");

        brush_group.add(brush1);
        brush_group.add(brush2);

        brush_menu.add(brush1);
        brush_menu.add(brush2);

        menu.add(file_menu);
        menu.add(canvas_menu);
        menu.add(brush_menu);
        setJMenuBar(menu);
    }
}
