package com.synaptik.jwow.anim;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
References: http://madx.dk/wowdev/wiki/index.php?title=M2/WotLK/.anim

@author Dan Watling <dan@synaptik.com>
 */
public class Anim {
	
	public static Anim read(File f) throws Exception {
		FileInputStream fis = null;
		Anim result = null;
		try {
			ByteBuffer bb = ByteBuffer.allocate((int)f.length());
			fis = new FileInputStream(f);
			fis.getChannel().read(bb);
			bb.rewind();
			
			result = read(bb);
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return result;
	}
	
	public static Anim read(ByteBuffer bb) {
		bb.order(ByteOrder.LITTLE_ENDIAN);
		Anim result = new Anim();
		
		// Not sure what the format is...
		
		return result;
	}
}
