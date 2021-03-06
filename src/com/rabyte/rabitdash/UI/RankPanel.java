package com.rabyte.rabitdash.UI;

import com.rabyte.rabitdash.util.Constants;
import com.rabyte.rabitdash.util.RankList;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class RankPanel {
    private JTable table1;
    public JPanel panel1;

    private void createUIComponents() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("NAME");
        columnNames.add("SCORE");
        Vector<Vector<String>> a = new Vector();
        a.addAll(RankList.getRankList());
        table1 = new JTable(a, columnNames);
        // TODO: place custom component creation code here
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, BorderLayout.NORTH);
        table1.putClientProperty("html.disable", Boolean.TRUE);
        table1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        scrollPane1.setViewportView(table1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
