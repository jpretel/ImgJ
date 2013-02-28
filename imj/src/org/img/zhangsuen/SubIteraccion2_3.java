package org.img.zhangsuen;

public class SubIteraccion2_3 extends Thread {
	private boolean[][] matriz;
	private int[][] esqueleto;
	private int[] _band;
	public SubIteraccion2_3(boolean[][] matriz, int[][] esqueleto, int[] _band) {
		this.setEsqueleto(esqueleto);
		this.setMatriz(matriz);
		this._band = _band;
	}

	
	public void run() {
		synchronized (esqueleto) {
			
			final int x = matriz.length;
			final int y = matriz[0].length;
			for (int i = 0; i < x; i++)
				for (int j = 0; j < y; j++) {
					if (!(i < 1 || i == x - 1 || j < 1 || j == y - 1 || !matriz[i][j])) {
						if (!matriz[i][j - 1] || !matriz[i - 1][j]
								|| !matriz[i][j + 1])
							esqueleto[i][j] = esqueleto[i][j] + 1;
						
					}
				}
			_band[0]++;
			esqueleto.notify();
		}
	}

	public boolean[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(boolean[][] matriz) {
		this.matriz = matriz;
	}

	public int[][] getEsqueleto() {
		return esqueleto;
	}

	public void setEsqueleto(int[][] esqueleto) {
		this.esqueleto = esqueleto;
	}
}
