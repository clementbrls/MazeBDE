package ui.button;
import ui.DrawMaze;
import ui.FrameUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class ButtonPanel extends JPanel implements ChangeListener {
    public static final Insets buttonMargin = new Insets(10,10,10,10);
    public static final Color colorDefault = new Color(241, 243, 244);
    private final ArrivalButton arrivalButton;
    private final DepartureButton departureButton;
    private final EmptyButton emptyButton;
    private final WallButton wallButton;
    public ButtonPanel(FrameUI frame){
        //setLayout(new BorderLayout());
        frame.getModel().addObserver(this);
        add(departureButton = new DepartureButton(frame,this));
        add(arrivalButton = new ArrivalButton(frame,this));
        add(emptyButton = new EmptyButton(frame,this));
        add(wallButton = new WallButton(frame,this));
        stateChanged(null);
    }


    protected Font getFontButton(){
        Font font;
        if (getWidth() < 200){
            font = new Font("Verdana",Font.PLAIN,4);
        } else if(getWidth()<300){
            font = new Font("Verdana",Font.PLAIN,6);
        } else if(getWidth()<400){
            font = new Font("Verdana",Font.PLAIN,10);
        } else if(getWidth()<500){
            font = new Font("Verdana",Font.PLAIN,12);
        } else if(getWidth()<600){
            font = new Font("Verdana",Font.PLAIN,14);
        } else if(getWidth()<700){
            font = new Font("Verdana",Font.PLAIN,16);
        } else if(getWidth()<800){
            font = new Font("Verdana",Font.PLAIN,18);
        } else if(getWidth()<900){
            font = new Font("Verdana",Font.PLAIN,20);
        } else if(getWidth()<1000){
            font = new Font("Verdana",Font.PLAIN,22);
        } else if(getWidth()<1100){
            font = new Font("Verdana",Font.PLAIN,24);
        } else if(getWidth()<1200){
            font = new Font("Verdana",Font.PLAIN,26);
        } else if(getWidth()<1300){
            font = new Font("Verdana",Font.PLAIN,28);
        } else if(getWidth()<1400){
            font = new Font("Verdana",Font.PLAIN,30);
        } else if(getWidth()<1500){
            font = new Font("Verdana",Font.PLAIN,32);
        } else if(getWidth()<1600){
            font = new Font("Verdana",Font.PLAIN,34);
        } else if(getWidth()<1700){
            font = new Font("Verdana",Font.PLAIN,36);
        } else if(getWidth()<1800){
            font = new Font("Verdana",Font.PLAIN,38);
        } else if(getWidth()<1900){
            font = new Font("Verdana",Font.PLAIN,40);
        } else if(getWidth()<2000){
            font = new Font("Verdana",Font.PLAIN,42);
        } else if(getWidth()<2100){
            font = new Font("Verdana",Font.PLAIN,44);
        } else if(getWidth()<2200){
            font = new Font("Verdana",Font.PLAIN,46);
        } else if(getWidth()<2300){
            font = new Font("Verdana",Font.PLAIN,48);
        } else {
            font = new Font("Verdana",Font.PLAIN,50);
        } //Merci Copilote
        

        return font;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        departureButton.stateChanged();
        arrivalButton.stateChanged();
        emptyButton.stateChanged();
        wallButton.stateChanged();
    }

    public void paintComponent(Graphics g){//permet de modifier la taille des boutons en fonction de la taille de la fenetre
        stateChanged(null);
    }
}
