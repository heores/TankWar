import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener
{
	test t;
	public ButtonHandler(test t)
	{
		this.t = t;
		
	}
	DataCon dc= new DataCon( );
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		dc.fs = t.fs;
		dc.user = t.uset.getText();
		t.uset.setText("");
		
		dc.jiazai();
		dc.getConn();
		dc.inserSQL();
		 
	}

}
