package com.synaptik.jwow;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.synaptik.jwow.blp.BLP;

/**
 * It is meant that you have BLP data extracted from the MPQ files already.
 * Use MPQEditor (http://zezula.net) if you need one.
 * 
 * This utility will just convert all BLP files found in the path specified to a PNG
 * file in the directory you run this from.
 * 
 * ---
 * 
 * Dan Watling <dan@synaptik.com>
 */
public class BLP2CUR {

	public static void main(String[] args) throws Exception {
		if (args.length == 1) {
			convertAllBLPsToCURs(args[0]);
		} else {
			System.out.println("Usage: blp2cur <folder to convert>");
			System.out.println("Example: blp2cur C:\\Games\\WoW\\extract\\blp");
			System.out.println("         Will convert all BLPs in all subdirectories underneath specified folder");
		}
	}
	
	protected static void convertAllBLPsToCURs(String rootFolder) throws Exception {
		List<File> f = getAllBLPs(new File(rootFolder));
		
		for (int index = 0; index < f.size(); index ++) {
			FileInputStream fis = null;
			try {
				ByteBuffer bb = ByteBuffer.allocate((int)f.get(index).length());
				fis = new FileInputStream(f.get(index));
				fis.getChannel().read(bb);
				bb.rewind();
				BLP blp = BLP.read(bb);
				if (blp != null) {
					BufferedImage bi = blp.getBufferedImage();
					System.out.println(blp.toString() + " - " + f.get(index).getAbsolutePath());
					if (bi == null) {
						System.out.println("-- UNSUPPORTED --");
					} else {
						writeCUR(bi, f.get(index).getAbsolutePath());
					}
				}
			} finally {
				if (fis != null) {
					fis.close();
				}
			}
		}
	}
	
	protected static List<File> getAllBLPs(File f) {
		List<File> result = new ArrayList<File>();
		
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (int index = 0; index < files.length; index ++) {
				if (files[index].isDirectory() && !".svn".equals(files[index].getName())) {
					result.addAll(getAllBLPs(files[index]));
				}
				if (files[index].getName().toUpperCase().endsWith("BLP")) {
					result.add(files[index]);
				}
			}
		} else {
			result.add(f);
		}
		return result;
		
	}

	private static void writeCUR(BufferedImage image, String f) throws IOException {
		ByteBuffer bb = ByteBuffer.allocate(65536);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		
		bb.putShort((short)0);
		bb.putShort((short)2);
		bb.putShort((short)1);
		bb.put((byte)image.getWidth());
		bb.put((byte)image.getHeight());
		bb.put((byte)0);
		bb.put((byte)0);
		bb.putShort((short)1);
		bb.putShort((short)1);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "PNG", baos);
		bb.putInt(baos.size());
		bb.putInt(bb.position()+4);

		bb.put(baos.toByteArray());
		baos.close();
		
		FileOutputStream fos = new FileOutputStream(f + ".cur");
		int len = bb.position();
		bb.rewind();
		fos.write(bb.array(), 0, len);
		fos.close();
	}
}
