/*
 * Copyright (c) 2006/2007 Flipperwing Ltd. (http://www.flipperwing.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwm.splice.client.logger;

import java.util.ArrayList;
import java.util.Iterator;

public class Logger implements ILogger {

	private static ILogger instance;
	private ArrayList listeners = new ArrayList();
	
	public static ILogger getInstance() {
		if(instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	private Logger() {
	}
	
	//////////////////////////////////////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#addLogListener(org.gwm.splice.client.logger.IFWLogListener)
	 */
	public void addLogListener(ILogListener listener) {
		listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#removeLogListener(org.gwm.splice.client.logger.IFWLogListener)
	 */
	public void removeLogListener(ILogListener listener) {
		listeners.remove(listener);
	}

	//////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#log(org.gwm.splice.client.logger.FWLogItem)
	 */
	public void log(LogItem item) {
		for (Iterator iter = listeners.iterator(); iter.hasNext();) {
			ILogListener listener = (ILogListener) iter.next();
			listener.itemLogged(item);
		}
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#log(int, java.lang.String, java.lang.Object)
	 */
	public void log(int level, String message, Object source) {
		log(new LogItem(level, message, source));
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#log(int, java.lang.String)
	 */
	public void log(int level, String message) {
		log(new LogItem(level, message));
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#logInfo(java.lang.String)
	 */
	public void logInfo(String message) {
		log(LogItem.INFO, message);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#logInfo(java.lang.String, java.lang.Object)
	 */
	public void logInfo(String message, Object source) {
		log(LogItem.INFO, message, source);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#logWarning(java.lang.String)
	 */
	public void logWarning(String message) {
		log(LogItem.WARN, message);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#logWarning(java.lang.String, java.lang.Object)
	 */
	public void logWarning(String message, Object source) {
		log(LogItem.WARN, message, source);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#logError(java.lang.String)
	 */
	public void logError(String message) {
		log(LogItem.ERROR, message);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#logError(java.lang.String, java.lang.Object)
	 */
	public void logError(String message, Object source) {
		log(LogItem.ERROR, message, source);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#logSevere(java.lang.String)
	 */
	public void logSevere(String message) {
		log(LogItem.SEVERE, message);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#logSevere(java.lang.String, java.lang.Object)
	 */
	public void logSevere(String message, Object source) {
		log(LogItem.SEVERE, message, source);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#logFatal(java.lang.String)
	 */
	public void logFatal(String message) {
		log(LogItem.FATAL, message);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.logger.IFWLogger#logFatal(java.lang.String, java.lang.Object)
	 */
	public void logFatal(String message, Object source) {
		log(LogItem.FATAL, message, source);
	}
	
}
