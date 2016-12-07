package editor.core;

public class MementoState {
	
	private String command;
	private Buffer buffer;
	private int start;
	private int end;
	private String clipboard;
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public Buffer getBuffer() {
		return buffer;
	}
	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}
	public String getClipboard() {
		return clipboard;
	}
	public void setClipboard(String clipboard) {
		this.clipboard = clipboard;
	}
	
	

}
