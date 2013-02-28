package org.img.zhangsuen;

public class SubIteraccionN_2 extends Thread {

	private boolean[][] matriz;
	private int[][] esqueleto;
	private int[] _band;

	public SubIteraccionN_2(boolean[][] matriz, int[][] esqueleto, int[] _band) {
		this.setMatriz(matriz);
		this.setEsqueleto(esqueleto);
		this._band = _band;
	}

	public void run() {
		synchronized (esqueleto) {
			final int x = matriz.length;
			final int y = matriz[0].length;

			for (int i = 0; i < x; i++)
				for (int j = 0; j < y; j++) {
					if (!(i < 1 || i == x - 1 || j < 1 || j == y - 1 || !matriz[i][j])) {
						int cont = 0;
						if (!matriz[i - 1][j] && matriz[i - 1][j + 1])
							cont++;

						if (!matriz[i - 1][j + 1] && matriz[i][j + 1])
							cont++;

						if (!matriz[i][j + 1] && matriz[i + 1][j + 1])
							cont++;

						if (!matriz[i + 1][j + 1] && matriz[i + 1][j])
							cont++;

						if (!matriz[i + 1][j] && matriz[i + 1][j - 1])
							cont++;

						if (!matriz[i + 1][j - 1] && matriz[i][j - 1])
							cont++;

						if (!matriz[i][j - 1] && matriz[i - 1][j - 1])
							cont++;

						if (!matriz[i - 1][j - 1] && matriz[i - 1][j])
							cont++;
						if (cont == 1)
							esqueleto[i][j] = esqueleto[i][j] + 1;
					}
				}
			_band[0] ++;
			esqueleto.notify();
		}
		
	}

	/**
	 * @return the matriz
	 */
	public boolean[][] getMatriz() {
		return matriz;
	}

	/**
	 * @param matriz
	 *            the matriz to set
	 */
	public void setMatriz(boolean[][] matriz) {
		this.matriz = matriz;
	}

	/**
	 * @return the esqueleto
	 */
	public int[][] getEsqueleto() {
		return esqueleto;
	}

	/**
	 * @param esqueleto
	 *            the esqueleto to set
	 */
	public void setEsqueleto(int[][] esqueleto) {
		this.esqueleto = esqueleto;
	}

}
