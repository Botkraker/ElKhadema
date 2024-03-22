package Elkhadema.khadema.domain;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;

public class Media {
	private Post post_id;
	private byte[] media;
	private String mediatype;
	private static int NumberofVidtemps;
	private int numbervidintemp=NumberofVidtemps+1;
	public Post getPost_id() {
		return post_id;
	}
	public void setPost_id(Post post_id) {
		this.post_id = post_id;
	}
	public byte[] getMedia() {
		return media;
	}
	public void setMedia(byte[] media) {
		this.media = media;
	}
	public String getMediatype() {
		return mediatype;
	}
	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}
	public Media(Post post_id, byte[] media, String mediatype) {
		super();
		this.post_id = post_id;
		this.media = media;
		this.mediatype = mediatype;
	}
	public Image getImage() {
		if (mediatype.compareTo("img")==0) {
	        try {
	            return new Image(new ByteArrayInputStream(media));
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
		}
		else {
			System.err.println("wrong type supposed to be img");
			return null;
		}
	}
	public MediaPlayer getVideo() {
		if (mediatype.compareTo("vid")==0) {
	        try {
	        	File tempFile = File.createTempFile("temp/"+numbervidintemp, ".mp4");
	            tempFile.deleteOnExit();
	            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
	                fos.write(media);
	            }
	            NumberofVidtemps++;
	            return new MediaPlayer(new javafx.scene.media.Media(tempFile.toURI().toString()));
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
		}
		else {
			System.err.println("wrong type supposed to be vid");
			return null;
		}
	}
	
}
