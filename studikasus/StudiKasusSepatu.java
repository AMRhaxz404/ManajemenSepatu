package studikasus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudiKasusSepatu {
    private static GudangSepatu gudangSepatu = new GudangSepatu();
    private static Admin admin = new Admin("amrin", "amrin");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowLoginGUI());
    }

    private static void createAndShowLoginGUI() {
        JFrame loginFrame = new JFrame("Login Admin");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(0, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                String enteredPassword = new String(passwordField.getPassword());

                if (admin.getUsername().equals(enteredUsername) && admin.getPassword().equals(enteredPassword)) {
                    loginFrame.dispose();
                    createAndShowMainGUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau Password salah. Coba lagi.");
                }
            }
        });

        loginFrame.getContentPane().add(loginPanel);
        loginFrame.setSize(300, 150);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    // MAIN GUI
    private static void createAndShowMainGUI() {
        JFrame frame = new JFrame("Management Gudang Sepatu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        JButton tambahButton = new JButton("Tambah Sepatu");
        JButton lihatButton = new JButton("Lihat Informasi Sepatu");
        JButton editButton = new JButton("Edit Stok dan Informasi");
        JButton cariNamaButton = new JButton("Cari Sepatu by Nama");
        JButton cariMerkButton = new JButton("Cari Sepatu by Merk");
        JButton lihatStokButton = new JButton("Lihat Jumlah Stok");
        JButton hapusButton = new JButton("Hapus Sepatu");

        panel.add(tambahButton);
        panel.add(lihatButton);
        panel.add(editButton);
        panel.add(cariNamaButton);
        panel.add(cariMerkButton);
        panel.add(lihatStokButton);
        panel.add(hapusButton);

        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahSepatuDialog();
            }
        });

        lihatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lihatInformasiDialog();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStokDialog();
            }
        });

        cariNamaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cariSepatuByNamaDialog();
            }
        });

        cariMerkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cariSepatuByMerkDialog();
            }
        });

        lihatStokButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lihatJumlahStokDialog();
            }
        });

        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusSepatuDialog();
            }
        });

        frame.getContentPane().add(panel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void tambahSepatuDialog() {
        JTextField namaField = new JTextField();
        JTextField merkField = new JTextField();
        JTextField stokField = new JTextField();

        Object[] message = {
                "Nama:", namaField,
                "Merk:", merkField,
                "Stok:", stokField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Tambah Sepatu", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nama = namaField.getText();
                String merk = merkField.getText();
                int stok = Integer.parseInt(stokField.getText());

                Sepatu sepatu = new Sepatu(nama, merk, stok);
                gudangSepatu.tambahSepatu(sepatu);

                JOptionPane.showMessageDialog(null, "Sepatu berhasil ditambahkan!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Masukkan stok dengan format angka!");
            }
        }
    }

    private static void lihatInformasiDialog() {
        List<Sepatu> listSepatu = gudangSepatu.lihatInformasiSepatu();
        StringBuilder message = new StringBuilder("Informasi Sepatu:\n");

        for (Sepatu sepatu : listSepatu) {
            message.append("Nama: ").append(sepatu.getNama()).append(", Merk: ").append(sepatu.getMerk())
                    .append(", Stok: ").append(sepatu.getStok()).append("\n");
        }

        JOptionPane.showMessageDialog(null, message.toString());
    }

    private static void editStokDialog() {
        JTextField namaField = new JTextField();
        JTextField merkField = new JTextField();
        JTextField stokField = new JTextField();

        Object[] message = {
                "Nama:", namaField,
                "Merk (kosongkan jika tidak ingin mengubah):", merkField,
                "Stok Baru:", stokField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Edit Sepatu", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nama = namaField.getText();
                String merk = merkField.getText();
                int stokBaru = Integer.parseInt(stokField.getText());

                if (!nama.isEmpty()) {
                    Sepatu sepatu = gudangSepatu.getSepatu(nama);

                    if (sepatu != null) {
                        if (!merk.isEmpty()) {
                            sepatu.setMerk(merk);
                        }
                        sepatu.setStok(stokBaru);

                        JOptionPane.showMessageDialog(null, "Informasi sepatu berhasil diubah!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Sepatu dengan nama tersebut tidak ditemukan!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nama sepatu tidak boleh kosong!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Masukkan stok dengan format angka!");
            }
        }
    }

    private static void cariSepatuByNamaDialog() {
        JTextField namaField = new JTextField();

        Object[] message = {
                "Nama Sepatu:", namaField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Cari Sepatu by Nama", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nama = namaField.getText();

            List<Sepatu> result = gudangSepatu.cariSepatuByNama(nama);
            displaySearchResult(result);
        }
    }

    private static void cariSepatuByMerkDialog() {
        JTextField merkField = new JTextField();

        Object[] message = {
                "Merk Sepatu:", merkField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Cari Sepatu by Merk", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String merk = merkField.getText();

            List<Sepatu> result = gudangSepatu.cariSepatuByMerk(merk);
            displaySearchResult(result);
        }
    }

    private static void displaySearchResult(List<Sepatu> result) {
        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sepatu tidak ditemukan!");
        } else {
            StringBuilder message = new StringBuilder("Hasil Pencarian:\n");

            for (Sepatu sepatu : result) {
                message.append("Nama: ").append(sepatu.getNama()).append(", Merk: ").append(sepatu.getMerk())
                        .append(", Stok: ").append(sepatu.getStok()).append("\n");
            }

            JOptionPane.showMessageDialog(null, message.toString());
        }
    }

    private static void lihatJumlahStokDialog() {
        int totalStok = gudangSepatu.lihatJumlahStok();
        JOptionPane.showMessageDialog(null, "Jumlah Stok Sepatu: " + totalStok);
    }

    private static void hapusSepatuDialog() {
        JTextField namaField = new JTextField();

        Object[] message = {
                "Nama Sepatu:", namaField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Hapus Sepatu", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nama = namaField.getText();

            try {
                gudangSepatu.hapusSepatu(nama);
                JOptionPane.showMessageDialog(null, "Sepatu berhasil dihapus!");
            } catch (SepatuNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
