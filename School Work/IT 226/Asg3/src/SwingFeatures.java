import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;


public class SwingFeatures 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		SwingFeaturesFrame.setDefaultLookAndFeelDecorated(false);
		SwingFeaturesFrame frame = new SwingFeaturesFrame();
		
		frame.setVisible(true);
		
		try 
		{
	            // Set cross-platform Java L&F (also called "Metal")
	        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        
	        //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
	        
	      //  UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	    //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    //    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
	    //    {
	     //       if ("Nimbus".equals(info.getName())) {
	      //          UIManager.setLookAndFeel(info.getClassName());
	       //         break;
	        //    }
	       // }
        }
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		catch (Exception e){}
	    
	}

}
