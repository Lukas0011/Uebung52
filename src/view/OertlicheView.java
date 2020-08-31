package view;

import model.Entry;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class OertlicheView extends JFrame {

    private JComboBox<String> searchMethod = new JComboBox<>();
    private JTextField nameTF = new JTextField(10);
    private JTextField locationTF = new JTextField(10);
    private JTextField phoneNumberTF = new JTextField(10);

    private JButton search = new JButton("Search");

    private JList<String> results = new JList<>();
    private JList<String> information = new JList<>();

    private DefaultListModel<String> resultsModel = new DefaultListModel<>();
    private DefaultListModel<String> informationModel = new DefaultListModel<>();

    private JLabel nameL = new JLabel("Name");
    private JLabel districtL = new JLabel("Ort");
    private JLabel phoneNumberL = new JLabel("Telefonnummer");

    private Component[] gridComponents = new Component[]{nameL, districtL, phoneNumberL, new JLabel(), nameTF, locationTF, phoneNumberTF, search};

    public OertlicheView(String title) {
        super(title);

        add(buildTop(), BorderLayout.NORTH);
        add(buildCenter());


        changeMode(searchMethod.getItemAt(0));

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setActionListener(ActionListener l) {
        search.addActionListener(l);
    }

    public void setItemListener(ItemListener l) {
        searchMethod.addItemListener(l);
    }

    public JPanel buildTop() {
        JPanel temp = new JPanel(new FlowLayout());
        JLabel header = new JLabel("Das Örtliche");
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, 24));

        temp.add(header);

        return temp;
    }

    public JPanel buildCenter() {
        JPanel container = new JPanel(new GridLayout(0, 1));
        JPanel top = new JPanel(new GridLayout(0, 1));
        JPanel bottom = new JPanel(new FlowLayout());

        // TOP GRID TOP ROW
        JPanel topFlow = new JPanel(new FlowLayout());
        searchMethod.setModel(new DefaultComboBoxModel<>(new String[]{"Standardsuche", "Rückwärtssuche"}));
        topFlow.add(searchMethod);

        // TOP GRID BOTTOM ROW
        JPanel botGrid = new JPanel(new GridLayout(0, 4));
        for(Component c : gridComponents) {
            JPanel flow = new JPanel();
            flow.add(c);
            botGrid.add(flow);
        }

        // ------------------------------------------------------

        // LEFT SCROLLPANE
        results.setModel(resultsModel);
        results.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        results.setVisibleRowCount(8);

        JScrollPane leftScroll = new JScrollPane(results);
        leftScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leftScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // RIGHT SCROLLPANE
        information.setModel(informationModel);
        information.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        information.setVisibleRowCount(8);

        JScrollPane rightScroll = new JScrollPane(information);
        rightScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rightScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        top.add(topFlow);
        top.add(botGrid);

        bottom.add(leftScroll);
        bottom.add(rightScroll);

        container.add(top);
        container.add(bottom);

        return container;
    }

    public void changeMode(String mode) {
        if(mode.equals(searchMethod.getItemAt(0))) {
            nameTF.setEnabled(true);
            locationTF.setEnabled(true);

            phoneNumberTF.setEnabled(false);
            phoneNumberTF.setText(null);
        }

        if(mode.equals(searchMethod.getItemAt(1))) {
            nameTF.setEnabled(false);
            locationTF.setEnabled(false);
            nameTF.setText(null);
            locationTF.setText(null);

            phoneNumberTF.setEnabled(true);
        }
    }

    public void fillResults(Entry[] entries) {
        DefaultListModel<String> temp = new DefaultListModel<>();

        if(entries != null) {
            ArrayList<String> entryNames = new ArrayList<>();

            for (Entry e : entries) {
                entryNames.add(e.getName() + ", " + e.getStreet());
            }
            temp.addAll(entryNames);
        }

        results.setModel(temp);
    }

    public void fillInformation(String[] information) {
        DefaultListModel<String> temp = new DefaultListModel<>();

        if(information != null) {
            for(int i = 0; i < information.length; ++i) {
                temp.add(i, information[i]);
            }
        }


        this.information.setModel(temp);
    }

    public JButton getSearch() {
        return search;
    }

    public JTextField getNameTF() {
        return nameTF;
    }

    public JTextField getLocationTF() {
        return locationTF;
    }

    public JTextField getPhoneNumberTF() {
        return phoneNumberTF;
    }

    public String returnMode() {
        if(searchMethod.getSelectedIndex() == 0) {
            return searchMethod.getSelectedItem().toString();
        }

        if(searchMethod.getSelectedIndex() == 1) {
            return searchMethod.getSelectedItem().toString();
        }

        return null;
    }

    public JList<String> getResults() {
        return results;
    }

    public void setListSelectionListener(ListSelectionListener l) {
        results.addListSelectionListener(l);
    }


}
