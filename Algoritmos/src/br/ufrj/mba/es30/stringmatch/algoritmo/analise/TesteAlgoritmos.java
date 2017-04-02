package br.ufrj.mba.es30.stringmatch.algoritmo.analise;

import br.ufrj.mba.es30.stringmatch.algoritmo.BoyerMoore;
import br.ufrj.mba.es30.stringmatch.algoritmo.InterfaceTeste;
import br.ufrj.mba.es30.stringmatch.algoritmo.KarpRabin;
import br.ufrj.mba.es30.stringmatch.algoritmo.KnuthMorrisPratt;
import br.ufrj.mba.es30.stringmatch.util.TextoUtil;

/**
 * Classe principal para testar os algorimos de busca de texto.
 */
public class TesteAlgoritmos {

	public static void main(String[] args) throws Exception {
		TesteAlgoritmos teste = new TesteAlgoritmos();
		
		String entrada = "elit";
		
		teste.testarAlgoritmo(new BoyerMoore(), entrada);
		teste.testarAlgoritmo(new KarpRabin(), entrada);
		teste.testarAlgoritmo(new KnuthMorrisPratt(), entrada);
	}
	
	public void testarAlgoritmo(InterfaceTeste algoritmo, String entrada) throws Exception {
		TextoUtil textoUtil = new TextoUtil();
		
		//define o limite maximo da quantidade de arquivos de texto que serao utilizados
		//como base para a pesquisa
		int maxArq = 17;
	    final double TEMPO_MEDICAO = 1.0;
	    int numExecucoes;		
		
		System.out.println(algoritmo.getClass().getSimpleName());
		System.out.println("Texto fonte (caracteres), Qtde execucoes, Tempo (s)");
		
		for (int i = 1; i <= maxArq; i++) {
			String fonte = textoUtil.carregarTexto(i);
			
			long tamanhoFonte = fonte.length();
			
			double t1,t2,tempoExecucao,limite;
			
			numExecucoes = 0;
			
			t1 = System.currentTimeMillis();
			
			limite = t1 + 1000 * TEMPO_MEDICAO;
			
			//executa o algoritmo e retorna o total de ocorrencias (saida) 
			//de acordo com a entrada.
			while (System.currentTimeMillis() < limite) {
				//executa a logica de pesquisa do texto, 
				//a entrada eh fixa e a saida nao é necessária para o gráfico.
				algoritmo.findAll(entrada, fonte);	
				
				numExecucoes++;
			}
			
			t2 = System.currentTimeMillis();
			
			//calcula o tempo em segundos
			tempoExecucao = (t2-t1)/1000/numExecucoes;
			
			System.out.printf("%d;%d;%15.9f\n", tamanhoFonte, numExecucoes, tempoExecucao);
			
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}

}
