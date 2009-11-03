package com.synaptik.jwow.a3d;

//import com.ardor3d.math.Vector3;
//import com.ardor3d.scenegraph.MeshData;
//import com.ardor3d.util.geom.BufferUtils;
import com.synaptik.jwow.m2.M2;
import com.synaptik.jwow.m2.Vertex;
import com.synaptik.jwow.skin.Skin;

public class M2Mesh /*extends MeshData */ {
	protected M2 model;
	protected Skin skin;
	
	public M2Mesh(M2 model, Skin skin) {
		this.model = model;
		this.skin = skin;
	}
	
	protected void buildMesh() {
//		super.setVertexBuffer(BufferUtils.createVector3Buffer(super.getVertexBuffer(), model.data.vertexes.length));
//		super.setNormalBuffer(BufferUtils.createVector3Buffer(model.data.vertexes.length));
//		super.setIndexBuffer(BufferUtils.createIntBuffer(skin.data.indexes.length));
//		for (int index = 0; index < model.data.vertexes.length; index ++) {
//			Vertex vertex = model.data.vertexes[index];
//			Vector3 position = new Vector3(vertex.position[0], vertex.position[1], vertex.position[2]);
//			Vector3 normal = new Vector3(vertex.normal[0], vertex.normal[1], vertex.normal[2]);
//			BufferUtils.setInBuffer(position, super.getVertexBuffer(), 0);
//			BufferUtils.setInBuffer(normal, super.getNormalBuffer(), 0);
//		}
	}
}
