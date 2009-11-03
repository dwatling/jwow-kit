package com.synaptik.jwow.skin;

import java.nio.ByteBuffer;

/**
@author Dan Watling <dan@synaptik.com>
 */
public class TextureUnit {
	short flags;
	short renderOrder;
	short submeshIndex;
	short submeshIndex2;
	short colorIndex;
	short renderFlags;
	short texUnitNumber;
	short unknown;
	short texture;
	short texUnitNumber2;
	short transparency;
	short textureAnim;

	
	public static TextureUnit read(ByteBuffer bb) {
		TextureUnit result = new TextureUnit();
		
		result.flags = bb.getShort();
		result.renderOrder = bb.getShort();
		result.submeshIndex = bb.getShort();
		result.submeshIndex2 = bb.getShort();
		result.colorIndex = bb.getShort();
		result.renderFlags = bb.getShort();
		result.texUnitNumber = bb.getShort();
		result.unknown = bb.getShort();
		result.texture = bb.getShort();
		result.texUnitNumber2 = bb.getShort();
		result.transparency = bb.getShort();
		result.textureAnim = bb.getShort();
		
		return result;
	}
}
