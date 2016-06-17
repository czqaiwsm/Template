package com.download.update;


/**
 * 
 * @author yuancheng
 * 
 * @version 2013-7-9 上午8:52:19
 */
public class UpdateInfo {

	private UpdateMode mUpdateMode;
	private String mUpdateVersion;
	private String mDownloadUrl;
	private String mUpdateDesc;

	private String isForceUpdate = "";

	private boolean isForceDown = false;

	public UpdateInfo() {
	}

	public void setUpdateMode(UpdateMode updateMode) {
		this.mUpdateMode = updateMode;
	}

	public UpdateMode getUpdateMode() {
		return this.mUpdateMode;
	}

	public void setUpdateDesc(String updateInfo) {
		this.mUpdateDesc = updateInfo;
	}

	public String getUpdateDesc() {
		return mUpdateDesc;
	}

	public void setUpdateVersion(String updateVersion) {
		this.mUpdateVersion = updateVersion;
	}

	public String getUpdateVersion() {
		return mUpdateVersion;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.mDownloadUrl = downloadUrl;
	}

	public String getDownloadUrl() {
		return mDownloadUrl;
	}

	public String getIsForceUpdate() {
		return isForceUpdate;
	}

	public void setIsForceUpdate(String isForceUpdate) {
		this.isForceUpdate = isForceUpdate;
	}

	public boolean getIsForceDown() {
		mUpdateMode = UpdateMode.USER_SELECT;
		isForceDown = false;
		if ("1".equalsIgnoreCase(isForceUpdate)){
			mUpdateMode = UpdateMode.FORCE_UPDATE;
			isForceDown = true;

		}
		return  isForceDown;
	}


}
