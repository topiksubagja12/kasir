package form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import Database.DatabaseConnection;

public class Transaksi extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public Transaksi() {
        setTitle("Data Transaksi");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{
                "ID", "ID Barang", "Nama", "Harga", "Jumlah", "Subtotal", "Tanggal"
        }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        loadData();
        setVisible(true);
    }

    private void loadData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transak");

            while (rs.next()) {
                int id = rs.getInt("id_transaksi");
                int idBarang = rs.getInt("id_barang");
                String nama = rs.getString("nama_barang");
                int harga = rs.getInt("harga");
                int jumlah = rs.getInt("jumlah");
                int subtotal = rs.getInt("subtotal");
                Timestamp tanggal = rs.getTimestamp("tanggal");

                tableModel.addRow(new Object[]{id, idBarang, nama, harga, jumlah, subtotal, tanggal});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Transaksi::new);
    }
}