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
package org.gwm.splice.client.util;

/**
 * Helper methods for Strings.
 * 
 * @author Andy Scholz (andy.scholz@gmail.com)
 *
 */
public class StringUtils {
	
	/**
	 * Converts an array of String to a comma-sperated list.
	 * 
	 * @param vals
	 * @return
	 */
	public static String arrayToCSL(String[] vals) {
		if(vals == null) {
			return null;
		}
		String csl = null;
		boolean first = true;
		for (int i = 0; i < vals.length; i++) {
			if(vals[i] != null) {
				if(first) {
					csl = vals[i];
					first = false;
				}
				else {
					csl = "," + vals[i];
				}
			}
		}
		return csl;
	}

}
