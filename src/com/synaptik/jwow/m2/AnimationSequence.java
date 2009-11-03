package com.synaptik.jwow.m2;

import java.nio.ByteBuffer;

import com.synaptik.jwow.ByteBufferUtil;

/**
@author Dan Watling <dan@synaptik.com>
 */
public class AnimationSequence {
	short animationID;
	short subAnimationID;
	int length;
	float movingSpeed;
	int flags;
	int flags2;
	int unknown1;
	int unknown2;
	int playbackSpeed;
	float[] boundingBox = new float[6];
	float radius;
	short nextAnimation;
	short index;
	
	public static AnimationSequence read(ByteBuffer bb) {
		AnimationSequence result = new AnimationSequence();
		
		result.animationID = bb.getShort();
		result.subAnimationID = bb.getShort();
		result.length = bb.getInt();
		result.movingSpeed = bb.getFloat();
		result.flags = bb.getInt();
		result.flags2 = bb.getInt();
		result.unknown1 = bb.getInt();
		result.unknown2 = bb.getInt();
		result.playbackSpeed = bb.getInt();
		result.boundingBox = ByteBufferUtil.readFloats(bb, 6);
		result.radius = bb.getFloat();
		result.nextAnimation = bb.getShort();
		result.index = bb.getShort();
		
		return result;
	}
}
