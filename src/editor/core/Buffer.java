package editor.core;

public class Buffer {
         
	StringBuffer buffer;
	
	public Buffer(StringBuffer buffer)
	{
		this.buffer = buffer;
	}
	
	public StringBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}

	public void replace(int start,int stop,String repl)
	{
		buffer.replace(start, stop, repl);
	}
	
	public void print()
	{
		System.out.println(buffer.toString());
	}
}
