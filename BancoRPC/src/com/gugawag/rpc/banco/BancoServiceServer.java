package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        contas = new ArrayList<Conta>();
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public double saldo(String numeroConta) throws RemoteException {
        double saldo = 0;
        for(Conta c : contas) {
            if(c.getNumeroConta() .equals(numeroConta))
                return c.getSaldo();
        }
        return saldo;
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public void adicionarConta(String numeroConta, double saldo) throws RemoteException {
        Conta c = new Conta(numeroConta, saldo);
        contas.add(c);
    }

    @Override
    public Conta pesquisarConta(String numeroConta) throws RemoteException {
        Conta conta = null;
        for(Conta c : contas) {
            if(c.getNumeroConta() .equals(numeroConta))
                conta = c;
        }
        return conta;
    }

    @Override
    public String removerConta(String numeroConta) throws RemoteException {
        Conta conta = pesquisarConta(numeroConta);
        contas.remove(conta);
        return "Conta " + conta + "removida";
    }

}
