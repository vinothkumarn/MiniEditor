package editor.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import editor.core.*;


public class InsertTest {

	
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
		
		String initialText = "Test";
		StringBuffer buf = new StringBuffer(initialText);
		buffer.setBuffer(buf); // set some buffer
		
		
		String str = "This is a sample test";
		miniEditorReceiver.editorInsert(str, 0);
		
		assertEquals(str+initialText,buffer.getBuffer().toString());
		
	}

}
