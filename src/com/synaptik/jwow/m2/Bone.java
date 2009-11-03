package com.synaptik.jwow.m2;

import java.nio.ByteBuffer;

import com.synaptik.jwow.ByteBufferUtil;

/**
@author Dan Watling <dan@synaptik.com>
 */
public class Bone {
	int animationSequence;
	int flags;
	short parentBone;
	short geosetID;
	int unknown;
//	0x10	ABlock	Translation	An animationblock for translation. Should be 3*floats.
//	0x24	ABlock	Rotation	An animationblock for rotation. Should be 4*shorts, see Quaternion values and 2.x.
//	0x38	ABlock	Scaling	An animationblock for scaling. Should be 3*floats.
	float[] pivotPoint = new float[3];
	
	public static Bone read(ByteBuffer bb) {
		Bone result = new Bone();
		
		result.animationSequence = bb.getInt();
		result.flags = bb.getInt();
		result.parentBone = bb.getShort();
		result.geosetID = bb.getShort();
		result.unknown = bb.getInt();
		result.pivotPoint = ByteBufferUtil.readFloats(bb, result.pivotPoint.length);
		
		return result;
	}
}
