package com.fresco.tournament.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int emp[] = new int[10];
		List<Integer> senList = new ArrayList<Integer>();
		List<Integer> ageList = new ArrayList<Integer>();
		for(int i = 0;i<10;i++) {
			int age = in.nextInt();
			if(age<10||age>100) {
				System.out.println("Invalid Input");
			}else if(age>60&&senList.size()<5) {
				senList.add(age);
			}
			else {
				ageList.add(age);
			}
		}
		
		int q1Time = 0;
		System.out.println(ageList);
		for(Integer eTime:ageList) {
			q1Time = q1Time+(eTime*15);
		}
		
		System.out.println("QUEUE1 TIME "+q1Time+" mins");

		int q2Time = 0;
		System.out.println(senList);
		for(Integer sTime:senList) {
			q2Time = q2Time+(sTime*15);
		}
		
		System.out.println("QUEUE2 TIME "+q2Time+" mins");
	}

}
