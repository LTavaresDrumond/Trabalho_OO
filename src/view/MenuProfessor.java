package view;

import app.Aluno;
import app.Professor;
import cadastros.CadastroAluno;
import cadastros.CadastroProfessor;

import javax.swing.*;

public class MenuProfessor {

    public static Professor dadosNovoprofessor() {
        String nome = lerNome();
        String cpf = lerCPF();
        String email = lerEmail();
        String matriculaFUB = lerMatriculaFUB();
        String areaFormacao = lerAreaFormacao();
        return new Professor(nome, cpf, email, areaFormacao, matriculaFUB);
    }

    private static String lerAreaFormacao() {
        return JOptionPane.showInputDialog("Informe a área de formação do professor: ");
    }

    private static String lerEmail() {
        return JOptionPane.showInputDialog("Informe o email do professor: ");
    }

    private static String lerCPF() {
        return JOptionPane.showInputDialog("Informe o CPF do professor: ");
    }

    private static String lerNome() {
        return JOptionPane.showInputDialog("Informe o nome do professor: ");
    }

    private static String lerMatriculaFUB() {
        return JOptionPane.showInputDialog("Informe a matriculaFUB do professor: ");
    }

    public static void menuProfessor(CadastroProfessor cadProfessor) {
        String txt = """
                Informe a opção desejada:
                1 - Cadastrar professor
                2 - Pesquisar professor
                3 - Atualizar professor
                4 - Remover professor
                0 - Voltar para menu anterior
                """;

        int opcao = -1;
        do {
            String strOpcao = JOptionPane.showInputDialog(txt);
            opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 1:
                    Professor novoProfessor = dadosNovoprofessor();
                    cadProfessor.cadastrarProfessor(novoProfessor);
                    break;

                case 2:
                    String matriculaFUB = lerMatriculaFUB();
                    Professor a = cadProfessor.pesquisarProfessor(matriculaFUB);
                    if (a != null)
                        JOptionPane.showMessageDialog(null, a.toString());
                    break;

                case 3:
                    matriculaFUB = lerMatriculaFUB();
                    Professor novoCadastro = dadosNovoprofessor();
                    boolean atualizado = cadProfessor.atualizarProfessor(matriculaFUB, novoCadastro);
                    if (atualizado) {
                        JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
                    }
                    break;

                case 4:
                    matriculaFUB = lerMatriculaFUB();
                    Professor remover = cadProfessor.pesquisarProfessor(matriculaFUB);
                    boolean removido = cadProfessor.removerProfessor(remover);
                    if (removido) {
                        JOptionPane.showMessageDialog(null, "Professor removido do cadastro");
                        System.gc();
                    }

                default:
                    break;
            }
        } while (opcao != 0);
        return;
    }
}
