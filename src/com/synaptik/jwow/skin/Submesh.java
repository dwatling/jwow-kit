package com.synaptik.jwow.skin;

import java.nio.ByteBuffer;

import com.synaptik.jwow.ByteBufferUtil;

/**
@author Dan Watling <dan@synaptik.com>
 */
public class Submesh {
	int	id;
	short startVertex;
	short nVertices;
	short startTriangle;
	short nTriangles;
	short nBones;
	short startBones;
	short unknown;
	short rootBone;
	float[] position = new float[3];
	float[] floats = new float[4];
	
	public static Submesh read(ByteBuffer bb) {
		Submesh result = new Submesh();
		
		result.id = bb.getInt();
		result.startVertex = bb.getShort();
		result.nVertices = bb.getShort();
		result.startTriangle = bb.getShort();
		result.nTriangles = bb.getShort();
		result.nBones = bb.getShort();
		result.startBones = bb.getShort();
		result.unknown = bb.getShort();
		result.rootBone = bb.getShort();
		result.position = ByteBufferUtil.readFloats(bb, result.position.length);
		result.floats = ByteBufferUtil.readFloats(bb, result.floats.length);
		
		return result;
	}
}
