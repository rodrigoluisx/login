package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.util.List;

import dao.ProdutoDAO;
import model.Produto;

public class TelaCadastro extends JFrame {

    private JTextField jTextFieldNome;
    private JTextField jTextFieldPreco;
    private JTextField jTextFieldEstoque;

    private JButton jButtonCadastrar;
    private JButton jButtonExcluir;
    private JButton jButtonAtualizar;

    private JTable jTableProdutos;

    private List<Produto> produtos;

    public TelaCadastro() {

        setTitle("Cadastro de Produto");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        jTextFieldNome = new JTextField();
        jTextFieldPreco = new JTextField();
        jTextFieldEstoque = new JTextField();

        jButtonCadastrar = new JButton("Cadastrar");
        jButtonExcluir = new JButton("Excluir");
        jButtonAtualizar = new JButton("Atualizar");

        jTableProdutos = new JTable();

        DefaultTableModel modelo = new DefaultTableModel(
            new Object[][] {},
            new String[] {"Nome", "Preço", "Estoque"}
        );

        jTableProdutos.setModel(modelo);

        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Nome:"));
        add(jTextFieldNome);

        add(new JLabel("Preço:"));
        add(jTextFieldPreco);

        add(new JLabel("Estoque:"));
        add(jTextFieldEstoque);

        add(new JLabel(""));
        add(jButtonCadastrar);

        add(new JLabel(""));
        add(jButtonExcluir);

        add(new JLabel(""));
        add(jButtonAtualizar);

        add(new JLabel("Produtos cadastrados:"));
        add(new JScrollPane(jTableProdutos));

        jButtonCadastrar.addActionListener(
            e -> jButtonCadastrarActionPerformed()
        );

        jButtonExcluir.addActionListener(
            e -> ExcluirActionPerformed()
        );

        jButtonAtualizar.addActionListener(
            e -> AtualizarActionPerformed()
        );

        carregarTabela();
    }

    private void jButtonCadastrarActionPerformed() {

        try {

            String nome = jTextFieldNome.getText().trim();

            double preco = Double.parseDouble(
                jTextFieldPreco.getText().trim()
            );

            int estoque = Integer.parseInt(
                jTextFieldEstoque.getText().trim()
            );

            if (nome.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Preencha o nome do produto."
                );

                return;
            }

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

            limparCampos();

            carregarTabela();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Preço deve ser um número decimal e estoque deve ser um número inteiro."
            );
        }
    }

    private void ExcluirActionPerformed() {

        int linha = jTableProdutos.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Selecione um produto para excluir."
            );

            return;
        }

        Produto produto = produtos.get(linha);

        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir o produto "
                + produto.getNome() + "?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {

            ProdutoDAO dao = new ProdutoDAO();

            dao.excluir(produto.getId());

            JOptionPane.showMessageDialog(
                this,
                "Produto excluído com sucesso!"
            );

            limparCampos();

            carregarTabela();
        }
    }

    private void AtualizarActionPerformed() {

        int linha = jTableProdutos.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Selecione um produto para atualizar."
            );

            return;
        }

        String nome = jTextFieldNome.getText().trim();
        String precoTexto = jTextFieldPreco.getText().trim();
        String estoqueTexto = jTextFieldEstoque.getText().trim();

        if (nome.isEmpty()
                || precoTexto.isEmpty()
                || estoqueTexto.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Preencha todos os campos."
            );

            return;
        }

        try {

            double preco = Double.parseDouble(precoTexto);

            int estoque = Integer.parseInt(estoqueTexto);

            Produto produto = produtos.get(linha);

            produto.setNome(nome);
            produto.setPreco(preco);
            produto.setEstoque(estoque);

            ProdutoDAO dao = new ProdutoDAO();

            dao.atualizar(produto);

            JOptionPane.showMessageDialog(
                this,
                "Produto atualizado com sucesso!"
            );

            limparCampos();

            carregarTabela();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Preço deve ser um número decimal e estoque deve ser um número inteiro."
            );
        }
    }

    private void carregarTabela() {

        DefaultTableModel tabela =
            (DefaultTableModel) jTableProdutos.getModel();

        tabela.setRowCount(0);

        ProdutoDAO dao = new ProdutoDAO();

        produtos = dao.listar();

        for (Produto produto : produtos) {

            tabela.addRow(new Object[]{
                produto.getNome(),
                String.format("%.2f", produto.getPreco()),
                produto.getEstoque()
            });
        }
    }

    private void limparCampos() {

        jTextFieldNome.setText("");
        jTextFieldPreco.setText("");
        jTextFieldEstoque.setText("");
    }
}
