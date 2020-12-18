import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class fracGame{
	
	private static Clip audClip;
	private static long clipPosition;

	public static void main(String[] args) {
		String musicDir = "A://Software/Java Projects/Fraction Game/jazz loop.wav";
		
		try {
			File musicPath = new File(musicDir);
			
			if(musicPath.exists()) {
				AudioInputStream audInput = AudioSystem.getAudioInputStream(musicPath);
				
				audClip = AudioSystem.getClip();
				
				audClip.open(audInput);
				audClip.start();
				audClip.loop(Clip.LOOP_CONTINUOUSLY);	//Loops the background music.

				login launchLogin = new login();	//Load the login screen.
			}
			else {
				System.out.println("Can't find file.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pauseMusic() {
		clipPosition = audClip.getMicrosecondPosition();
		audClip.stop();
	}
	
	public static void playMusic() {
		audClip.setMicrosecondPosition(clipPosition);
		audClip.start();
	}
	
}
