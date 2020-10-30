package br.com.zup.estrelas.analise.ordenacao;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZupEstrelasAnaliseOrdenacaoApplication {

	public static int geraNumerosVetor() {
		Random random = new Random();
		return random.nextInt(40000000);
	}

	public static int buscarPosicaoVetor(int[] vetor, int numeroBusca) {
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] == numeroBusca) {
				return i;
			}
		}
		return -1;
	}

	public static int[] popularVetor(int tamanhoVetor, int[] numerosDesordenados) {
		for (int i = 0; i < numerosDesordenados.length; i++) {
			numerosDesordenados[i] = geraNumerosVetor();
		}

		return numerosDesordenados;
	}

	public static int[] ordenacaoFeitaEmSala(int[] numerosDesordenados, int[] numerosOrdenados) {
		for (int i = 0; i < numerosDesordenados.length; i++) {
			int auxiliarPosicaoArray = 0;

			for (int j = 0; j < numerosDesordenados.length; j++) {
				if (numerosDesordenados[j] < numerosDesordenados[i]) {
					auxiliarPosicaoArray += 1;
				}
			}

			numerosOrdenados[auxiliarPosicaoArray] = numerosDesordenados[i];
		}

		return numerosOrdenados;
	}

	public static int[] bubbleSort(int[] vetorASerOrdenado) {
		boolean troca = true;

		int aux;
		while (troca) {
			troca = false;
			for (int i = 0; i < vetorASerOrdenado.length - 1; i++) {
				if (vetorASerOrdenado[i] > vetorASerOrdenado[i + 1]) {
					aux = vetorASerOrdenado[i];
					vetorASerOrdenado[i] = vetorASerOrdenado[i + 1];
					vetorASerOrdenado[i + 1] = aux;
					troca = true;
				}
			}
		}

		return vetorASerOrdenado;
	}

	public static int[] insertionSort(int[] vetor) {
		int elementoReferencia, i, j;

		for (j = 1; j < vetor.length; j++) {
			elementoReferencia = vetor[j];
			for (i = j - 1; (i >= 0) && (vetor[i] > elementoReferencia); i--) {
				vetor[i + 1] = vetor[i];
			}

			vetor[i + 1] = elementoReferencia;
		}

		return vetor;
	}

	public static void quickSort(int[] vetor, int inicio, int fim) {
		if (inicio < fim) {
			int posicaoPivo = separar(vetor, inicio, fim);
			quickSort(vetor, inicio, posicaoPivo - 1);
			quickSort(vetor, posicaoPivo + 1, fim);
		}
	}

	private static int separar(int[] vetor, int inicio, int fim) {
		int pivo = vetor[inicio];
		int i = inicio + 1, f = fim;

		while (i <= f) {
			if (vetor[i] <= pivo) {
				i++;
			} else if (pivo < vetor[f]) {
				f--;
			} else {
				int troca = vetor[i];
				vetor[i] = vetor[f];
				vetor[f] = troca;
				i++;
				f--;
			}
		}

		vetor[inicio] = vetor[f];
		vetor[f] = pivo;
		return f;
	}

	public static void main(String[] args) {
		SpringApplication.run(ZupEstrelasAnaliseOrdenacaoApplication.class, args);
		int[] numerosDesordenados = new int[40000];
		int[] numerosOrdenados = new int[numerosDesordenados.length];

		int[] numerosBubbleSort = new int[40000];
		int[] numerosInsertionSort = new int[40000];
		int[] numerosQuickSort = new int[40000];

		long inicio, fim;

		numerosDesordenados = popularVetor(numerosDesordenados.length, numerosDesordenados);

		System.out.println("-- ORDENAÇÃO Feita em sala --");

		inicio = System.currentTimeMillis();
		numerosOrdenados = ordenacaoFeitaEmSala(numerosDesordenados, numerosOrdenados);
		fim = System.currentTimeMillis();
		System.out.printf("Tempo de execução  (milis): %d\n\n", fim - inicio);

		System.out.println("-- ORDENAÇÃO Bubble Sort --");
		numerosBubbleSort = popularVetor(numerosBubbleSort.length, numerosBubbleSort);

		inicio = System.currentTimeMillis();
		bubbleSort(numerosBubbleSort);
		fim = System.currentTimeMillis();
		System.out.printf("Tempo de execução  (milis): %d\n\n", fim - inicio);

		System.out.println("-- ORDENAÇÃO Insertion Sort --");
		numerosInsertionSort = popularVetor(numerosInsertionSort.length, numerosInsertionSort);

		inicio = System.currentTimeMillis();
		insertionSort(numerosInsertionSort);
		fim = System.currentTimeMillis();
		System.out.printf("Tempo de execução  (milis): %d\n\n", fim - inicio);

		System.out.println("-- ORDENAÇÃO Quick Sort --");
		numerosQuickSort = popularVetor(numerosQuickSort.length, numerosQuickSort);

		inicio = System.currentTimeMillis();
		quickSort(numerosQuickSort, 0, numerosQuickSort.length - 1);
		fim = System.currentTimeMillis();
		System.out.printf("Tempo de execução  (milis): %d\n\n", fim - inicio);

		// System.out.println("\nPosição do vetor: " +
		// buscarPosicaoVetor(numerosOrdenados, 71));
	}

}
