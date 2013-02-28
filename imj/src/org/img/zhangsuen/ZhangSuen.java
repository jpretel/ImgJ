package org.img.zhangsuen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZhangSuen {
	private boolean[][] matriz;
	public int contador;

	public ZhangSuen(boolean[][] matriz) {
		this.setMatriz(matriz);
	}

	public boolean[][] iniciar() {
		final int x = matriz.length;
		final int y = matriz[0].length;
		boolean[][] esqueleto = new boolean[x][y];
		int[] _band = new int[1];

		int[] _cont = new int[1];
		/*
		 * SUB ITERACCION 1
		 */

		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++) {
				if (i < 1 || i == x - 1 || j < 1 || j == y - 1 || !matriz[i][j]) {
					matriz[i][j] = false;
				} else {
					_band = new int[1];
					_cont = new int[1];
					//new SubItN1(matriz, i, j, _band, _cont).start();
					//new SubItN2(matriz, i, j, _band, _cont).start();
					//new SubIt13(matriz, i, j, _band, _cont).start();
					new SubIt14(matriz, i, j, _band, _cont).start();
					//new Iteraccion(esqueleto, i, j, _band, _cont).start();
					//e//squeleto[i][j] = !(band == 4);
					// System.out.println(": " + i +" " + j);
				}
				// System.out.println("  " + i);
			}

		/*
		 * SUB ITERACCION 2
		 */

		/*
		 * matriz = esqueleto;
		 * 
		 * esqueleto = new boolean[x][y];
		 * 
		 * for (int i = 0; i < x; i++) for (int j = 0; j < y; j++) { if (i < 1
		 * || i == x - 1 || j < 1 || j == y - 1 || !matriz[i][j]) { matriz[i][j]
		 * = false; } else {
		 * 
		 * esqueleto[i][j] = !(band == 4);
		 * 
		 * } } return esqueleto;
		 * 
		 * /* ExecutorService es = Executors.newFixedThreadPool(2);
		 * 
		 * SubIteraccionN_1 s11 = new SubIteraccionN_1(matriz, esqueleto,
		 * _band); SubIteraccionN_2 s12 = new SubIteraccionN_2(matriz,
		 * esqueleto, _band); SubIteraccion1_3 s13 = new
		 * SubIteraccion1_3(matriz, esqueleto, _band); SubIteraccion1_4 s14 =
		 * new SubIteraccion1_4(matriz, esqueleto, _band);
		 * 
		 * IteraccionN it1 = new IteraccionN(esqueleto, matriz, _band);
		 * 
		 * es.execute(s11); es.execute(s12); es.execute(s13); es.execute(s14);
		 * /* s11.start(); s12.start(); s13.start(); s14.start();
		 */

		/*
		 * matriz = it1.unir(); esqueleto = new int[x][y]; _band = new int[1];
		 * 
		 * SubIteraccionN_1 s21 = new SubIteraccionN_1(matriz, esqueleto,
		 * _band); SubIteraccionN_2 s22 = new SubIteraccionN_2(matriz,
		 * esqueleto, _band); SubIteraccion2_3 s23 = new
		 * SubIteraccion2_3(matriz, esqueleto, _band); SubIteraccion2_4 s24 =
		 * new SubIteraccion2_4(matriz, esqueleto, _band);
		 * 
		 * it1 = new IteraccionN(esqueleto,matriz, _band);
		 * 
		 * s21.start(); s22.start(); s23.start(); s24.start();
		 */

		// return it1.unir();
		return null;
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
}

class SubItN1 extends Thread {
	private boolean[][] matriz;
	private int i;
	private int j;
	private int[] _band;
	private int[] _cont;

	public SubItN1(boolean[][] matriz, int i, int j, int[] _band, int[] _cont) {
		this.matriz = matriz;
		this.i = i;
		this.j = j;
		this._band = _band;
		this._cont = _cont;
	}

	@Override
	public void run() {
		synchronized (_cont) {
			int cont = 0;

			for (int k = -1; k <= 1; k++)
				for (int l = -1; l <= 1; l++) {
					if (matriz[i + k][j + l])
						cont++;
				}
			cont--;

			if (cont >= 2 && cont <= 6)
				_cont[0]++;
			
			_band[0]++;
			_cont.notify();
		}
		

	}

}

class SubItN2 extends Thread {
	private boolean[][] matriz;
	private int i;
	private int j;
	private int[] _band;
	private int[] _cont;

	public SubItN2(boolean[][] matriz, int i, int j, int[] _band, int[] _cont) {
		this.matriz = matriz;
		this.i = i;
		this.j = j;
		this._band = _band;
		this._cont = _cont;
	}

	@Override
	public void run() {
		synchronized (_cont) {
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
				_cont[0]++;
			_band[0]++;
			_cont.notify();
		}
		
	}
}

class SubIt13 extends Thread {
	private boolean[][] matriz;
	private int i;
	private int j;
	private int[] _band;
	private int[] _cont;

	public SubIt13(boolean[][] matriz, int i, int j, int[] _band, int[] _cont) {
		this.matriz = matriz;
		this.i = i;
		this.j = j;
		this._band = _band;
		this._cont = _cont;
	}

	@Override
	public void run() {
		synchronized (_cont) {
			if (!matriz[i - 1][j] || !matriz[i][j + 1] || !matriz[i + 1][j])
				_cont[0]++;
			_band[0]++;
			_cont.notify();
		}
	}
}

class SubIt14 extends Thread {
	private boolean[][] matriz;
	private int i;
	private int j;
	private int[] _band;
	private int[] _cont;

	public SubIt14(boolean[][] matriz, int i, int j, int[] _band, int[] _cont) {
		this.matriz = matriz;
		this.i = i;
		this.j = j;
		this._band = _band;
		this._cont = _cont;
	}

	@Override
	public void run() {
		synchronized (_cont) {
			if (!matriz[i][j + 1] || !matriz[i + 1][j] || !matriz[i][j - 1])
				_cont[0]++;
			_band[0]++;
			_cont.notify();
		}		
	}
}

class Iteraccion extends Thread {
	private boolean[][] esqueleto;
	private int i;
	private int j;
	private int[] _band;
	private int[] _cont;

	public Iteraccion(boolean[][] esqueleto, int i, int j, int[] _band, int[] _cont) {
		this.esqueleto = esqueleto;
		this.i = i;
		this.j = j;
		this._band = _band;
		this._cont = _cont;
	}

	@Override
	public void run() {
		//System.out.println("Espera " + i );
		synchronized (_cont) {
			while(_band[0] < 4){
				try {
					_cont.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			esqueleto[i][j] = !(_cont[0] == 4);
		}
	}
}