package com.jiangyifen.ec2.utils;

import java.util.Date;

import com.vaadin.ui.Label;

public class TimeShowThread extends Thread {

	private Label lb_time;

	public TimeShowThread(Label lb_time) {
		this.lb_time = lb_time;
		System.out.println("inter");
	}

	@Override
	public void run() {
		while (true) {
			String fullTime = new Date().toString();
			String time = fullTime.substring(11, 19);
			lb_time.setCaption(time);
//			System.out.println("-------");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}

}
