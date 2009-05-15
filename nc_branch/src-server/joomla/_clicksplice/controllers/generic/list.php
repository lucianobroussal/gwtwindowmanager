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


require_once("../../init.php");

$objectType = $_REQUEST['objectType'];
$filter = new Filter($_REQUEST);

switch($objectType) {
	case "user":
		$table = "users";
		break;
	default:
		$table = $objectType;
}

$columns = $filter->columns ? implode(",", $filter->columns) : "*";

$sql = "select ".$columns." from #__". $table;

$sql.= $filter->toSQL();

$database->setQuery($sql);
$rows = $database->loadRowList();

$resultList = new ResultList($filter, $rows);

echo json_encode($resultList);

//echo $sql;
//echo "<br/>";
//echo json_encode($filter);

//
//echo "<pre>";
////debug_zval_dump($attrs['results'][0]);
//debug_zval_dump($database->loadRowList());
//echo "</pre>";

?>

