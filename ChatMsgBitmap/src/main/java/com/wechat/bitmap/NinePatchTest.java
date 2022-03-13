package com.wechat.bitmap;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.android.ninepatch.NinePatch;
/**
 * https://www.cnblogs.com/javawebsoa/p/2992104.html
 * @author wangqx
 *
 */
@SuppressWarnings("serial")
public class NinePatchTest extends JPanel {
	NinePatch ninePatch;

	/**
	 * This constructor which serves as a content pane uses a default flow
	 * layout and a double-buffering strategy.
	 */
	public NinePatchTest() {
		ninePatch = loadNinePatch("/images/content_bg1.9.png");

		add(new JButton("Button 1"));
		add(new JButton("Button 2"));
		add(new JButton("Button 3"));
		add(new JButton("Button 4"));
	}

	/**
	 * @param path
	 *            - the image path.
	 * @return an NinePatch object, or {@code null} if the given path is not
	 *         valid or an error occurs during loading.
	 */
	private NinePatch loadNinePatch(String path) {
		InputStream stream = this.getClass().getResourceAsStream(path);
		if (stream != null) {
			try {
				return NinePatch.load(stream, true, false);
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

	/**
	 * To improve the repaint speed, the code block contained in
	 * paintComponent() must be able to be executed quickly. For example, we
	 * usually put the load image code out of the paintComponent() for rapid UI
	 * update.
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle clip = g2.getClipBounds();
		ninePatch.draw(g2, clip.x, clip.y, clip.width, clip.height);
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("NinePatch test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		JPanel contentPane = new NinePatchTest();
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
