/*
 * JAVE - A Java Audio/Video Encoder (based on FFMPEG)
 * 
 * Copyright (C) 2008-2009 Carlo Pelliccia (www.sauronsoftware.it)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.itlieutenant.core;

import java.io.File;

/**
 * The default ffmpeg executable locator, which exports on disk the ffmpeg
 * executable bundled with the library distributions. It should work both for
 * windows and many linux distributions. If it doesn't, try compiling your own
 * ffmpeg executable and plug it in JAVE with a custom {@link FFMPEGLocator}.
 * 
 * @author Carlo Pelliccia
 */
public class DefaultFFMPEGLocator extends FFMPEGLocator {

	/**
	 * Trace the version of the bundled ffmpeg executable. It's a counter: every
	 * time the bundled ffmpeg change it is incremented by 1.
	 */
	private static final String myFfmpegVersion = "3.1.4";

	/**
	 * The ffmpeg executable file path.
	 */
	private String path;

	public DefaultFFMPEGLocator(String path) {
		File exe = new File(path);
		if (exe.exists()) {
			this.path = path;
		} else {
			defaultFFMPEGLocator();
		}
	}

	/**
	 * It builds the default FFMPEGLocator, exporting the ffmpeg executable on a
	 * temp file.
	 */
	public void defaultFFMPEGLocator() {

		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("mac") != -1) {
			this.path = "/usr/local/Cellar/ffmpeg/" + myFfmpegVersion + "/bin/ffmpeg";
		} else {// linux centos
			this.path = "/root/bin/ffmpeg";
		}
		// Ok.
	}

	protected String getFFMPEGExecutablePath() {
		return path;
	}

}
