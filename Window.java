import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JFileChooser;
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
        save_item.addActionListener((e) -> {
            JFileChooser fc = new JFileChooser();
            int r = fc.showSaveDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){
                canvas.saveImg(fc.getSelectedFile().getAbsolutePath());
            }
        });
        file_menu.add(save_item);

        var canvas_menu = new JMenu("Canvas");
        var clear_item = new JMenuItem("Clear");
        clear_item.addActionListener((e) -> canvas.clear());
        canvas_menu.add(clear_item);

        var brush_menu = new JMenu("Brush color");
        var brush_group = new ButtonGroup();

        var brush7 = new JRadioButtonMenuItem("Black");
        brush7.addItemListener((e) -> {
            if(e.getStateChange()==ItemEvent.SELECTED)
                canvas.brush_index = 6;
        });
        var brush6 = new JRadioButtonMenuItem("White");
        brush6.addItemListener((e) -> {
            if(e.getStateChange()==ItemEvent.SELECTED)
                canvas.brush_index = 4;
        });
        var brush1 = new JRadioButtonMenuItem("Rainbow");
        brush1.setSelected(true);
        brush1.addItemListener((e) -> {
            if(e.getStateChange()==ItemEvent.SELECTED)
                canvas.brush_index = 0;
        });
        var brush2 = new JRadioButtonMenuItem("Red");
        brush2.addItemListener((e) -> {
            if(e.getStateChange()==ItemEvent.SELECTED)
                canvas.brush_index = 1;
        });
        var brush3 = new JRadioButtonMenuItem("Green");
        brush3.addItemListener((e) -> {
            if(e.getStateChange()==ItemEvent.SELECTED)
                canvas.brush_index = 2;
        });
        var brush4 = new JRadioButtonMenuItem("Blue");
        brush4.addItemListener((e) -> {
            if(e.getStateChange()==ItemEvent.SELECTED)
                canvas.brush_index = 3;
        });
        var brush5 = new JRadioButtonMenuItem("Black-white");
        brush5.addItemListener((e) -> {
            if(e.getStateChange()==ItemEvent.SELECTED)
                canvas.brush_index = 5;
        });

        brush_group.add(brush1);
        brush_group.add(brush2);
        brush_group.add(brush3);
        brush_group.add(brush4);
        brush_group.add(brush5);
        brush_group.add(brush6);
        brush_group.add(brush7);

        brush_menu.add(brush6);
        brush_menu.add(brush7);
        brush_menu.add(brush1);
        brush_menu.add(brush2);
        brush_menu.add(brush3);
        brush_menu.add(brush4);
        brush_menu.add(brush5);

        menu.add(file_menu);
        menu.add(canvas_menu);
        menu.add(brush_menu);
        setJMenuBar(menu);
    }
}
