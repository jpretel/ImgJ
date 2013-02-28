package org.img.zhangsuen;

public class IteraccionN {

	private int[][] esqueleto;
	private int[] _band;
	private boolean[][] matriz;
	public IteraccionN(int[][] esqueleto, boolean matriz[][], int _band[]) {
		this.esqueleto = esqueleto;
		this._band = _band;
		this.matriz = matriz;
	}

	public boolean[][] unir() {
		synchronized (esqueleto) {
			while (_band[0] < 4) {
				try {
					esqueleto.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		final int x = esqueleto.length;
		final int y = esqueleto[0].length;

		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++) {
				if (i < 1 || i == x - 1 || j < 1 || j == y - 1 || !matriz[i][j]) {
					matriz[i][j] = false;
					//System.out.println(matriz[i][j]);
				} else {
					
					matriz[i][j] = !(esqueleto[i][j] == 4);
					//System.out.println(matriz[i][j]);
				}
			}
		return matriz;

	}

}
