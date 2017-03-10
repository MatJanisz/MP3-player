package mp3.player.apk.tool;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Tools {

	FileInputStream fis;
	BufferedInputStream bis;
	public Player player;
	public long pauseLocation;
	public long songTotalLength;
	public String fileLocation;
	public boolean Playlogic = true, ResumeLogic = false;

	public void Stop() {
		if (player != null) {
			player.close();
			pauseLocation = 0;
			songTotalLength = 0;
			Playlogic = true;
		}
	}

	public void Pause() {
		if (player != null) {
			try {
				pauseLocation = fis.available();
				player.close();
				ResumeLogic = true;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void Play(String path) {
		if (Playlogic) {
			try {
				fis = new FileInputStream(path);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
				songTotalLength = fis.available();
				fileLocation = path + "";
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			new Thread() {
				public void run() {
					try {
						Playlogic = false;
						player.play();
						if (player.isComplete())
							Playlogic = true;
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}.start();
		}

	}

	public void Resume() {
		if (ResumeLogic) {
			try {
				fis = new FileInputStream(fileLocation);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
				fis.skip(songTotalLength - pauseLocation);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			new Thread() {
				public void run() {
					try {
						ResumeLogic = false;
						player.play();
						if(player.isComplete())
						{
							ResumeLogic = false;
							Playlogic = true;
						}
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}.start();
		}

	}
}
