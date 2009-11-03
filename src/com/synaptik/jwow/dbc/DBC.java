package com.synaptik.jwow.dbc;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.synaptik.jwow.ByteBufferUtil;
import com.synaptik.jwow.JWoWException;

/**
Reference: http://madx.dk/wowdev/wiki/index.php?title=DBC

@author Dan Watling <dan@synaptik.com>
 */
public class DBC {

	byte[] signature = new byte[4];
	int nRecords;
	int nFields;
	int lRecord;
	int lStringBlock;
	byte[] data;
	byte[] stringData;
	
	public static DBC read(File f) throws Exception {
		FileInputStream fis = null;
		DBC result = null;
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
	
	public static DBC read(ByteBuffer bb) {
		bb.order(ByteOrder.LITTLE_ENDIAN);
		DBC result = new DBC();
		
		bb.get(result.signature);
		result.nRecords = bb.getInt();
		result.nFields = bb.getInt();
		result.lRecord = bb.getInt();
		result.lStringBlock = bb.getInt();
		
		result.data = new byte[result.nRecords * result.lRecord];
		bb.get(result.data);
		
		result.stringData = new byte[result.lStringBlock];
		bb.get(result.stringData);
		
		return result;
	}
	
	public int size() {
		return nRecords;
	}
	
	public byte[] getRecord(int index) {
		byte[] result = new byte[lRecord];
		
		System.arraycopy(data, index*lRecord, result, 0, lRecord);
		
		return result;
	}
	
	public int[] getRecordAsIntArray(int index) {
		byte[] data = getRecord(index);
		int fieldLength = lRecord / nFields;
		int[] result = new int[nFields];
		for (int i = 0; i < result.length; i ++) {
			int value = 0;
			int t1 = ByteBufferUtil.normalizeByte(data[i * fieldLength]);
			int t2 = ByteBufferUtil.normalizeByte(data[i * fieldLength +1]) << 8;
			int t3 = ByteBufferUtil.normalizeByte(data[i * fieldLength +2]) << 16;
			int t4 = ByteBufferUtil.normalizeByte(data[i * fieldLength +3]) << 24;
			value = t1 + t2 + t3 + t4;
			result[i] = value;
		}
		
		return result;
	}
	
	public String getString(int record, int column) {
		int[] data = getRecordAsIntArray(record);
		String result = "";
		int offset = data[column];
		boolean done = false;
		while (!done) {
			if (stringData[offset] != 0) {
				byte b = stringData[offset++];
				result += (char)b;
			} else {
				done = true;
			}
		}
		
		return result;
	}
	
	public int getInt(int record, int column) throws JWoWException {
		if (column > nFields) {
			throw new JWoWException("Column exceeds number of fields (column: " + column +", nFields: " + nFields+")");
		}
		int[] data = getRecordAsIntArray(record);
		
		return data[column];
	}
	
	public String toString() {
		return "signature: " + new String(signature) + 
			"; nRecords: " + nRecords +
			"; nFields: " + nFields + 
			"; lRecord: " + lRecord +
			"; lStringBlock: " + lStringBlock;
	}
}
