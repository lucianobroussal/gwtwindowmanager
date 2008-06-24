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

class User extends GenericDBTable {

	/** @var id : bigint */
	var $id;
	/** @var userName : varchar */
	var $userName;
	/** @var password : varchar */
	var $password;
	/** @var firstName : varchar */
	var $firstName;
	/** @var middleName1 : varchar */
	var $middleName1;
	/** @var middleName2 : varchar */
	var $middleName2;
	/** @var lastName : varchar */
	var $lastName;
	/** @var salutation : varchar */
	var $salutation;
	/** @var email : varchar */
	var $email;
	/** @var altEmail : varchar */
	var $altEmail;
	/** @var phone : varchar */
	var $phone;
	/** @var cellPhone : varchar */
	var $cellPhone;
	/** @var address : varchar */
	var $address;
	/** @var city : varchar */
	var $city;
	/** @var state : varchar */
	var $state;
	/** @var postCode : varchar */
	var $postCode;
	/** @var country : char */
	var $country;
	/** @var fax : varchar */
	var $fax;
	/** @var description : varchar */
	var $description;
	/** @var authenticationMode : int */
	var $authenticationMode;
	/** @var sessionAttributes : varchar */
	var $sessionAttributes;

	///////////////////////////////////////////////////////////////////////////////////

	function User(&$database) {
		$this->GenericDBTable('User', 'id', &$database);
	}


	///////////////////////////////////////////////////////////////////////////////////

	/** Dont change or override this
	 * It's needed to determine table info (like field names).
	 */
	function _getTableBaseClass() {
		return get_class();
	}

	///////////////////////////////////////////////////////////////////////////////////

}


?>
