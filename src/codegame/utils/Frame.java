package codegame.utils;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class Frame {
	protected Display display;
	protected Shell shell;
	public Frame(String title,int width,int height) {
		display = Display.getDefault();
		shell = new Shell();
		shell.setSize(width,height);
		shell.setText(title);
		init();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	protected abstract  void init();
}
