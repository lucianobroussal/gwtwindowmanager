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

// Set flag that to indicate this is a valid entry point
define( '_VALID_MOS', 1 );

require_once("../../init.php");
require_once($csModelsDir."/JUser.php");


$obj = new JUser(&$database);

readData($obj);

$verrors = validate($obj);

if($verrors) {
	returnValidationError("Unable to save because of validation errors", $verrors);
}

$obj->store();

returnOK("User saved");

//////////////////////////////////////////////////////////////////////////////////

/**
 * Returns array of errors if failed, else returns false (i.e. no errors).
 */
function validate($obj) {
	$errors = array();
	$obj->checkRequiredFields("name,username, email", &$errors);
	
	
	if(count($errors) > 0) {
		return $errors;
	}
	
	return false;
}

?>
