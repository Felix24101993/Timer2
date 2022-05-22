import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FirstWindow extends JFrame {
    private JPanel mainPanel;
    private JRadioButton OnTimeButton;
    private JRadioButton CountdownButton;
    private JTextField CountdownField;
    private JButton ChooseButton;
    private JLabel CurrentTimeLabel;
    private JLabel SpeedLabel;
    private JComboBox ScrollBox;
    private JButton StartButton;
    private JButton StopButton;
    private JFormattedTextField OnTimeField;
    private Color ColorForSecondWindow;
    private SecondWindow SecondWindow;
    private int SecondWindowFrequency;
    private Date SecondWindowExpireDate;
    private int TimeOut;

    public FirstWindow(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
        this.SecondWindowFrequency=Integer.parseInt(ScrollBox.getSelectedItem().toString());
        this.AddEventForChooseButton();
        this.AddEventForStartButton();
        this.AddEventForOnTimeButton();
        this.AddEventForCountdownButton();
        this.AddEventOnScrollButton();
        this.getContentPane().setBackground(Color.orange);
    }

    private void AddEventForStartButton(){
        var thisWindow = this;

        StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TimeUnit.SECONDS.sleep(TimeOut);
                } catch (InterruptedException error) {
                    error.printStackTrace();
                }
                SecondWindow = new SecondWindow("Second Window");
                SecondWindow.setSize(500,500);
                SecondWindow.setVisible(true);
                thisWindow.EnableButtons(false);
                SecondWindow.ChangeColor(ColorForSecondWindow, SecondWindowFrequency);

                SecondWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        SecondWindow.dispose();
                        SecondWindow = null;
                        thisWindow.EnableButtons(true);
                    }
                });

                StopButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(SecondWindow!=null){
                            SecondWindow.dispose();
                            SecondWindow = null;
                            thisWindow.EnableButtons(true);
                        }
                    }
                });
            }
        });
    }

    private void AddEventForChooseButton(){
        ChooseButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser jcc = new JColorChooser();
                Color color = jcc.showDialog(null, "Choose background color", Color.yellow);
                if(color != null) {
                    ColorForSecondWindow = color;
                }
            }
        });
    }

    private void AddEventForOnTimeButton(){
        OnTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnTimeField.requestFocus(true);
                CountdownButton.setSelected(false);
            }
        });

        OnTimeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
    private void createUIComponents() {
        OnTimeField = new JFormattedTextField(new SimpleDateFormat("HH:mm:ss"));
    }

    private void AddEventForCountdownButton(){
        CountdownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CountdownField.requestFocus(true);
                OnTimeButton.setSelected(false);

            }
        });

        CountdownField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    CountdownField.setEditable(true);
                } else {
                    CountdownField.setEditable(false);
                }
            }
        });

        CountdownField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if(CountdownField.isEditable()){
                    TimeOut = Integer.parseInt(CountdownField.getText());
                }
            }
        });
    }

    private void AddEventOnScrollButton(){
        ScrollBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SecondWindowFrequency = Integer.parseInt(ScrollBox.getSelectedItem().toString());
            }
        });
    }

    private void EnableButtons(boolean shouldEnable){
        ChooseButton.setEnabled(shouldEnable);
        StartButton.setEnabled(shouldEnable);
        OnTimeButton.setEnabled(shouldEnable);
        CountdownButton.setEnabled(shouldEnable);
        OnTimeField.setEnabled(shouldEnable);
        CountdownField.setEnabled(shouldEnable);
        CurrentTimeLabel.setEnabled(shouldEnable);
        SpeedLabel.setEnabled(shouldEnable);
        ScrollBox.setEnabled(shouldEnable);
        StopButton.setEnabled(!shouldEnable);
    }
}
