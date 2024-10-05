package org.example;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    //Cria um Scanner fora do metodo principal para que possa ser usado em todos os metodos
    private static final Scanner sc = new Scanner(System.in);
    //Cria um formatador para que o formato da data fique como dia/mês/ano
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static void menu(){
        //Guarda a data de hoje na variavel "hoje"
        LocalDate hoje = LocalDate.now();

        //Impressão do menu principal para interação com o usuário
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

        //Guarda o número digitado pelo usuário
        int res = Integer.parseInt(sc.next());

        //Para caso o usuário selecione as opções 1, 2 ou 3, chama o metodo "calculaTempo"
        switch (res){
            case 1,2,3:
                calculaTempo(hoje, res);
                break;
            //Caso seja a opção 4 chama o metodo "menuMedeTempo"
            case 4:
                menuMedeTempo(hoje);
                break;
            //Caso seja a opção 0, se despede do usuário
            case 0:
                System.out.println("Até logo!");
        }
    }

    private static void menuRetorno(){
        //Menu chamado ao final de cada metodo, para saber se o usuário deseja ou não
        //fazer uma nova operação, caso deseje, chama o metodo "menu" e caso não, se despede do usuário
        System.out.println("Deseja fazer uma nova simulação?(S/N)");
        String res = sc.next();
        if (Objects.equals(res, "S")){
            menu();
        }else{
            System.out.println("Até a próxima");
        }
    }

    private static void calculaTempo(LocalDate hoje, int res){
        //Cria uma lista com as opções de tempo dadas anteriormente
        String[] tempo = new String[]{"Dias", "Meses", "Anos"};

        //Interação com o usuário
        System.out.println("Quantos "+tempo[res-1]+" você quer calcular? ");
        String dias = sc.next();
        System.out.println("Antes ou Depois da data atual? (A/D)");
        String antesDepois = sc.next();

        //Caso o usuário deseje calcular a data antes do dia atual...
        //Chama o calculo responsável pela opção de tempo selecionada no menu anterior
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
        //Caso o usuário deseje calcular a data depois do dia atual...
        //Chama o calculo responsável pela opção de tempo selecionada no menu anterior
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
        //Imprime para o usuário a data calculada
        System.out.println("A data calculada é: " + hoje.format(formatter));
        //Chama o metodo "menuRetorno"
        menuRetorno();
    }
    private static void menuMedeTempo(LocalDate hoje){
        //Menu de interação com o usuário
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
        //Chama o metodo "medeTempo a partir da opção selecionada
        medetempo(hoje, res);
    }
    private static void medetempo(LocalDate hoje, int res){
        //Cria uma variável para armazenar a nova data calculada
        LocalDate dataCalc = null;
        //Guarda a data responsável pela data selecionada pelo o usuário
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
            //Caso o usuário escolha a opção de "inserir data", interage novamante com o usuário
            //Para descobrir a data a ser calculada
            case 5:
                System.out.println("Insira somente o dia desejado:");
                int d = Integer.parseInt(sc.next());
                System.out.println("Insira somente o mês desejado:");
                int m = Integer.parseInt(sc.next());
                System.out.println("Insira somente o ano desejado:");
                int a = Integer.parseInt(sc.next());
                dataCalc = LocalDate.of(a, m, d);
                break;
            //Caso o usuário escolha a opção "Voltar", chama o metodo "menu"
            case 0:
                menu();
                break;
        }
        //Imprime o usuário as datas e quantos dias há de diferença entre elas
        System.out.println("Entre " +hoje.format(formatter)+ " e " + dataCalc.format(formatter));
        System.out.println("Há " + hoje.until(dataCalc, ChronoUnit.DAYS) + " dias");

        //chama o metodo "menuRetorno"
        menuRetorno();
    }

    public static void main(String[] args) {
        //Chama o metodo menu para interação com o usuário
        menu();
    }
}