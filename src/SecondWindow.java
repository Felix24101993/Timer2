import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SecondWindow extends JFrame {
    public Boolean IsOpened;
    public Timer Timer;
    public boolean DidTimerExecute;


    public SecondWindow(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.IsOpened= true;
    }

    public void ChangeColor(Color Color, int frequency)
    {
        var thisWindow = this;

        var t = new java.util.Timer();
        var tTask = new TimerTask() {
        @Override
        public void run() {
            if(DidTimerExecute==true){
                thisWindow.getContentPane().setBackground(new Color(0,0,0));
                DidTimerExecute = false;
            }else{
                thisWindow.getContentPane().setBackground(Color);
                DidTimerExecute = true;
            }
        }
        };
        t.schedule(tTask, 0,frequency*1000);
    }
}
