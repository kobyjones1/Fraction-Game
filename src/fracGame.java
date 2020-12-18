import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class fracGame{
	
	private static Clip audClip;
	private static long clipPosition;

	public static void main(String[] args) {
		try {
			File musicPath = new File("./jazz loop.wav");	//Music for loop.

			if(musicPath.exists()) {
				AudioInputStream audInput = AudioSystem.getAudioInputStream(musicPath);
				
				audClip = AudioSystem.getClip();
				
				audClip.open(audInput);
				audClip.start();
				audClip.loop(Clip.LOOP_CONTINUOUSLY);	//Loops the background music.

				login launchLogin = new login();	//Launches the login window.
			}
			else {
				JOptionPane.showMessageDialog(null, "Can't find file.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pauseMusic() {	//Pauses the music if it's currently playing.
		clipPosition = audClip.getMicrosecondPosition();
		audClip.stop();
	}
	
	public static void playMusic() {	//Play the music if it's currently paused.
		audClip.setMicrosecondPosition(clipPosition);
		audClip.start();
	}
	
}
