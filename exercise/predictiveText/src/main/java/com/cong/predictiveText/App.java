package com.cong.predictiveText;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

public class App {
    private static final int MAX_ITEM_DISPLAY = 10;
    private JTextField txtInput;
    private JPanel panelMain;
    private JButton btnSearch;
    private JScrollPane scrollListPredictive;
    private JList listPredictiveText;
    private JComboBox cbxSearchMethod;

    public App() {

        prepareGUI();

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String key = (listPredictiveText.getSelectedIndex() == -1) ? txtInput.getText() : listPredictiveText.getSelectedValue().toString();
                if (key.equals("") == false) {
                    DictionaryList dictionaryList = new DictionaryList();

                    if (cbxSearchMethod.getSelectedIndex() == 1) {
                        dictionaryList.setDictionary(BloomFilter.getInstance());
                    } else {
                        dictionaryList.setDictionary(Trie.getInstance());
                    }

                    String method = cbxSearchMethod.getSelectedItem().toString();

                    long startTime = System.nanoTime();
                    boolean checkExist = dictionaryList.contains(key);
                    long endTime = System.nanoTime();
                    long benchmark = endTime - startTime;


                    if (checkExist) {
                        JOptionPane.showMessageDialog(null, key + " exist by " + method + " in " + benchmark + " ns");
                    } else {
                        JOptionPane.showMessageDialog(null, key + " does not exist by " + method + " in " + benchmark + " ns");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Input not null");
                }
            }
        });

        txtInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if (txtInput.getText().equals("") == false) {
                    listPredictiveText.setEnabled(true);
                    List<String> list = Trie.getInstance().query(txtInput.getText());
                    int maxItemDisplay = list.size() > MAX_ITEM_DISPLAY ? MAX_ITEM_DISPLAY : list.size();
                    listPredictiveText.setListData(list.subList(0, maxItemDisplay).toArray());
                } else {
                    listPredictiveText.setEnabled(false);
                    listPredictiveText.setListData(new String[]{""});
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Predictive Text");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
    }

    private void prepareGUI() {
        listPredictiveText.setEnabled(false);
        listPredictiveText.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPredictiveText.setLayoutOrientation(JList.VERTICAL);

        cbxSearchMethod.addItem("Trie");
        cbxSearchMethod.addItem("Bloom filter");

        scrollListPredictive.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollListPredictive.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {
            DataReader.read(System.getProperty("user.dir") + "/blogs");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}