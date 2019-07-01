package implementacoes;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Classe utilizada para realização da conversão do espaço
 * de cores de RGB para YIQ e vice-versa
 * 
 * @author Guilherme Mattos
 * @version 1.0 (junho-2019)
 */
public class Converte {

	/**	Campos de variaveis YIQ */
	private double y;
	private double i;
	private double q;

	/** Matriz para converter RGB para YIQ */ 
	public static double[][] rgbToYiq = {{0.299, 0.587, 0.114}, 
										{0.596, -0.274, -0.322}, 
										{0.211, -0.523, 0.312}};

	/** Matriz para converter YIQ para RGB */
	public static double[][] yiqToRgb = {{1.000, 0.956, 0.621}, 
										{1.000, -0.272, -0.647}, 
										{1.000, -1.106, 1.703}};

	/**
	 * Método para retornar o Y.
	 * @return y do espaço YIQ.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Metodo para atribuir o y para o espaço YIQ.
	 * @param _y do espaço YIQ.
	 */
	public void setY(double _y) {
		this.y = _y;
	}

	/**
	 * Método para retornar o I.
	 * @return i do espaço YIQ.
	 */
	public double getI() {
		return this.i;
	}

	/**
	 * Metodo para atribuir o i para o espaço YIQ.
	 * @param _i do espaço YIQ.
	 */
	public void setI(double _i) {
		this.i = _i;
	}

	/**
	 * Método para retornar o q.
	 * @return q do espaço YIQ.
	 */
	public double getQ() {
		return this.q;
	}

	/**
	 * Metodo para atribuir o q para o espaço YIQ.
	 * @param _q do espaço YIQ.
	 */
	public void setQ(double _q) {
		this.q = _q;
	}

	public Converte rgbParaYiq(Color rgb){

		Converte conversao = new Converte();

		conversao.setY((rgb.getRed() * rgbToYiq[0][0]) + (rgb.getGreen() * rgbToYiq[0][1]) + (rgb.getBlue() * rgbToYiq[0][2]));
		//System.out.println(conversao.getY());
		conversao.setI(rgb.getRed() * rgbToYiq[1][0] + rgb.getGreen() * rgbToYiq[1][1] + rgb.getBlue() * rgbToYiq[1][2]);
		//System.out.println(conversao.getI());
		conversao.setQ(rgb.getRed() * rgbToYiq[2][0] + rgb.getGreen() * rgbToYiq[2][1] + rgb.getBlue() * rgbToYiq[2][2]);
		//System.out.println(conversao.getI());

		return conversao; 
	}

	public Color yiqParaRgb(Converte yiq){
		
		int resultadoR;
		int resultadoG;
		int resultadoB;
		
		resultadoR =(int) (yiq.getY() + (yiq.getI() * yiqToRgb[0][1]) + (yiq.getQ() * yiqToRgb[0][2]));
		if (resultadoR < 0) 
		{
			resultadoR = 0;
		} 
		else if (resultadoR > 255 ) 
		{
			resultadoR = 255;
		}
		
		resultadoG =(int) (yiq.getY() + (yiq.getI() * yiqToRgb[1][1]) + (yiq.getQ() * yiqToRgb[1][2]));
		if (resultadoG < 0) 
		{
			resultadoG = 0;
		} 
		else if (resultadoG > 255 ) 
		{
			resultadoG = 255;
		}
		
		resultadoB =(int) (yiq.getY() + (yiq.getI() * yiqToRgb[2][1]) + (yiq.getQ() * yiqToRgb[2][2]));
		if (resultadoB < 0) 
		{
			resultadoB = 0;
		} 
		else if (resultadoB > 255 ) 
		{
			resultadoB = 255;
		}
		
		// System.out.println("R: " + resultadoR + " G: " + resultadoG + " B: " +resultadoB + " bbbbbbbbbb\n");

		Color novoRes = new Color(resultadoR, resultadoG, resultadoB);
		
		// System.out.println(novoRes.getRGB() + " aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n\n");
		
		return novoRes; 
	}	
}
