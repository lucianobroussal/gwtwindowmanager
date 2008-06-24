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
package org.gwm.splice.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProxyServlet extends HttpServlet {
	
	/**
	 * Just to get rid of serialize warnings... 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the "real" url we want to send request to
		URL targetUrl = new URL(request.getParameter("targeturl"));
		
		// open a connection to the target url
		HttpURLConnection conn = (HttpURLConnection)targetUrl.openConnection();
		
		// write the headers to the target
		Enumeration e = request.getHeaderNames();
		while(e.hasMoreElements()) {
			String a = (String)e.nextElement();
			conn.addRequestProperty(a,request.getHeader(a));
		}

		conn.setRequestMethod(request.getMethod());
		if(request.getMethod().equalsIgnoreCase("POST")) {
			conn.setRequestMethod(request.getMethod());
			conn.setDoOutput(true);
			// write request data to target
			writeData(request.getInputStream(), conn.getOutputStream());
		}

		// send the ouput from the target back to client
		writeData(conn.getInputStream(), response.getOutputStream());
	}

	///////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Simple input -> output copy.
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	private void writeData(InputStream is, OutputStream os) throws IOException {
		int len;
		byte b[] = new byte[1024];
		
		len = is.read(b,0,1024);
		while(len > 0) {
			os.write(b,0,len);
			len = is.read(b,0,1024);
		}
		is.close();
		os.close();
	}

}
