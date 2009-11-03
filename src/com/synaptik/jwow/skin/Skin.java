package com.synaptik.jwow.skin;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.synaptik.jwow.ByteBufferUtil;

/**
References: http://madx.dk/wowdev/wiki/index.php?title=M2/WotLK/.skin
@author Dan Watling <dan@synaptik.com>
 */
public class Skin {
	_offsets offsets;
	_lengths lengths;
	_other other;
	public _data data;
	
	protected Skin() {
		offsets = new _offsets();
		lengths = new _lengths();
		other = new _other();
		data = new _data();
	}
	
	public static Skin read(File f) throws Exception {
		FileInputStream fis = null;
		ByteBuffer bb = ByteBuffer.allocate((int)f.length());
		Skin result = null;
		try {
			fis = new FileInputStream(f);
			fis.getChannel().read(bb);
			bb.rewind();
			result = read(bb);
		} finally {
			fis.close();
		}
		return result;
	}
	public static Skin read(ByteBuffer bb) {
		Skin result = new Skin();
		bb.order(ByteOrder.LITTLE_ENDIAN);	// format is in little endian, ensure the buffer is too
		
		bb.get(result.other.type);
		result.lengths.nIndices = bb.getInt();
		result.offsets.ofsIndices = bb.getInt();
		result.lengths.nTriangles = bb.getInt();
		result.offsets.ofsTriangles = bb.getInt();
		result.lengths.nProperties = bb.getInt();
		result.offsets.ofsProperties = bb.getInt();
		result.lengths.nSubmeshes = bb.getInt();
		result.offsets.ofsSubmeshes = bb.getInt();
		result.lengths.nTextureUnits = bb.getInt();
		result.offsets.ofsTextureUnits = bb.getInt();
		result.other.lod = bb.getInt();
		
		readData(result, bb);
		
		return result;
	}
	
	protected static void readData(Skin result, ByteBuffer bb) {
		bb.position(result.offsets.ofsIndices);
		result.data.indexes = ByteBufferUtil.readShorts(bb, result.lengths.nIndices);
		
		bb.position(result.offsets.ofsTriangles);
		result.data.triangles = ByteBufferUtil.readShorts(bb, result.lengths.nTriangles);
		
		bb.position(result.offsets.ofsProperties);
		result.data.vertexProperties = new byte[result.lengths.nProperties][4];
		for (int index = 0; index < result.data.vertexProperties.length; index ++) {
			bb.get(result.data.vertexProperties[index]);
		}
		
		bb.position(result.offsets.ofsSubmeshes);
		result.data.submeshes = new Submesh[result.lengths.nSubmeshes];
		for (int index = 0; index < result.data.submeshes.length; index ++) {
			result.data.submeshes[index] = Submesh.read(bb);
		}
		
		bb.position(result.offsets.ofsTextureUnits);
		result.data.textureUnits = new TextureUnit[result.lengths.nTextureUnits];
		for (int index = 0; index < result.data.textureUnits.length; index ++) {
			result.data.textureUnits[index] = TextureUnit.read(bb);
		}
	}
	
	class _offsets {
		int ofsIndices;
		int ofsTriangles;
		int ofsProperties;
		int ofsSubmeshes;
		int ofsTextureUnits;
	}
	
	class _lengths {
		int nIndices;
		int nTriangles;
		int nProperties;
		int nSubmeshes;
		int nTextureUnits;
	}
	class _other {
		byte[] type = new byte[4];
		int lod;
	}
	public class _data {
		public short[] indexes;
		public short[] triangles;
		public byte[][] vertexProperties;
		public Submesh[] submeshes;
		public TextureUnit[] textureUnits;
		
		public String toString() {
			return 
				"indexes: " + indexes.length + 
				"; triangles: " + triangles.length + 
				"; vertexProperties: " + vertexProperties.length + 
				"; submeshes: " + submeshes.length + 
				"; textureUnits: " + textureUnits.length;
		}
	}
}
