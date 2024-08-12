package view;

import javax.swing.JOptionPane;

public class MenuPrincipal {

	public static int menuOpcoes() {
		String opcoes = "1 - Abrir cadastro de alunos \n"
				+ "2 - Abrir cadastro de professores \n"
				+ "3 - Abrir cadastro de disciplinas \n"
				+ "4 - Abrir cadastro de turmas \n"
				+ "0 - Sair";

		boolean parar = false;
		int opcao = -1;

		while (!parar) {
			try {
				String strOpcao = JOptionPane.showInputDialog(opcoes);

				if (strOpcao == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada.");
				}

				opcao = Integer.parseInt(strOpcao);
				parar = true;

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "A opção escolhida não é válida!");
			}
		}
		return opcao;
	}
}