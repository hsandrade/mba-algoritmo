package br.ufrj.mba.es30.stringmatch.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Classe para gerar a massa de teste e carregar os arquivos de texto.
 */
public class TextoUtil {

	public static void main(String[] args) throws Exception {
		TextoUtil gerar = new TextoUtil();
	
		int qtdArquivos = 17; //1024bytes ate 64MB

		//considerando que 1 arquivo é padrao e já deve existir para inicializar os demais
		for (int i = 2; i <= qtdArquivos; i++) {
			String textoAnterior = gerar.carregarTexto(i-1);
			//"dobrar" o tamanho do arquivo anterior.
			gerar.gravarTexto(i, textoAnterior.concat(textoAnterior));
		}
	}
	
	/**
	 * Texto padrao de 1024 bytes, com conteudo lorem ipsum dollor.
	 * O arquivo tem apenas uma linha independente da quantidade de bytes. 
	 * @return
	 */
	public String carregarTexto(int indice) throws Exception {
		//Scanner scan = new Scanner(new File(getNomeArquivo(indice)));
		ClassLoader cl = getClass().getClassLoader();
		Scanner scan = new Scanner(new File(cl.getResource(getNomeArquivo(indice)).getFile()));
		try {
			return scan.nextLine();
		}
		finally {
			scan.close();
		}
	}
	
	/**
	 * Gravar os arquivos de texto. <br>
	 * Uso único e indepente da analise teorica
	 * @param indice
	 * @param conteudo
	 * @throws Exception
	 */
	public void gravarTexto(int indice, String conteudo) throws Exception {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(getNomeArquivo(indice)))) {
			bw.write(conteudo);
		} 
	}
	
	private String getNomeArquivo(int indice) {
		return "resources/tamanho-" + indice + ".txt";
		//return "C:/Projetos/temp/massa/tamanho-" + indice + ".txt";
	}

}
