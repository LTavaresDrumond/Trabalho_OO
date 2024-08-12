package view;

import app.Aluno;
import app.Disciplina;
import app.Professor;
import app.Turma;
import cadastros.CadastroAluno;
import cadastros.CadastroDisciplina;
import cadastros.CadastroProfessor;
import cadastros.CadastroTurma;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuTurma {

    static CadastroProfessor cadastroProfessor = new CadastroProfessor();
    static CadastroDisciplina cadastroDisciplina = new CadastroDisciplina();
    static CadastroAluno cadastroAluno = new CadastroAluno();

    public static Turma dadosNovaTurma() {
        String diaHora = lerDiaHora();
        String codigo = lerCodigo();
        String semestre = lerSemestre();
        int numVagas = lerNumVagas();
        Professor professor = lerProfessor();
        Disciplina disciplina = lerDisciplina();
        List<Aluno> alunos = lerAluno(numVagas);

        return new Turma(codigo, diaHora, semestre, numVagas, professor, disciplina, alunos);
    }

    private static String lerCodigo() {
        return JOptionPane.showInputDialog("Informe o código da turma: ");
    }

    private static int lerNumVagas() {
        return Integer.parseInt(JOptionPane.showInputDialog("Informe o número de vagas da turma: "));
    }

    private static String lerSemestre() {
        return JOptionPane.showInputDialog("Informe o semestre da turma: ");
    }

    private static String lerDiaHora() {
        return JOptionPane.showInputDialog("Informe o dia/Hora da turma: ");
    }

    private static Professor lerProfessor() {
        String matriculaFUB = JOptionPane.showInputDialog("Informe a matrícula do professor(a):\n " + cadastroProfessor.toString());
        Professor professor = cadastroProfessor.pesquisarProfessor(matriculaFUB);
        return professor;
    }

    private static Disciplina lerDisciplina() {
        String codigoDisciplina = JOptionPane.showInputDialog("Informe o código da disciplina:\n " + cadastroDisciplina.toString());
        Disciplina disciplina = cadastroDisciplina.pesquisarDisciplina(codigoDisciplina);
        return disciplina;
    }

    private static List<Aluno> lerAluno(int numVagas) {
        List<Aluno> alunos = new ArrayList<>();
        int vagasDisponiveis = numVagas;
        for (int i = 0; i < numVagas; i++) {
            String matriculaAluno = JOptionPane.showInputDialog("Informe a matrícula do aluno: há "
                            + vagasDisponiveis + " vagas!\n" + cadastroAluno.toString());

            if (matriculaAluno.isEmpty()) {
                break;
            }
            Aluno aluno = cadastroAluno.pesquisarAluno(matriculaAluno);
            if (aluno == null) {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                i--;
            } else if(alunos.contains(aluno)){
                JOptionPane.showMessageDialog(null, "Aluno já matriculado.");
                i--;
            }else {
                alunos.add(aluno);
                vagasDisponiveis--;
            }
        }
        return alunos;
    }



    public static void menuTurma(CadastroTurma cadTurma) {
        String txt = """
                Informe a opção desejada:
                1 - Cadastrar Turma
                2 - Pesquisar Turma
                3 - Atualizar Turma
                4 - Remover Turma
                5 - Imprimir lista de presença
                0 - Voltar para menu anterior
                """;

        int opcao = -1;
        do {
            String strOpcao = JOptionPane.showInputDialog(txt);
            opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 1:
                    Turma novaTurma = dadosNovaTurma();
                    cadTurma.cadastrarTurma(novaTurma);
                    break;

                case 2:
                    String codigo = lerCodigo();
                    Turma t = cadTurma.pesquisarTurma(codigo);
                    if (t != null)
                        JOptionPane.showMessageDialog(null, t.toString());
                    break;

                case 3:
                    codigo = lerCodigo();
                    Turma novoCadastro = dadosNovaTurma();
                    boolean atualizado = cadTurma.atualizarTurma(codigo, novoCadastro);
                    if (atualizado) {
                        JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
                    }
                    break;

                case 4:
                    codigo = lerCodigo();
                    Turma remover = cadTurma.pesquisarTurma(codigo);
                    boolean removido = cadTurma.removerTurma(remover);
                    if (removido) {
                        JOptionPane.showMessageDialog(null, "Turma removida do cadastro");
                        System.gc();
                    }
                    break;
                case 5:
                    codigo = lerCodigo();
                    cadTurma.imprimirListaPresenca(codigo);
                default:
                    break;
            }
        } while (opcao != 0);
        return;
    }
}
