package com.synaptik.jwow.a3d;

import java.io.File;

//import com.ardor3d.bounding.BoundingBox;
//import com.ardor3d.example.ExampleBase;
//import com.ardor3d.framework.FrameHandler;
//import com.ardor3d.input.logical.LogicalLayer;
//import com.ardor3d.math.Vector3;
//import com.ardor3d.renderer.queue.RenderBucketType;
//import com.ardor3d.renderer.state.MaterialState;
//import com.ardor3d.renderer.state.MaterialState.ColorMaterial;
//import com.ardor3d.scenegraph.Node;
//import com.ardor3d.scenegraph.hint.LightCombineMode;
//import com.ardor3d.ui.text.BasicText;
import com.synaptik.jwow.m2.M2;
import com.synaptik.jwow.skin.Skin;

public class ModelViewer /*extends ExampleBase */{
	private M2Mesh mesh;
//	private Node t;
	
	public static void main(String[] args) {
//		start(ModelViewer.class);
	}

//	@Inject
//	public ModelViewer(LogicalLayer logicalLayer, FrameHandler frameHandler) {
//		super(logicalLayer, frameHandler);
//	}
//	
//	@Override
//	protected void initExample() {
//		_canvas.setTitle("jWoWModelViewer");
//		
//		final BasicText keyText = BasicText.createDefaultTextLabel("Text", "Some text...");
//        keyText.getSceneHints().setRenderBucketType(RenderBucketType.Ortho);
//        keyText.getSceneHints().setLightCombineMode(LightCombineMode.Off);
//        keyText.setTranslation(new Vector3(0, 20, 0));
//        _root.attachChild(keyText);
//
//        try {
//        	M2 m2 = M2.read(new File("./Data/CREATURE/Deer/Deer.m2"));
//        	Skin skin = Skin.read(new File("./Data/CREATURE/Deer/Deer00.skin"));
//        	mesh = new M2Mesh(m2, skin);
//        	t = new Node("Deer");
////        	t.setModelBound(new BoundingBox());
//        	t.setTranslation(new Vector3(0, 0, -15));
//        	_root.attachChild(t);
//        	System.out.println(m2.getName());
//        } catch (Exception ex) {
//        	// do nothing
//        	ex.printStackTrace(System.err);
//        }
//        
//        
//        final MaterialState ms = new MaterialState();
//        ms.setColorMaterial(ColorMaterial.Diffuse);
//        _root.setRenderState(ms);
//	}
}
