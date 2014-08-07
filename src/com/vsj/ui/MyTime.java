package com.vsj.ui;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyTime {
	JFrame frame = null;
	JLabel label = null;

	public MyTime() {
		frame = new JFrame("时钟");
		label = new JLabel();
		label.setFont(new Font("幼圆", 1, 40));
		label.setBackground(Color.BLUE);
		frame.add(label);
		frame.setSize(200, 90);
		frame.setLocation(500, 300);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		MyTime mt = new MyTime();
		new TimeThread(mt).start();
	}
}

class TimeThread extends Thread {
	private MyTime mt;

	public TimeThread(MyTime mt) {
		this.mt = mt;
	}

	public void run() {
		while (true) {

			String fullTime = new Date().toString();
			String time = fullTime.substring(11, 19);

			mt.label.setText(time);
			mt.label.repaint();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
