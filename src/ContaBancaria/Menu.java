package ContaBancaria;

import java.io.IOException;
import java.util.Scanner;

import Contacontroller.ContaController;
import contamodel.ContaCorrente;
import contamodel.ContaPoupanca;
import contautil.Cores;

public class Menu {

	public static void main(String[] args) {

		Scanner leia = new Scanner(System.in);

		ContaController contas = new ContaController();

		int option, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		// Teste da Classe "Conta Corrente"

		/*
		 * ContaCorrente cc1 = new ContaCorrente(1, 123, 1, "José da Silva", 0.0f,
		 * 1000.0f); cc1.visualizar(); cc1.sacar(12000.0f); cc1.depositar(5000.0f);
		 * cc1.visualizar();
		 * 
		 * //Teste da Classe "Conta Poupança"
		 * 
		 * ContaPoupanca cp1 = new ContaPoupanca(2, 123, 2, "Maria dos Santos",
		 * 100000.0f, 15); cp1.visualizar(); cp1.sacar(1000.0f);
		 * 
		 * cp1.depositar(5000.0f); cp1.visualizar();
		 */

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND);

			System.out.println("************************************************");
			System.out.println("                                                ");
			System.out.println("              | BANCO DO BRAZIL  |              ");
			System.out.println("                                                ");
			System.out.println("************************************************");
			System.out.println("                                                ");
			System.out.println("           1 - CRIAR CONTA                      ");
			System.out.println("           2 - LISTAR TODAS AS CONTAS           ");
			System.out.println("           3 - BUSCAR CONTA POR NUMERO          ");
			System.out.println("           4 - ATUALIZAR DADOS DA CONTA         ");
			System.out.println("           5 - APAGAR CONTA                     ");
			System.out.println("           6 - SACAR                            ");
			System.out.println("           7 - DEPOSITAR                        ");
			System.out.println("           8 - TRANSFERENCIA DE VALORES         ");
			System.out.println("           9 - ENCERRAR O PROGRAMA              ");
			System.out.println("                                                ");
			System.out.println("************************************************");
			System.out.println("Entre com a opção desejada:                     ");
			System.out.println("                                                " + Cores.TEXT_RESET);

			option = leia.nextInt();

			if (option == 9) {
				System.out.println("\nBANCO DO BRAZIL - Volte Sempre!");
				leia.close();
				System.exit(0);
			}

			switch (option) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nCriar Conta \n\n");

				System.out.println("Digite o nº da Agência: ");
				agencia = leia.nextInt();
				System.out.println("Digite o nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();

				do {
					System.out.println("Digite  Tipo da conta (1 - CC ou 2 - CP");
					tipo = leia.nextInt();
				} while (tipo < 1 && tipo > 2);

				System.out.println("Digite o saldo da Conta (R$): ");
				saldo = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o limite de crédito (R$): ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversário da Conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}
				keyPress();
				break;

			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nListar todas as contas \n\n");
				contas.listarTodas();
				keyPress();
				break;

			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nConsultar dados da Conta - por número \n\n");

				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();
				contas.procurarPorNumero(numero);

				keyPress();
				break;

			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nAtualizar dados da conta \n\n");

				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();

				if (contas.buscarNaCollection(numero) != null) {
					System.out.println("Digite o número da Agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o nome do Titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();

					System.out.println("Digite o saldo da conta (R$): ");
					saldo = leia.nextFloat();

					tipo = contas.retornaTipo(numero);

					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o limite de Crédito (R$): ");
						limite = leia.nextFloat();

						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));

					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversário da Conta: ");
						aniversario = leia.nextInt();
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("Desculpe... tipo de Conta Inválida.");
					}
					}

				} else
					System.out.println("\nConta não encontrada!");

				keyPress();
				break;

			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nApagar a Conta \n\n");

				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();
				contas.deletar(numero);

				keyPress();
				break;

			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nSaque \n\n");

				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();

				do {
					System.out.println("Digite o Valor do Saque (R$): ");
					valor = leia.nextInt();
				} while (valor <= 0);

				contas.sacar(numero, valor);

				keyPress();
				break;

			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nDepósito \n\n");

				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();

				do {
					System.out.println("\nDigite o valor do Depósito (R$): ");
					valor = leia.nextFloat();
				} while (valor <= 0);

				contas.depositar(numero, valor);

				keyPress();
				break;

			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nTransferência entre Contas \n\n");

				System.out.println("Digite o Número da conta de Origem: ");
				numero = leia.nextInt();

				System.out.println("Digite o Numero da Conta de Destino");
				numeroDestino = leia.nextInt();

				do {
					System.out.println("Digite o Valor da Transferência (R$): ");
					valor = leia.nextFloat();
				} while (valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);

				keyPress();
				break;

			default:

				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!");
			}

		}

	}

	public static void keyPress() {

		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();
		} catch (IOException e) {
			System.out.println("Você pressionou uma tecla diferente de Enter!");

		}
	}

}
