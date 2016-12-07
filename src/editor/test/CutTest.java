package editor.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import editor.core.Buffer;
import editor.core.MiniEditorReceiver;


public class CutTest {

	
	private Buffer buffer;
	private MiniEditorReceiver miniEditorReceiver;
	private String text="";
	
	
	@Before
	public void initialize()
	{
		buffer = new Buffer(new StringBuffer(text));
		miniEditorReceiver = new MiniEditorReceiver(buffer);
	
	}
		
	@Test
	public void test() {
		
		StringBuffer buf = new StringBuffer("Test");
		buffer.setBuffer(buf); // set some buffer
		
		miniEditorReceiver.editorSelect(0,2);
		miniEditorReceiver.editorCut();
		
		assertEquals("st",buffer.getBuffer().toString());
	
	}

}
