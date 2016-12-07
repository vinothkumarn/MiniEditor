package editor.core;

public class MiniEditorReceiver implements MiniEditor
{
	Buffer buffer;
	String clipboard;
	int start;
	int stop;
	
	public MiniEditorReceiver(Buffer buffer)
	{
		this.buffer = buffer;
	}

	@Override
	public String getBuffer()
	{
		return buffer.getBuffer().toString();
	}

	@Override
	public String getSelection()
	{
		return "selected text here" ;
	}

	@Override
	public String getClipboard()
	{
		return clipboard;
	}

	public void setClipboard(String clipboard)
	{
		this.clipboard = clipboard;
	}
	
	@Override
	public void editorInsert(String substring,int position)
	{
		System.out.println("DEBUG: inserting text [" + substring + "]");
		StringBuffer strBuffer = buffer.getBuffer();
		System.out.println("initial:"+strBuffer);
		if(substring.equals("\n"))
		{
			strBuffer.append(substring);
		}
		else
		{
		strBuffer.insert(position,substring);
		}
		System.out.println("inserted:"+strBuffer);
	}

	@Override
	public void editorSelect(int start, int stop)
	{
		System.out.println("DEBUG: selecting interval [" + start + "," + stop + "]");
		this.start = start;
		this.stop = stop;
	}

	@Override
	public void editorCopy()
	{
		System.out.println("DEBUG: performing Copy") ;
		StringBuffer strBuffer = buffer.getBuffer();
		setClipboard(strBuffer.substring(start, stop));
		System.out.println(getClipboard());
	}

	@Override
	public void editorCut()
	{
		System.out.println("DEBUG: performing Cut") ;
		StringBuffer strBuffer = buffer.getBuffer();
		setClipboard(strBuffer.substring(start, stop));
		System.out.println(getClipboard());
		strBuffer.replace(start,stop,"");
		System.out.println(buffer.getBuffer());
	}

	@Override
	public void editorPaste()
	{
		System.out.println("DEBUG: performing Paste") ;
		StringBuffer strBuffer = buffer.getBuffer();
		strBuffer.insert(start,getClipboard());
		System.out.println("pasted:"+buffer.getBuffer());
	}

	public void editorUpdate(String text) 
	{	
	   StringBuffer strBuffer = new StringBuffer(text);
	   buffer = new Buffer(strBuffer);
	   System.out.println(buffer.getBuffer());
	}
}