package view;

private void carregarTabela() {

    DefaultTableModel tabela =
        (DefaultTableModel) jTableProdutos.getModel();

    tabela.setRowCount(0);

    ProdutoDAO dao = new ProdutoDAO();

    List<Produto> produtos = dao.listar();

    for (Produto produto : produtos) {

        tabela.addRow(new Object[]{
            produto.getNome(),
            String.format("%.2f", produto.getPreco()),
            produto.getEstoque()
        });
    }
}


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;

import dao.ProdutoDAO;
import model.Produto;

public class TelaCadastro extends JFrame {

    private JTextField jTextFieldNome;
    private JTextField jTextFieldPreco;
    private JTextField jTextFieldEstoque;
    private JButton jButtonCadastrar;

    public TelaCadastro() {

        setTitle("Cadastro de Produto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        jTextFieldNome = new JTextField();
        jTextFieldPreco = new JTextField();
        jTextFieldEstoque = new JTextField();

        jButtonCadastrar = new JButton("Cadastrar");

        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Nome:"));
        add(jTextFieldNome);

        add(new JLabel("Preço:"));
        add(jTextFieldPreco);

        add(new JLabel("Estoque:"));
        add(jTextFieldEstoque);

        add(new JLabel(""));
        add(jButtonCadastrar);

        jButtonCadastrar.addActionListener(e -> jButtonCadastrarActionPerformed());
    }

    private void jButtonCadastrarActionPerformed() {

        try {

            String nome = jTextFieldNome.getText();

            double preco = Double.parseDouble(
                jTextFieldPreco.getText()
            );

            int estoque = Integer.parseInt(
                jTextFieldEstoque.getText()
            );

            Produto produto = new Produto();

            produto.setNome(nome);
            produto.setPreco(preco);
            produto.setEstoque(estoque);

            ProdutoDAO dao = new ProdutoDAO();

            dao.cadastrar(produto);

            JOptionPane.showMessageDialog(
                this,
                "Produto cadastrado com sucesso!"
            );

            jTextFieldNome.setText("");
            jTextFieldPreco.setText("");
            jTextFieldEstoque.setText("");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Preço e estoque devem ser números!"
            );
        }
    }
}
