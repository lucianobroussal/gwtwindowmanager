<?php
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

/**
 * @author andy.scholz@gmail.com
 * @copyright (c)2006-2007 Flipperwing Ltd.
 */


// no direct access
if(!defined( '_VALID_MOS' )) { die( 'Restricted access' ); }

class JUser extends GenericDBTable {

	/** @var id : int */
	var $id;
	/** @var name : varchar */
	var $name;
	/** @var username : varchar */
	var $username;
	/** @var email : varchar */
	var $email;
	/** @var password : varchar */
	var $password;
	/** @var usertype : varchar */
	var $usertype;
	/** @var block : tinyint */
	var $block;
	/** @var sendEmail : tinyint */
	var $sendEmail;
	/** @var gid : tinyint unsigned */
	var $gid;
	/** @var registerDate : datetime */
	var $registerDate;
	/** @var lastvisitDate : datetime */
	var $lastvisitDate;
	/** @var activation : varchar */
	var $activation;
	/** @var params : text */
	var $params;

	///////////////////////////////////////////////////////////////////////////////////

	function JUser(&$database) {
		$this->GenericDBTable('JUser', 'org.gwm.splice.client.user', 'jos_users', &$database);
	}


	///////////////////////////////////////////////////////////////////////////////////

	/** Dont change or override this
	 * It's needed to determine table info (like field names).
	 */
	function _getTableBaseClass() {
		return get_class();
	}
	function _getRemoteObjectType() {
		return 'org.gwm.splice.client.user.JUser';
	}

	///////////////////////////////////////////////////////////////////////////////////

}


?>
