package controller;

import model.Entry;
import model.OertlicheModel;
import view.OertlicheView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OertlicheController implements ActionListener, ItemListener, ListSelectionListener {

    private OertlicheView v;
    private OertlicheModel m;

    public OertlicheController() {
        v = new OertlicheView("Yes");
        m = new OertlicheModel();

        v.setActionListener(this);
        v.setItemListener(this);
        v.setListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(v.getSearch().getActionCommand())) {
            Entry[] entries = m.search(v.returnMode(), v.getNameTF().getText(), v.getLocationTF().getText(), v.getPhoneNumberTF().getText());
            if(entries != null) {
                v.fillResults(entries);
            } else {
                v.fillResults(null);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            v.changeMode(e.getItem().toString());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {
                System.out.println(v.getResults().getSelectedValue());
                v.fillInformation(m.entryInformation(v.getResults().getSelectedValue()));
        }
    }
}
