package com.synaptik.jwow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
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
public class BLP2PNG {

	public static void main(String[] args) throws Exception {
		if (args.length == 1) {
			convertAllBLPsToPNGs(args[0]);
		} else {
			System.out.println("Usage: blp2png <folder to convert>");
			System.out.println("Example: blp2png C:\\Games\\WoW\\extract\\blp");
			System.out.println("         Will convert all BLPs in all subdirectories underneath that folder and save it to the folder you are in.");
		}
	}
	
	protected static void convertAllBLPsToPNGs(String rootFolder) throws Exception {
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
						ImageIO.write(bi, "png", new File(f.get(index).getName() + ".png"));
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

}
