/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuan7.Membership.src.view.member;

/**
 *
 * @author LEGION
 */

import pertemuan7.Membership.src.model.Member;
import javax.swing.table.AbstractTableModel; // Pastikan impor ini benar
import java.util.List;

class MemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Jenis Member"};
    private List<Member> data;

    public MemberTableModel(List<Member> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Member rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
            case 1:
                value = rowItem.getJenisMember().getNama();
                break;
        }
        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false; // Sel tidak dapat diedit
    }

    public void add(Member value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}