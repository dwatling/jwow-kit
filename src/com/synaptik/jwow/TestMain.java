package com.synaptik.jwow;

import java.io.File;

import com.synaptik.jwow.anim.Anim;

public class TestMain {

	public static void main(String[] args) throws Exception {
		Anim a = Anim.read(new File("data/CREATURE/Kobold/Kobold0062-00.anim"));
		System.out.println(a);
	}
}
