package org.example;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static void menu(){
        LocalDate hoje = LocalDate.now();

        System.out.println("A data de hoje é " + hoje.format(formatter));
        System.out.println("Qual operação você deseja fazer?");
        System.out.println("[1]Somar/Subtrair DIAS");
        System.out.println("[2]Somar/Subtrair MESES");
        System.out.println("[3]Somar/Subtrair ANOS");
        System.out.println("[4]Medir data entre");
        System.out.println();
        System.out.println("[0]Sair");
        System.out.println();
        System.out.println("Digite o numero da opção desejada");
        int res = Integer.parseInt(sc.next());
        switch (res){
            case 1,2,3:
                calculaTempo(hoje, res);
                break;
            case 4:
                menuMedeTempo(hoje);
                break;
        }
    }
    private static void menuRetorno(){
        System.out.println("Deseja fazer uma nova simulação?(S/N)");
        String res = sc.next();
        if (Objects.equals(res, "S")){
            menu();
        }else{
            System.out.println("Até a próxima");
        }
    }
    private static void calculaTempo(LocalDate hoje, int res){
        String[] tempo = new String[]{"Dias", "Meses", "Anos"};
        System.out.println("Quantos "+tempo[res-1]+" você quer calcular? ");
        String dias = sc.next();
        System.out.println("Antes ou Depois da data atual? (A/D)");
        String antesDepois = sc.next();
        if (Objects.equals(antesDepois, "A")){
            switch (res){
                case 1:
                    hoje = hoje.minusDays(Integer.parseInt(dias));
                    break;
                case 2:
                    hoje = hoje.minusMonths(Integer.parseInt(dias));
                    break;
                case 3:
                    hoje = hoje.minusYears(Integer.parseInt(dias));
                    break;
            }
        }
        if (Objects.equals(antesDepois, "D")) {
            switch (res){
                case 1:
                    hoje = hoje.plusDays(Integer.parseInt(dias));
                    break;
                case 2:
                    hoje = hoje.plusMonths(Integer.parseInt(dias));
                    break;
                case 3:
                    hoje = hoje.plusYears(Integer.parseInt(dias));
                    break;
            }
        }
        System.out.println("A data calculada é: " + hoje.format(formatter));
        menuRetorno();
    }
    private static void menuMedeTempo(LocalDate hoje){
        System.out.println("Entre quais datas você quer medir o tempo?");
        System.out.println("[1]Até o Carnaval");
        System.out.println("[2]Até o Dia dos Namorados");
        System.out.println("[3]Até o Natal");
        System.out.println("[4]Até o Ano Novo");
        System.out.println("[5]Inserir data");
        System.out.println();
        System.out.println("[0]Voltar");
        System.out.println();
        System.out.println("Digite o numero da opção desejada");

        int res = Integer.parseInt(sc.next());
        medetempo(hoje, res);
    }
    private static void medetempo(LocalDate hoje, int res){
        LocalDate dataCalc = null;
        switch (res){
            case 1:
                dataCalc = LocalDate.of(2025, Month.FEBRUARY, 28);
                break;
            case 2:
                dataCalc = LocalDate.of(2025, Month.JUNE, 12);
                break;
            case 3:
                dataCalc = LocalDate.of(2024, Month.DECEMBER, 25);
                break;
            case 4:
                dataCalc = LocalDate.of(2025, Month.JANUARY, 1);
                break;
            case 5:
                System.out.println("Insira somente o dia desejado:");
                int d = Integer.parseInt(sc.next());
                System.out.println("Insira somente o mês desejado:");
                int m = Integer.parseInt(sc.next());
                System.out.println("Insira somente o ano desejado:");
                int a = Integer.parseInt(sc.next());
                dataCalc = LocalDate.of(a, m, d);
                break;
            case 0:
                menu();
                break;
        }
        System.out.println("Entre " +hoje.format(formatter)+ " e " + dataCalc.format(formatter));
        System.out.println("Há " + hoje.until(dataCalc, ChronoUnit.DAYS) + " dias");

        menuRetorno();
    }
    public static void main(String[] args) {
        menu();
    }
}