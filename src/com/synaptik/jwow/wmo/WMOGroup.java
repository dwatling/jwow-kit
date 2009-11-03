package com.synaptik.jwow.wmo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.synaptik.jwow.ByteBufferUtil;

/**
Reference: http://madx.dk/wowdev/wiki/index.php?title=WMO
@author Dan Watling <dan@synaptik.com>
**/
public class WMOGroup {
	int ofsGroupName;
	int ofsDescriptiveGroupName;
	int flags;
	float[] corner1 = new float[3];
	float[] corner2 = new float[3];
	short idxMOPR;
	short nMOPRItems;
	short nBatchA;
	short nBatchB;
	short nBatchC;
	byte[] idxFog = new byte[4];
	int unknown;
	int wmoGroupID;
	int unknown1;
	int unknown2;
	
	public static WMOGroup read(ByteBuffer bb) {
		bb.order(ByteOrder.LITTLE_ENDIAN);
		WMOGroup result = new WMOGroup();
		result.ofsGroupName = bb.getInt();
		result.ofsDescriptiveGroupName = bb.getInt();
		result.flags = bb.getInt();
		result.corner1 = ByteBufferUtil.readFloats(bb, result.corner1.length);
		result.corner2 = ByteBufferUtil.readFloats(bb, result.corner2.length);
		result.idxMOPR = bb.getShort();
		result.nMOPRItems = bb.getShort();
		result.nBatchA = bb.getShort();
		result.nBatchB = bb.getShort();
		result.nBatchC = bb.getShort();
		result.idxFog = ByteBufferUtil.readBytes(bb, result.idxFog.length);
		result.unknown = bb.getInt();
		result.wmoGroupID = bb.getInt();
		result.unknown1 = bb.getInt();
		result.unknown2 = bb.getInt();
		
		return result;
	}
}
