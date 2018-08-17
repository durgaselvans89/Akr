import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

    public class CreateLinkExample{

    public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    Link link = new Link(shell, SWT.NONE);
    String message = "Java is an <a>Platform Independent</a>,<a>Robust</a>,<a>Object Oriented</a>Programming Language.Also have<a> Automatic Memory Management.</a>";
    link.setText(message);
    link.setSize(800, 100);
    link.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event event) {
        System.out.println("You have selected: "+event.text);
      }
    });
    shell.setText("Show Link");
    shell.open();
  shell.pack();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}