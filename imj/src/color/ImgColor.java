package color;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

public class ImgColor {
	private int[][][] valores;
	private Image imagen;
	private int alto;
	private int ancho;
	private BufferedImage imagenBuff;
	private int[] pixeles;

	public ImgColor() {
		setImagen(Toolkit.getDefaultToolkit().getImage("imagen.jpg"));
		initMediaTracker();
		obtenerPixeles();
	}

	/**
	 * @param url
	 */
	public ImgColor(String url) {
		setImagen(Toolkit.getDefaultToolkit().getImage(url));
		initMediaTracker();
		obtenerPixeles();
	}

	private void initMediaTracker() {
		MediaTracker tracker = new MediaTracker(new Canvas());
		tracker.addImage(getImagen(), 1);
		try {
			if (!tracker.waitForID(1, 10000)) {
				System.out.println("Error en la carga de la imagen");
				System.exit(1);
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		setAlto(getImagen().getHeight(null));
		setAncho(getImagen().getWidth(null));
		Coversion C = new Coversion();
		setImagenBuff(C.toBufferedImage(getImagen()));
	}

	private void obtenerPixeles() {
		int iniAncho = getAncho();
		int iniAlto = getAlto();
		int[] pix = new int[iniAncho * iniAlto];
		try {
			new PixelGrabber(getImagen(), 0, 0, iniAncho, iniAlto, pix, 0,
					iniAncho).grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setPixeles(pix);
	}

	/**
	 * @return the valores
	 */
	public int[][][] getValores() {
		return valores;
	}

	/**
	 * @param valores
	 *            the valores to set
	 */
	public void setValores(int[][][] valores) {
		this.valores = valores;
	}

	/**
	 * @return the imagen
	 */
	public Image getImagen() {
		return imagen;
	}

	/**
	 * @param imagen
	 *            the imagen to set
	 */
	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * @param alto
	 *            the alto to set
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * @return the ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * @param ancho
	 *            the ancho to set
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the imagenBuff
	 */
	public BufferedImage getImagenBuff() {
		return imagenBuff;
	}

	/**
	 * @param imagenBuff
	 *            the imagenBuff to set
	 */
	public void setImagenBuff(BufferedImage imagenBuff) {
		this.imagenBuff = imagenBuff;
	}

	/**
	 * @return the pixeles
	 */
	public int[] getPixeles() {
		return pixeles;
	}

	/**
	 * @param pixeles
	 *            the pixeles to set
	 */
	public void setPixeles(int[] pixeles) {
		this.pixeles = pixeles;
	}

	class Coversion {

		BufferedImage toBufferedImage(Image image) {
			if (image instanceof BufferedImage) {
				return ((BufferedImage) image);
			} else {
				image = new ImageIcon(image).getImage();
				BufferedImage bufferedImage = new BufferedImage(
						image.getWidth(null), image.getHeight(null),
						BufferedImage.TYPE_4BYTE_ABGR);
				return (bufferedImage);
			}
		}
	}
}
