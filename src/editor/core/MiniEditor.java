package editor.core;

public interface MiniEditor
{
	public String getBuffer();
	public String getSelection();
	public String getClipboard();

	public void editorInsert(String substring,int position);
	public void editorSelect(int start, int stop);
	public void editorCopy();
	public void editorCut();
	public void editorPaste();
}