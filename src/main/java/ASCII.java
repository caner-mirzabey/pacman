/*Copyright (c) 2011 Aravind Rao

Modifications by Sam Barnum, 360Works 2012

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import org.fusesource.jansi.*;
import org.jnativehook.*;
import org.jnativehook.keyboard.*;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.*;

public final class ASCII {
	public static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

	static {
		logger.setLevel(Level.OFF);
	}

	boolean negative;

	public ASCII() {
		this(false);
	}

	public ASCII(final boolean negative) {
		this.negative = negative;
	}

	public static void main(String[] args) {
		AnsiConsole.systemInstall();

		javax.swing.SwingUtilities.invokeLater(() -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images",
			                                                                              "jpg",
			                                                                              "gif",
			                                                                              "png"));
			fileChooser.showOpenDialog(null);
			try {
				File                f     = fileChooser.getSelectedFile();
				final BufferedImage image = ImageIO.read(f);
				if (image == null) {
					throw new IllegalArgumentException(f + " is not a valid image.");
				}

				AnsiConsole.systemInstall();
				Ansi.ansi().eraseScreen();
				Navigator navigator = new Navigator(image, 0, 0, 100, 100);
				navigator.display(0, 0);
				GlobalScreen.registerNativeHook();
				GlobalScreen.addNativeKeyListener(navigator);
				while (true) {
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			finally {
				Ansi.ansi().eraseScreen();
				AnsiConsole.systemUninstall();
				try {
					GlobalScreen.unregisterNativeHook();
				}
				catch (NativeHookException e) {
					e.printStackTrace();
				}
			}
		});
	}

	static class Navigator implements NativeKeyListener {
		final BufferedImage image;
		int x = 0;
		int y = 0;
		int width;
		int height;

		public Navigator(BufferedImage image, int x, int y, int width, int height) {
			Ansi.ansi().eraseScreen();
			this.image = image;
			this.x = x;
			this.y = y;
			if (x + width > image.getWidth() || y + height > image.getHeight() || x < 0 || y < 0) {
				throw new IllegalArgumentException();
			}
			this.width = width;
			this.height = height;
		}

		@Override
		public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
			switch (nativeKeyEvent.getKeyCode()) {
				case NativeKeyEvent.VC_UP:
					if (y - 20 >= 0) {
						display(x, y -= 20);
					}
					break;
				case NativeKeyEvent.VC_DOWN:
					if (y + height + 20 <= image.getHeight()) {
						display(x, y += 20);
					}
					break;
				case NativeKeyEvent.VC_LEFT:
					if (x - 20 >= 0) {
						display(x -= 20, y);
					}
					break;
				case NativeKeyEvent.VC_RIGHT:
					if (x + 20 + width <= image.getWidth()) {
						display(x += 20, y);
					}
					break;
				case NativeKeyEvent.VC_ENTER: {
					display(x, y);
					break;
				}
				case NativeKeyEvent.VC_HOME:
					display(x = 0, y = 0);
					break;
				case NativeKeyEvent.VC_END:
					display(x = image.getWidth() - width, y = image.getHeight() - height);
					break;
				case NativeKeyEvent.VC_ESCAPE:
					System.exit(0);
				default:
					break;
			}
		}

		@Override
		public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

		}

		@Override
		public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

		}

		public void display(int x_pos, int y_pos) {
			BufferedImage image = this.image.getSubimage(x_pos, y_pos, width, height);
			Ansi          ansi  = new Ansi((image.getWidth() + 1) * image.getHeight());
			ansi.setEnabled(true);
			ansi.scrollUp(height);
			ansi.cursor(0, 0);
			for (int y = 0; y < image.getHeight(); y++) {
				ansi.newline();
				for (int x = 0; x < image.getWidth(); x++) {
					Color pixelColor = new Color(image.getRGB(x, y));
					double gValue = (double) pixelColor.getRed() * 0.2989 + (double) pixelColor.getBlue() * 0.5870 +
					                (double) pixelColor.getGreen() * 0.1140;
					final char s =
							//														219;
							returnStrPos(gValue);
					Ansi.Color color = getANSIColor(pixelColor);
					ansi.fg(color).a(s);
				}
			}
			ansi.reset();
			try {
				out(ansi);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void out(Ansi ansi) throws IOException {
			long                 startTime            = System.currentTimeMillis();
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(AnsiConsole.out);
			bufferedOutputStream.write(ansi.toString().getBytes());
			OutputStream outputStream = AnsiConsole.wrapOutputStream(bufferedOutputStream);
			outputStream.flush();
			long duration = System.currentTimeMillis() - startTime;
			System.out.println("method3: " + duration);
		}

		private char returnStrPos(double g)//takes the grayscale value as parameter
		{
			final char str;

			if (g >= 230.0) {
				str = '#';
			}
			else if (g >= 200.0) {
				str = '.';
			}
			else if (g >= 180.0) {
				str = '*';
			}
			else if (g >= 160.0) {
				str = ':';
			}
			else if (g >= 130.0) {
				str = 'o';
			}
			else if (g >= 100.0) {
				str = '&';
			}
			else if (g >= 70.0) {
				str = '8';
			}
			else if (g >= 50.0) {
				str = '#';
			}
			else {
				str = '@';
			}

			//			if (g >= 230.0) {
			//				str = 176;
			//			}
			//			else if (g >= 200.0) {
			//				str = 177;
			//			}
			//			else if (g >= 180.0) {
			//				str = 223;
			//			}
			//			else if (g >= 160.0) {
			//				str = 222;
			//			}
			//			else if (g >= 130.0) {
			//				str = 254;
			//			}
			//			else if (g >= 100.0) {
			//				str = 178;
			//			}
			//			else if (g >= 70.0) {
			//				str = 245;
			//			}
			//			else if (g >= 50.0) {
			//				str = 178;
			//			}
			//			else {
			//				str = 219;
			//			}
			return str; // return the character

		}

		private float[] getHSB(java.awt.Color color) {
			float hsb[] = new float[3];
			int   r     = (color.getRGB() >> 16) & 0xFF;
			int   g     = (color.getRGB() >> 8) & 0xFF;
			int   b     = (color.getRGB()) & 0xFF;
			return java.awt.Color.RGBtoHSB(r, g, b, hsb);
		}

		private Ansi.Color getANSIColor(java.awt.Color color) {
			float[] hsb = getHSB(color);

			if (hsb[1] < 0.1 && hsb[2] > 0.9) {
				return Ansi.Color.WHITE;
			}
			else if (hsb[2] < 0.1) {
				return Ansi.Color.BLACK;
			}
			else {
				float deg = hsb[0] * 360;
				if (deg >= 0 && deg < 30) {
					return Ansi.Color.RED;
				}
				else if (deg >= 30 && deg < 90) {
					return Ansi.Color.YELLOW;
				}
				else if (deg >= 90 && deg < 150) {
					return Ansi.Color.GREEN;
				}
				else if (deg >= 150 && deg < 210) {
					return Ansi.Color.CYAN;
				}
				else if (deg >= 210 && deg < 270) {
					return Ansi.Color.BLUE;
				}
				else if (deg >= 270 && deg < 330) {
					return Ansi.Color.MAGENTA;
				}
				else {
					return Ansi.Color.RED;
				}
			}
		}
	}

}
