package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry();
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while(opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println("Saldo conta: " + banco.saldo(conta));
                    break;
                }
                case 2: {
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println("Quantidade de contas: " + banco.quantidadeContas());
                    break;
                }
                case 3 : {
                    entrada.nextLine();
                    System.out.println("Informe o numero da conta: ");
                    String conta = entrada.nextLine();
                    System.out.println("Informe o saldo da conta: ");
                    Double saldo = entrada.nextDouble();
                    banco.adicionarConta(conta, saldo);
                    System.out.println("Conta adicionada com sucesso");
                    break;
                }
                case 4 : {
                    entrada.nextLine();
                    System.out.println("Informe o numero da conta: ");
                    String conta = entrada.nextLine();
                    System.out.println(banco.pesquisarConta(conta));
                    break;
                }
                case 5 : {
                    entrada.nextLine();
                    System.out.println("Informe o numero da conta: ");
                    String conta = entrada.nextLine();
                    System.out.println(banco.removerConta(conta));
                    break;
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("\nDynnah Hanna\n");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Adicionar conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("9 - Sair");
    }

}