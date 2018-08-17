import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

public class Snippet182 {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		Link link = new Link(shell, SWT.BORDER);
		link.setText("This a very simple <A>link</A> widget.");
		link.setSize(140, 40);
		shell.pack ();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}