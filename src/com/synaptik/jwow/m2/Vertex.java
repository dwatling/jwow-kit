package com.synaptik.jwow.m2;

import java.nio.ByteBuffer;

import com.synaptik.jwow.ByteBufferUtil;

/**
@author Dan Watling <dan@synaptik.com>
 */
public class Vertex {
	float[] position = new float[3];
	byte[] boneWeight = new byte[4];
	byte[] boneIndices = new byte[4];
	float[] normal = new float[3];
	float[] textureCoords = new float[2];
	float[] unknown = new float[2];
	
	public static Vertex read(ByteBuffer bb) {
		Vertex result = new Vertex();
		
		result.position = ByteBufferUtil.readFloats(bb, result.position.length);
		result.boneWeight = ByteBufferUtil.readBytes(bb, result.boneWeight.length);
		result.boneIndices = ByteBufferUtil.readBytes(bb, result.boneIndices.length);
		result.normal = ByteBufferUtil.readFloats(bb, result.normal.length);
		result.textureCoords = ByteBufferUtil.readFloats(bb, result.textureCoords.length);
		result.unknown = ByteBufferUtil.readFloats(bb, result.unknown.length);
		
		return result;
	}
}
