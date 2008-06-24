rem Copyright (c) 2006-2007 Luciano Broussal <luciano.broussal AT gmail.com>
rem (http://www.gwtwindowmanager.org)
rem
rem Licensed under the Apache License, Version 2.0 (the "License"); you may not
rem use this file except in compliance with the License. You may obtain a copy of
rem the License at
rem
rem http://www.apache.org/licenses/LICENSE-2.0
rem
rem Unless required by applicable law or agreed to in writing, software
rem distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
rem WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
rem License for the specific language governing permissions and limitations under
rem the License.

rem set the paths to the GWT libs in env.bat

call env
@java -cp "%~dp0\..\gwm.jar;%~dp0\src;%~dp0\classes;%~dp0\src-samples;%GWT_HOME%/gwt-user.jar;%GWT_HOME%/gwt-dev-windows.jar" com.google.gwt.dev.GWTCompiler -out "%~dp0\www" %* org.gwm.samples.gwmdemo.GwmDemo