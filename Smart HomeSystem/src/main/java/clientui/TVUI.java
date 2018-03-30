package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.TVClient;

public class TVUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton warm;
    private final TVClient parent;

    public TVUI(TVClient tvClient) {
        super(tvClient);
        parent = tvClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        warm = new JButton("Warm");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{warm});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == warm) {
            parent.warm();
        }
    }
}
