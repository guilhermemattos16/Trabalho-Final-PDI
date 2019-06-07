package implementacoes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import swing.janelas.PDI_Lote;

/**
 * Classe para os métodos(funcoes) para o calculo do Greenness, onde serão mantidos
 * , que serao usadas pelo resto do programa.
 * 
 * @author Flavia Mattos & Arthur Costa
 */
public class Greenness {

	/**
	 * Essa função é a implementação da método de GreennesskG = kG − (R + B)
	 * onde K é o valor passado pelo usuário e o R,G e B são os valores obtido da imagem
	 * 
	 * @param img A imagem onde o filtro será aplicado
	 * @param K O valor K da equação
	 * @return retorna a imagem depois de aplicado o filtro
	 */
	public BufferedImage GreennKG(BufferedImage img) {
		BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

		// COME�O NORMALIZA��O
		float histograma[] = new float[256];
		float histograma1[] = new float[256];
		float histograma2[] = new float[256];
		float novoNivel = 0;
		int soma1 = 0;
		int soma2 = 0;
		float cont = 0;
		float sp = 0;
		float spl = 0;
		float spu = 0;
		float grl1 = 0;
		float grl2 = 0;
		float gru1 = 0;
		float gru2 = 0;
		float dl = 0;
		float du = 0;
		float pll1 = 0;
		float pll2 = 0;
		float plu1 = 0;
		float plu2 = 0;
		float pkl = 0;
		float pku = 0;
		float p1 = 0;
		float p2 = 0;
		
		// Inicializa os vetores dos histogramas
		for (int i=0; i<256; i++) {
			histograma[i] = 0;
			histograma1[i] = 0;
			histograma2[i] = 0;
		}
		
		// Gera o Histograma
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				Color x = new Color(img.getRGB(i, j));
				int cor = x.getGreen();

				histograma[cor]++;
			}
		}
		
		// Calcula a quantidade de pixels
		cont = img.getWidth()*img.getHeight();

		// Exibe o histograma da imagem original
		for (int i = 0; i<256; i++) {
			System.out.println(histograma[i]);
		}
		
		// Calcula a m�dia SP do histograma da imagem original
		for (int i = 0; i < 256; i++) {
			sp += (histograma[i]/cont) * i;
		}
//		
//		// Exibe a quantidade total de pixels da imagem
//		System.out.println("final: " +cont);
//		
//		// Exibe a m�dia SP
//		System.out.println("SP: " +sp);
//		
		for (int i=0; i<256; i++) {
			if(i<=sp) {
				histograma1[i] = histograma[i];
			}
			else {
				histograma2[i] = histograma[i];
			}
			
			soma1 += histograma1[i];
			soma2 += histograma2[i];
			
		}
		
		for (int i = 0; i < 256; i++) {
			spl += (histograma1[i]/soma1) * i;
		}
		
		for (int i = 0; i < 256; i++) {
			spu += (histograma2[i]/soma2) * i;
		}
		
		grl1 = (sp - spl)/(sp);
		
		if (grl1 > 0.5) {
			dl = (1 - grl1)/2;
		}
		else if (grl1 <= 0.5) {
			dl = grl1/2;
		}
		
		grl2 = grl1 + dl;
		gru1 = (255 - spu)/(255 - sp);
		
		if (gru1 > 0.5) {
			du = (1 - gru1)/2;
		}
		else if (gru1 <= 0.5) {
			du = gru1/2;
		}
		
		gru2 = gru1 + du;
		
		for(int i=0; i<256; i++) {
			if (pkl < histograma1[i]) {
				pkl = histograma[i];
			}
		}
		
		for(int i=0; i<256; i++) {
			if (pku < histograma2[i]) {
				pku = histograma[i];
			}
		}
		
		pll1 = grl1 * pkl;
		pll2 = grl2 * pkl;
		plu1 = gru1 * pku;
		plu2 = gru2 * pku;
		
		for (int i=0; i<256; i++) {
			if (histograma1[i] <= pll2) {
				histograma1[i] = (int) pll1;
			}
			else if (histograma1[i] > pll2) {
				histograma1[i] = (int) pll2;
			}
		}
		
		for (int i=0; i<256; i++) {
			if (histograma2[i] <= plu2) {
				histograma2[i] = (int) plu1;
			}
			else if (histograma2[i] > plu2) {
				histograma2[i] = (int) plu1;
			}
		}
		
		for(int i=0; i<256; i++) {
			p1 += (histograma1[i]/soma1);
			novoNivel = sp * p1;
			histograma1[i] = novoNivel;
		}
		
		novoNivel = 0;
		
		for(int i=0; i<256; i++) {
			p2 += (histograma2[i]/soma2);
			novoNivel = sp * p2;
			histograma2[i] = novoNivel;
		}
		
		for (int i=0; i<256; i++) {
			if(i<=sp) {
				histograma[i] = histograma1[i];
			}
			else {
				histograma[i] = histograma2[i];
			}
		}
		

		System.out.println("\n\nHistograma Final: ");
		for(int i1=0; i1<256; i1++) {
			System.out.println(histograma1[i1]);
		}
		
		//FINAL NORMALIZAÇÃO

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {

				Color p = new Color(img.getRGB(i, j));
				//double atual = p.getGreen() - p.getRed() - p.getBlue();
				//double cor = 255 * ((atual - min) / (max - min));
				
				double cor = histograma[p.getGreen()];
				int cor1 = (int) cor;

				Color novo = new Color(cor1, cor1, cor1);
				res.setRGB(i, j, novo.getRGB());
			}
		}
		
		return res;
	}
}


