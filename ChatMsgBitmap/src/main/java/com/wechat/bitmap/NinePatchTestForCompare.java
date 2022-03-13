package com.wechat.bitmap;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * https://www.cnblogs.com/javawebsoa/p/2992104.html
 * @author wangqx
 *
 */
@SuppressWarnings("serial")
public class NinePatchTestForCompare extends JPanel {
	BufferedImage image;

	/**
	 * This constructor uses a default flow layout and a double-buffering
	 * strategy.
	 */
	public NinePatchTestForCompare() {
		image = loadImage("/images/content_bg2.png");

		add(new JButton("Button 1"));
		add(new JButton("Button 2"));
		add(new JButton("Button 3"));
		add(new JButton("Button 4"));
	}

	/**
	 * @param path
	 *            - the image path.
	 * @return an BufferedImage object, or {@code null} if the given path is not
	 *         valid or an error occurs during loading.
	 */
	private BufferedImage loadImage(String path) {
		InputStream stream = this.getClass().getResourceAsStream(path);
		if (stream != null) {
			try {
				return ImageIO.read(stream);
			} catch (IOException e) {
				System.err.println("An error occurs during loading.");
				e.printStackTrace();
				return null;
			}
		} else {
			System.err.println("Couldn't find the file: " + path);
			return null;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle clip = g2.getClipBounds();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, clip.x, clip.y, clip.width, clip.height, null);
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("NinePatchTestForCompare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		JPanel contentPane = new NinePatchTestForCompare();
		contentPane.setOpaque(true); // content pane must be opaque
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		frame.setContentPane(contentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the EDT:
		// Creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}

		});
	}

}