package com.synaptik.jwow.wmo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.synaptik.jwow.ByteBufferUtil;

/**
Reference: http://madx.dk/wowdev/wiki/index.php?title=WMO
@author Dan Watling <dan@synaptik.com>
**/
public class WMORoot {
	int nTextures;
	int nGroups;
	int nPortals;
	int nLights;
	int nModels;
	int nDoodads;
	int nSets;
	int ambientColor;
	int wmoID;
	float[] corner1 = new float[3];
	float[] corner2 = new float[3];
	int unknown;
			
	public static WMORoot read(ByteBuffer bb) {
		bb.order(ByteOrder.LITTLE_ENDIAN);
		WMORoot result = new WMORoot();
		result.nTextures = bb.getInt();
		result.nGroups = bb.getInt();
		result.nPortals = bb.getInt();
		result.nLights = bb.getInt();
		result.nModels = bb.getInt();
		result.nDoodads = bb.getInt();
		result.nSets = bb.getInt();
		result.ambientColor = bb.getInt();
		result.wmoID = bb.getInt();
		result.corner1 = ByteBufferUtil.readFloats(bb, result.corner1.length);
		result.corner2 = ByteBufferUtil.readFloats(bb, result.corner2.length);
		result.unknown = bb.getInt();
		
		return result;
	}
}
