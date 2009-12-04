package br.edu.ufcg.msn.analise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class tese {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("teste"));
		String str;
		while((str = br.readLine())!= null){
			String[] tokens = str.split("\\s+");
			System.out.println("a.analisaMetodo(new "+tokens[0] + "(), numPontos, numConjuntos);");
		}
	}

}
