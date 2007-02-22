package org.gwm.splice.client.service.data;

import java.util.HashMap;
import java.util.Map;

import org.gwm.splice.client.desktop.DesktopManager;

import com.google.gwt.user.client.ui.Image;

/**
 * A registry for storing information about mime types, such as images.
 * 
 * @author Andy Scholz (andy.scholz@gmail.com)
 * 
 */
public class MimeTypeRegistry {

	private static MimeTypeRegistry instance;

	private Map registry = new HashMap();
	
	private String defaultSmallIconName = "mimetypes/unknown";

	private String defaultLargeIconName = "mimetypes/unknown";

	public static MimeTypeRegistry getInstance() {
		if (instance == null) {
			instance = new MimeTypeRegistry();
		}
		return instance;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	MimeTypeRegistry() {
		
		//TODO add defaults..........
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	public void register(String mimeType, String imageName) {
		registry.put(mimeType, new MimeTypeInfo(mimeType, imageName));
	}

	public void deregister(String mimeType) {
		registry.remove(mimeType);
	}

	public Image getSmallIcon(String mimeType) {
		MimeTypeInfo info = (MimeTypeInfo) registry.get(mimeType);
		if(info != null) {
			Image image = new Image(DesktopManager.getInstance().getSmallIconUrl(info.imageName));
			image.setPixelSize(16, 16);
			return image;
		}
		return getDefaultSmallIcon();
	}

	public Image getLargeIcon(String mimeType) {
		MimeTypeInfo info = (MimeTypeInfo) registry.get(mimeType);
		if(info != null) {
			Image image = new Image(DesktopManager.getInstance().getLargeIconUrl(info.imageName));
			image.setPixelSize(32, 32);
			return image;
		}
		return getDefaultLargeIcon();
	}

	// //////////////////////////////////////////////////////////////////////////////////

	private class MimeTypeInfo {
		String mimeType;

		String imageName;

		public MimeTypeInfo(String mimeType, String imageName) {
			this.mimeType = mimeType;
			this.imageName = imageName;
		}

	}

	// //////////////////////////////////////////////////////////////////////////////////

	/**
	 * @return the defaultLargeIcon
	 */
	public Image getDefaultLargeIcon() {
		return new Image(DesktopManager.getInstance().getLargeIconUrl(defaultLargeIconName));
	}

	/**
	 * @return the defaultSmallIcon
	 */
	public Image getDefaultSmallIcon() {
		return new Image(DesktopManager.getInstance().getSmallIconUrl(defaultSmallIconName));
	}

	// //////////////////////////////////////////////////////////////////////////////////

	/**
	 * @return the defaultLargeIconName
	 */
	public String getDefaultLargeIconName() {
		return defaultLargeIconName;
	}

	/**
	 * @param defaultLargeIconName the defaultLargeIconName to set
	 */
	public void setDefaultLargeIconName(String defaultLargeIconName) {
		this.defaultLargeIconName = defaultLargeIconName;
	}

	/**
	 * @return the defaultSmallIconName
	 */
	public String getDefaultSmallIconName() {
		return defaultSmallIconName;
	}

	/**
	 * @param defaultSmallIconName the defaultSmallIconName to set
	 */
	public void setDefaultSmallIconName(String defaultSmallIconName) {
		this.defaultSmallIconName = defaultSmallIconName;
	}

}
