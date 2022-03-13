package com.wechat.bitmap;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * JAVA如何在为窗口设置背景图片
 * https://blog.csdn.net/Cannel_2020/article/details/7063366
 * @author wangqx
 *
 */
public class MainJFrame extends JFrame {
 
	public MainJFrame() {
		//设置标题
		super("JFram设置背景图片(Cannel_2020)");
		//设置大小
		setSize(500, 400);
		//设置位置
		setLocation(200, 50);
		//背景图片的路径。（相对路径或者绝对路径。本例图片放于"java项目名"的文件下）
		String path = "brx.9.png";
		// 背景图片
		ImageIcon background = new ImageIcon(path);
		// 把背景图片显示在一个标签里面
		JLabel label = new JLabel(background);
		// 把标签的大小位置设置为图片刚好填充整个面板
		label.setBounds(0, 0, this.getWidth(), this.getHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		// 把背景图片添加到分层窗格的最底层作为背景
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		//设置可见
		setVisible(true);
		//点关闭按钮时退出
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//https://blog.csdn.net/tan313/article/details/40537971  java编写界面设置 背景图片的大小
	public void test() {
		ImageIcon background = new ImageIcon("background.jpg");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// 得到电脑屏幕的大小
		Image temp = background.getImage().getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(),
				background.getImage().SCALE_DEFAULT);// 设置图片大小跟屏幕大小一致
		background = new ImageIcon(temp);// 背景图片等于设置好后大小的图片
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
	}
 
	public static void main(String[] args) {
		new MainJFrame();
	}
}
