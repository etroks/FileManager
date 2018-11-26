package fileManager.view;

import fileManager.model.Button;
import fileManager.model.Model;
import fileManager.utils.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.showMessageDialog;

public class DirectoryView implements View {

    private final JFrame jFrame;
    private Map<JButton, Button> buttonsMap = new HashMap<>();
    private JButton buttonBack;
    private JLabel label1, label2, label3, label4, label5, label6;
    private JPanel jPanel;
    private boolean initialization = true;
    private Model model;

    public DirectoryView(Model model) throws InterruptedException {
        this.model = model;
        this.jFrame = new JFrame("FileManager");
        this.jPanel = new JPanel();
        initComponents();
        //this.jFrame.setVisible(true);
    }


    public void initComponents() {
        jPanel.setLayout(null);
        jPanel.setSize(1280, 620);
        jPanel.setLocation(0, 70);
        jPanel.setOpaque(false);

        jFrame.setContentPane(new BackgroundInit());
        jFrame.setLayout(null);//без компановки
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//отрубать все нахуй крестиком
        jFrame.setSize(1280, 720);
        jFrame.setResizable(false);//стабильный размер окошка
        jFrame.setLocationRelativeTo(null);//окошко в центре
        jFrame.getContentPane().add(jPanel);

        label1 = labelInit(Color.WHITE, "Выберите директорию:", 20, 20, 200, 20);
        buttonBack = button1InitOnFrame(420, 20, 100, 20, "Назад");
        refreshDirectory();
    }

    public void refreshDirectory() {
        del(buttonsMap);
        for (int i = 0; i < model.getPaths().size(); i++) {
            Path path = model.getPaths().get(i);
            if (i < 30)
                buttonsMap.put(button1InitOnPanel(20, i * 20, 500, 15,
                        path.getFileName().toString()),
                        new Button(path.getFileName().toString(),
                                path.getParent().toString() + "\\" + path.getFileName().toString()));
            else
                buttonsMap.put(button1InitOnPanel(550, (i - 30) * 20, 500, 15,
                        path.getFileName().toString()),
                        new Button(path.getFileName().toString(),
                                path.getParent().toString() + "\\" + path.getFileName().toString()));
        }

        this.jPanel.updateUI();
        if (initialization) {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            jPanel.setVisible(true);
            jFrame.setVisible(true);
            initialization = false;
        }
    }

    public class eHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonBack) {
                try {
                    model.setPaths(model.getPaths().get(0).getParent().getParent().toString());
                    refreshDirectory();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            Button button = null;
            for (JButton jButton : buttonsMap.keySet()) {
                if (e.getSource() == jButton) {
                    button = buttonsMap.get(jButton);
                    break;
                }
            }
            if (button != null) {
                try {
                    if (Files.isDirectory(Paths.get(button.getPath()))) {
                        model.setPaths(button.getPath());
                        refreshDirectory();
                    } else {
                        showMessageDialog(jPanel,
                                FileUtils.getReadingStrategy(button.getPath()).readFile(Paths.get(button.getPath())));
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }//считывает нажатия на кнопки

    class BackgroundInit extends JPanel {
        public void paintComponent(Graphics g) {
            Image im = null;
            try {
                im = ImageIO.read(getClass().getClassLoader().getResource("background.jpg"));
            } catch (IOException e) {
            }
            g.drawImage(im, 0, 0, null);
        }
    }//устанавливает фоновое изображение

    private JLabel labelInit(Color color, String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setLocation(x, y);
        label.setSize(width, height);
        label.setForeground(color);
        jFrame.add(label);
        return label;
    }//создает надпись

    private JButton button1InitOnPanel(/*ImageIcon icon1, */int x, int y, int width, int height, String textButton) {
        JButton button = new JButton(/*icon1*/);
        button.setLocation(x, y);
        button.setSize(width, height);
        button.addActionListener(new eHandler());
        button.setText(textButton);
        button.setBorderPainted(false);
        jPanel.add(button);
        return button;
    }//создает кнопку на панели

    private JButton button1InitOnFrame(/*ImageIcon icon1, */int x, int y, int width, int height, String textButton) {
        JButton button = new JButton(/*icon1*/);
        button.setLocation(x, y);
        button.setSize(width, height);
        button.addActionListener(new eHandler());
        button.setText(textButton);
        button.setBorderPainted(false);
        jFrame.add(button);
        return button;
    }//создает кнопку в окне

    private void del(Map<JButton, Button> buttonsMap) {
        buttonsMap.clear();
        jPanel.removeAll();
    }
}
