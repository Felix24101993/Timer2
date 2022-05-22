import javax.swing.*;

public class OptionsPane {
    public void Init(){
        String[] responses = {"Settings", "Close"};

        var optionsPane = JOptionPane.showOptionDialog(null,
                "Choose option",
                "Option Dialog",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, responses, 0);

        if (optionsPane == JOptionPane.YES_OPTION)
        {
            JFrame frame = new FirstWindow("My Timer");
        }

        if(optionsPane == JOptionPane.NO_OPTION)
        {
            JOptionPane.getRootFrame().dispose();
        }
    }
}
