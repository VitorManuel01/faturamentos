package com.faturamentos;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class App 
{
    public static void main( String[] args )
    {
            try {
            // Lê o arquivo JSON e passa pra uma string
            FileReader reader = new FileReader("C:/Users/VITOR/Documents/Java VScode/faturamentos/src/main/resources/faturamentos.json");
            StringBuilder conteudo = new StringBuilder();
            int a;
            while ((a = reader.read()) != -1) {
                conteudo.append((char) a);
            }
            reader.close();

            // faz o Parse do JSON
            JSONArray faturamentoArray = new JSONArray(conteudo.toString());

            // Processa os dados pra pegar quais são os maiores e menores faturamentos e tbm um contador para os dias pra fazer a média dps
            List<Double> faturamentos = new ArrayList<>();
            double menorFaturamento = Double.MAX_VALUE;
            double maiorFaturamento = Double.MIN_VALUE;
            double somaFaturamentos = 0;
            int diasComFaturamento = 0;

            for (int i = 0; i < faturamentoArray.length(); i++) {
                JSONObject item = faturamentoArray.getJSONObject(i);
                double faturamento = item.getDouble("faturamento");
                
                if (faturamento > 0) {
                    faturamentos.add(faturamento);
                    somaFaturamentos += faturamento;
                    if (faturamento < menorFaturamento) {
                        menorFaturamento = faturamento;
                    }
                    if (faturamento > maiorFaturamento) {
                        maiorFaturamento = faturamento;
                    }
                    diasComFaturamento++;
                }
            }

            //calcula a média
            double mediaFaturamento = somaFaturamentos / diasComFaturamento;
            int diasAcimaDaMedia = 0;

            
            for (double faturamento : faturamentos) {
                if (faturamento > mediaFaturamento) {
                    diasAcimaDaMedia++;
                }
            }
         
            System.out.println("Menor faturamento: " + menorFaturamento);
            System.out.println("Maior faturamento: " + maiorFaturamento);
            System.out.println("Número de dias acima da média: " + diasAcimaDaMedia);

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao processar os dados: " + e.getMessage());
        }
    }
}

